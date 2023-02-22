package com.nashss.se.projectsyncup.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.exceptions.ProjectNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for a project using {@link Project} to represent the model in DynamoDB.
 */
public class ProjectDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a ProjectDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the projects table
     */

    @Inject
    public ProjectDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link Project} corresponding to the specified id.
     *
     * @param projectId the Project ID
     * @return the stored project, or null if none was found.
     */
    public Project getProject(String projectId) {
        Project project = this.dynamoDbMapper.load(Project.class, projectId);

        if (project == null) {
            throw new ProjectNotFoundException("Could not find project with id " + projectId);
        }

        return project;
    }

    /**
     * Saves (creates or updates) the given project.
     *
     * @param project The project to save
     * @return The Project object that was saved
     */
    public Project saveProject(Project project) {
        this.dynamoDbMapper.save(project);
        return project;
    }

    /**
     * Deletes the given project.
     *
     * @param project The project to delete
     * @return The Project object that was deleted
     */
    public Project deleteProject(Project project) {
        this.dynamoDbMapper.delete(project);
        return project;
    }

    /**
     * Retrieves the entire List of Projects created bu member.
     *
     * @return The current project List
     */

//    public List<Project> getAllCreatedProjects(String createdById) {
//        Map<String, AttributeValue> valueMap = new HashMap<>();
//        valueMap.put(":createdById", new AttributeValue().withS(createdById));
//
//        DynamoDBQueryExpression<Project> queryExpression = new DynamoDBQueryExpression<Project>()
//                .withIndexName("createdByIdIndex")
//                .withKeyConditionExpression("createdById = :createdById")
//                .withExpressionAttributeValues(valueMap)
//                .withConsistentRead(false); // set consistency mode to EventuallyConsistent
//
//        List<Project> projects = dynamoDbMapper.query(Project.class, queryExpression);
//
//        return projects;
//    }

    /**
     * Retrieves all projects for a member from the database.
     *
     * @param createdById The ID of the member who created the projects.
     * @return A list of all projects created by the member.
     * @throws ProjectNotFoundException If the members ID is `null` or if no projects are found.
     */
    public List<Project> getAllCreatedProjects(String createdById) {
        if (createdById == null) {
            throw new ProjectNotFoundException("No projects found!!");
        }

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
        expressionAttributeValues.put(":val1", new AttributeValue().withS(createdById));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("createdById = :val1")
                .withExpressionAttributeValues(expressionAttributeValues);
        return dynamoDbMapper.scan(Project.class, scanExpression);
    }

}

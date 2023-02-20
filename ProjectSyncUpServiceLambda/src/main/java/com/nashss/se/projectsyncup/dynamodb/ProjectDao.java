package com.nashss.se.projectsyncup.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
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
     * Retrieves the entire Guest List at the party.
     *
     * @return The current Guest List
     */
    public List<Task> getTaskList() {
        List<Task> projectTaskList = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("tasks");

        ScanResult result = client.scan(scanRequest);
        List<Map<String, AttributeValue>> taskList = result.getItems();

        for (Map<String, AttributeValue> entry : taskList) {
            final Task task = mapper.convertValue(taskList, Task.class);
            projectTaskList.add(task);
        }

        return projectTaskList;
    }

}

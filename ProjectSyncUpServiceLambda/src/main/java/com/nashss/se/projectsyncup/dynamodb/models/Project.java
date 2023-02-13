package com.nashss.se.projectsyncup.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.GetProjectRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectResult;
import com.nashss.se.projectsyncup.lambda.LambdaActivityRunner;
import com.nashss.se.projectsyncup.lambda.LambdaRequest;
import com.nashss.se.projectsyncup.lambda.LambdaResponse;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class represents a record in the Projects in DynamoDB.
 */
@DynamoDBTable(tableName = "projects")
public class Project {

    private String projectId;
    private String projectName;
    private String projectDescription;
    private String projectStatus;
    private String createdById;
    private Set<String> projectTasks;
    private Set<String> projectMembers;

    /**
     * Gets the project ID of this project.
     *
     * @return the project ID
     */
    @DynamoDBHashKey(attributeName = "projectId")
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID of this project.
     *
     * @param projectId the project ID
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the project name of this project.
     *
     * @return the project name
     */
    @DynamoDBAttribute(attributeName = "projectName")
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project name of this project.
     *
     * @param projectName the project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets the project description of this project.
     *
     * @return the project description
     */
    @DynamoDBAttribute(attributeName = "projectDescription")
    public String getProjectDescription() {
        return projectDescription;
    }


    /**
     * Sets the project description of this project.
     *
     * @param projectDescription the project description
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Gets the project status of this project (Not started, In Progress, Completed).
     *
     * @return the project status
     */
    @DynamoDBAttribute(attributeName = "projectStatus")
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * Sets the project status of this project.
     *
     * @param projectStatus the project status
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * Gets the id of user who created this project.
     *
     * @return the userId of who crated project
     */
    @DynamoDBAttribute(attributeName = "createdById")
    public String getCreatedById() {
        return createdById;
    }

    /**
     * Sets the id of user who created this project.
     *
     * @param createdById the userId of who crated project
     */
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    /**
     * Returns the set of tasks associated with this Project, null if there are none.
     *
     * @return Set of tasks for this project
     */
    @DynamoDBAttribute(attributeName = "projectTasks")
    public Set<String> getProjectTasks() {
        // normally, we would prefer to return an empty Set if there are no
        // tasks, but DynamoDB doesn't represent empty Sets...needs to be null
        // instead
        if (null == projectTasks) {
            return null;
        }

        return new HashSet<>(projectTasks);
    }

    /**
     * Sets the tasks for this Project as a copy of input, or null if input is null.
     *
     * @param projectTasksSet Set of tasks for this project
     */
    public void setTasks(Set<String> projectTasksSet) {
        // see comment in getProjectTasks()
        this.projectTasks = projectTasksSet;
    }

    /**
     * Returns the set of team members associated with this Project, null if there are none.
     *
     * @return Set of team members for this project
     */
    @DynamoDBAttribute(attributeName = "projectMembers")
    public Set<String> getProjectMembers() {
        // normally, we would prefer to return an empty Set if there are no tasks,
        // but DynamoDB doesn't represent empty Sets...needs to be null instead
        if (null == projectMembers) {
            return null;
        }

        return new HashSet<>(projectMembers);
    }

    /**
     * Sets the team members for this Project as a copy of input, or null if input is null.
     *
     * @param projectMembers Set of team members for this project
     */
    public void setProjectMembers(Set<String> projectMembers) {
        // see comment in getProjectMembers()
        if (null == projectMembers) {
            this.projectMembers = null;
        } else {
            this.projectMembers = new HashSet<>(projectMembers);
        }

        this.projectMembers = projectMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return getProjectId().equals(project.getProjectId()) &&
                getProjectName().equals(project.getProjectName()) &&
                getProjectDescription().equals(project.getProjectDescription()) &&
                getProjectStatus().equals(project.getProjectStatus()) &&
                getCreatedById().equals(project.getCreatedById()) &&
                getProjectTasks().equals(project.getProjectTasks()) &&
                getProjectMembers().equals(project.getProjectMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(), getProjectName(), getProjectDescription(),
                getProjectStatus(), getCreatedById(), getProjectTasks(), getProjectMembers());
    }
}

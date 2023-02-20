package com.nashss.se.projectsyncup.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


/**
 * AddTaskToProjectRequest is a class that holds the necessary information to add a task to a project.
 */
@JsonDeserialize(builder = AddTaskToProjectRequest.Builder.class)
public class AddTaskToProjectRequest {
    private final String projectId;
    private final String taskId;
    private final String taskName;
    private final String taskDescription;
    private final String taskDueDate;
    private final String taskAssignedUser;

    /**
     * Constructor for the AddTaskToProjectRequest class.
     *
     * @param taskId
     * @param taskName         The name of the task to be added.
     * @param taskDescription  The description of the task to be added.
     * @param taskDueDate      The due date of the task to be added.
     * @param taskAssignedUser The user assigned to the task to be added.
     * @param projectId        The id of the project to which the task should be added.
     */
    public AddTaskToProjectRequest(String taskId, String taskName, String taskDescription, String taskDueDate, String taskAssignedUser, String projectId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDueDate = taskDueDate;
        this.taskAssignedUser = taskAssignedUser;
        this.projectId = projectId;
    }

    /**
     * Returns the id of the project to which the task should be added.
     *
     * @return the project id
     */
    public String getProjectId() {
        return projectId;
    }

    public String getTaskId() {
        return projectId;
    }

    /**
     * Returns the name of the task to be added.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the description of the task to be added.
     *
     * @return the task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the due date of the task to be added.
     *
     * @return the task due date
     */
    public String getTaskDueDate() {
        return taskDueDate;
    }

    /**
     * Returns the status of the task to be added.
     *
     * @return the task status
     */


    /**
     * Returns the user assigned to the task to be added.
     *
     * @return the task assigned user
     */
    public String getTaskAssignedUser() {
        return taskAssignedUser;
    }

    /**
     * Returns the time spent on the task to be added.
     *
     * @return the task timer
     */



    @Override
    public String toString() {
        return "AddTaskToProjectRequest{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskDueDate='" + taskDueDate + '\'' +
                ", taskAssignedUser=" + taskAssignedUser + '\'' +
                ", projectId=" + projectId +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {

        private String taskId;
        private String taskName;
        private String taskDescription;
        private String taskDueDate;
        private String taskAssignedUser;
        private String projectId;


        public Builder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder withTaskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public Builder withTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
            return this;
        }

        public Builder withTaskDueDate(String taskDueDate) {
            this.taskDueDate = taskDueDate;
            return this;
        }

        public Builder withTaskAssignedUser(String taskAssignedUser) {
            this.taskAssignedUser = taskAssignedUser;
            return this;
        }

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public AddTaskToProjectRequest build() {
            return new AddTaskToProjectRequest(taskId, taskName, taskDescription,
                    taskDueDate, taskAssignedUser, projectId);
        }
    }

}



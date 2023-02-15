package com.nashss.se.projectsyncup.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


/**
 * AddTaskToProjectRequest is a class that holds the necessary information to add a task to a project.
 */
@JsonDeserialize(builder = AddTaskToProjectRequest.Builder.class)
public class AddTaskToProjectRequest {
    private final String projectId;
    private final String taskName;
    private final String taskDescription;
    private final String taskDueDate;
    private final String taskStatus;
    private final String taskAssignedUser;
    private final Integer taskTimer;

    /**
     * Constructor for the AddTaskToProjectRequest class.
     *
     * @param projectId The id of the project to which the task should be added.
     * @param taskName The name of the task to be added.
     * @param taskDescription The description of the task to be added.
     * @param taskDueDate The due date of the task to be added.
     * @param taskStatus The status of the task to be added.
     * @param taskAssignedUser The user assigned to the task to be added.
     * @param taskTimer The time spent on the task to be added.
     */
    public AddTaskToProjectRequest(String projectId, String taskName, String taskDescription, String taskDueDate,
                                   String taskStatus, String taskAssignedUser, Integer taskTimer) {
        this.projectId = projectId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDueDate = taskDueDate;
        this.taskStatus = taskStatus;
        this.taskAssignedUser = taskAssignedUser;
        this.taskTimer = taskTimer;
    }

    /**
     * Returns the id of the project to which the task should be added.
     *
     * @return the project id
     */
    public String getProjectId() {
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
    public String getTaskStatus() {
        return taskStatus;
    }

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
    public Integer getTaskTimer() {
        return taskTimer;
    }


    @Override
    public String toString() {
        return "AddTaskToProjectRequest{" +
                "projectId='" + projectId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskDueDate='" + taskDueDate + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskAssignedUser=" + taskAssignedUser +
                ", taskTimer=" + taskTimer +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String projectId;
        private String taskName;
        private String taskDescription;
        private String taskDueDate;
        private String taskStatus;
        private String taskAssignedUser;
        private Integer taskTimer;

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
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

        public Builder withTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        public Builder withTaskAssignedUser(String taskAssignedUser) {
            this.taskAssignedUser = taskAssignedUser;
            return this;
        }

        public Builder withTaskTimer(Integer taskTimer) {
            this.taskTimer = taskTimer;
            return this;
        }

        public AddTaskToProjectRequest build() {
            return new AddTaskToProjectRequest(projectId, taskName, taskDescription,
                    taskDueDate, taskStatus, taskAssignedUser, taskTimer);
        }
    }

}



package com.nashss.se.projectsyncup.models;

import java.util.Objects;

/**
 * This class represents the model of a task.
 * It contains information about the task ID, task name,
 * task description, taskDueDate, status, who created the task,
 * and who the tasks was assigned to.
 */
public class TaskModel {

    private final String taskId;
    private final String projectId;
    private final String taskName;
    private final String taskDescription;
    private final String taskDueDate;
//    private final String createdById;
    private final String taskAssignedUser;

    /**
     * Instantiates a new TaskModel object.
     * Private to enforce user to use Builder to create a new instance of CreateProjectRequest
     */
    public TaskModel(String taskId, String taskName, String taskDescription,
                     String taskDueDate, String taskAssignedUser, String projectId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDueDate = taskDueDate;

        this.taskAssignedUser = taskAssignedUser;
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }


    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

//    public String getCreatedById() {
//        return createdById;
//    }

    public String getTaskAssignedUser() {
        return taskAssignedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskModel)) return false;
        TaskModel taskModel = (TaskModel) o;
        return getTaskId().equals(taskModel.getTaskId()) &&
                getProjectId().equals(taskModel.getProjectId()) &&
                getTaskName().equals(taskModel.getTaskName()) &&
                getTaskDescription().equals(taskModel.getTaskDescription()) &&
                getTaskDueDate().equals(taskModel.getTaskDueDate()) &&
                getTaskAssignedUser().equals(taskModel.getTaskAssignedUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getProjectId(), getTaskName(),
                getTaskDescription(), getTaskDueDate(), getTaskAssignedUser());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String taskId;
        private String projectId;
        private String taskName;
        private String taskDescription;
        private String taskDueDate;
//        private String createdById;
        private String taskAssignedUser;

        public Builder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

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

//        public Builder withCreatedById(String createdById) {
//            this.createdById = createdById;
//            return this;
//        }

        public Builder withTaskAssignedUser(String taskAssignedUser) {
            this.taskAssignedUser = taskAssignedUser;
            return this;
        }

        public TaskModel build() {
            return new TaskModel(taskId, taskName, taskDescription,
                    taskDueDate, taskAssignedUser, projectId);
        }
    }
}

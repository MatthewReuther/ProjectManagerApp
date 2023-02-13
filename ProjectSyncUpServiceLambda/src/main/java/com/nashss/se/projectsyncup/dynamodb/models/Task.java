package com.nashss.se.projectsyncup.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;


/**
 * Class represents a record in the Task in DynamoDB.
 */
@DynamoDBTable(tableName = "tasks")
public class Task {

    private String taskId;
    private String taskName;
    private String taskDescription;
    private String taskDueDate;
    private String createdById;
    private String taskAssignedUser;

    @DynamoDBHashKey(attributeName = "taskId")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @DynamoDBAttribute(attributeName = "taskName")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @DynamoDBAttribute(attributeName = "taskDescription")
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @DynamoDBAttribute(attributeName = "taskDueDate")
    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    @DynamoDBAttribute(attributeName = "createdById")
    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getTaskAssignedUser() {
        return taskAssignedUser;
    }

    @DynamoDBAttribute(attributeName = "taskAssignedUser")
    public void setTaskAssignedUser(String taskAssignedUser) {
        this.taskAssignedUser = taskAssignedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTaskId().equals(task.getTaskId()) &&
                getTaskName().equals(task.getTaskName()) &&
                getTaskDescription().equals(task.getTaskDescription()) &&
                getTaskDueDate().equals(task.getTaskDueDate()) &&
                getCreatedById().equals(task.getCreatedById()) &&
                getTaskAssignedUser().equals(task.getTaskAssignedUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getTaskName(), getTaskDescription(),
                getTaskDueDate(), getCreatedById(), getTaskAssignedUser());
    }

    @Override
    public String toString() {
        return "Song{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskDueDate='" + taskDueDate + '\'' +
                ", createdById=" + createdById +
                ", taskAssignedUser=" + taskAssignedUser +
                '}';
    }
}

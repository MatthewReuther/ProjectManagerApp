package com.nashss.se.projectsyncup.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


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

}

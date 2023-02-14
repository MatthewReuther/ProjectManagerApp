package com.nashss.se.projectsyncup.dynamodb;

import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a task using {@link Task} to represent the model in DynamoDB.
 */
@Singleton
public class TaskDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an TaskDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the tasks table
     */
    @Inject
    public TaskDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Creates or updates the task.
     *
     * @param task The task to save
     * @return The Task object that was saved
     */
    public Task saveTask(Task task) {
        this.dynamoDbMapper.save(task);
        return task;
    }

    /**
     * Returns a {@link List} of {@link Task} corresponding to the specified project id.
     *
     * @param projectId the project ID
     * @return A list of stored tasks for project, or throws an TasksNotFoundException if none were found.
     */

    public List<Task> getTasksForProject(String projectId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":projectId" , new AttributeValue().withS(projectId));

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withKeyConditionExpression("projectId = :projectId")
                .withExpressionAttributeValues(valueMap);

        List<Task> taskList = dynamoDbMapper.queryPage(Task.class, queryExpression).getResults();

        return taskList;
    }

    /**
     * Returns a {@link List} of {@link Task} corresponding to the specified members id.
     *
     * @param taskAssignedUser the members ID
     * @return A list of stored Tasks assigned to member,
     * or throws an TaskNotFoundException if none were found.
     */
    public List<Task> getAssignedTasks(String taskAssignedUser) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":taskAssignedUser" , new AttributeValue().withS(taskAssignedUser));

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withKeyConditionExpression("taskAssignedUser = :taskAssignedUser")
                .withExpressionAttributeValues(valueMap);
        List<Task> taskList = dynamoDbMapper.queryPage(Task.class, queryExpression).getResults();

        return taskList;
    }


}





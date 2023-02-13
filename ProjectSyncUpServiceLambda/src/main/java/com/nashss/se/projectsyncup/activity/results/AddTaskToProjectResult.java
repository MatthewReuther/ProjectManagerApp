package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The `AddTaskToProjectResult` class represents the result of adding a TaskModel.
 * It contains the projectModel that was created.
 */
public class AddTaskToProjectResult {

    private final List<TaskModel> taskList;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param taskList The list of tasks in project.
     */
    private AddTaskToProjectResult(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the taskList that was updated.
     *
     * @return The taskList that was updated.
     */
    public List<TaskModel> getTaskList() {
        return new ArrayList<>(taskList);
    }

    /**
     * Returns a string representation of the {@link AddTaskToProjectResult} object.
     *
     * @return a string representation of the {@link AddTaskToProjectResult} object, in the format of
     * "AddTaskToProjectResult{task=TASK_MODEL}".
     */
    @Override
    public String toString() {
        return "AddTaskToProjectResult{" +
                "taskList=" + taskList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TaskModel> taskList;

        public Builder withTaskList(List<TaskModel> taskList) {
            this.taskList = new ArrayList<>(taskList);
            return this;
        }

        public AddTaskToProjectResult build() {
            return new AddTaskToProjectResult(taskList);
        }
    }

}
package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The `AddTaskToProjectResult` class represents the result of adding a TaskModel.
 * It contains the projectModel that was created.
 */
public class AddTaskToProjectResult {

    private final List<TaskModel> projectTasks;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param projectTasks The list of tasks in project.
     */
    private AddTaskToProjectResult(List<TaskModel> projectTasks) {
        this.projectTasks = projectTasks;
    }

    /**
     * Returns the taskList that was updated.
     *
     * @return The taskList that was updated.
     */
    public List<TaskModel> getProjectTasks() {
        return new ArrayList<>(projectTasks);
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
                "projectTasks=" + projectTasks +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TaskModel taskModel;
        private List<TaskModel> projectTasks;

        /**
         * Builder to create AddGuestToPartyResult object.
         * @param taskModel the list of users at the party.
         * @return the Builder object
         */
        public Builder withTaskModel(TaskModel taskModel) {
            this.taskModel = taskModel;
            return this;
        }

        public Builder withTaskList(List<TaskModel> taskList) {
            this.projectTasks = new ArrayList<>(taskList);
            return this;
        }

        public AddTaskToProjectResult build() {
            return new AddTaskToProjectResult(projectTasks);
        }
    }

}
package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.List;

/**
 * The `RemoveTaskFromProjectResult` class represents the result of removing a TaskModel.
 * It contains the taskModel that was created.
 */
public class DeleteTaskFromProjectResult {

    private final List<TaskModel> projectTasks;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param taskList The list of tasks in project.
     */
    private DeleteTaskFromProjectResult(List<TaskModel> taskList) {
        this.projectTasks = taskList;
    }

    public List<TaskModel> getProjectTasks() {
        return projectTasks;
    }

    /**
     * Returns a string representation of the {@link DeleteTaskFromProjectResult} object.
     *
     * @return a string representation of the {@link DeleteTaskFromProjectResult} object, in the format of
     * "RemoveTaskFromProjectResult{task=TASK_MODEL}".
     */
    @Override
    public String toString() {
        return "RemoveTaskFromProjectResult{" +
                "taskList=" + projectTasks +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<TaskModel> taskList;

        public Builder withProject(List<TaskModel> taskList) {
            this.taskList = taskList;
            return this;
        }

        public DeleteTaskFromProjectResult build() {
            return new DeleteTaskFromProjectResult(taskList);
        }
    }

}

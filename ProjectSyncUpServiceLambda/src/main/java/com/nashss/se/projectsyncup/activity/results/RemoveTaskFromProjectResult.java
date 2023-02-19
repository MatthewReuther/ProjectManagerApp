package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.List;

/**
 * The `RemoveTaskFromProjectResult` class represents the result of removing a TaskModel.
 * It contains the taskModel that was created.
 */
public class RemoveTaskFromProjectResult {

    private final List<TaskModel> taskList;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param taskList The list of tasks in project.
     */
    private RemoveTaskFromProjectResult(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    public List<TaskModel> getProjectTasks() {
        return taskList;
    }

    /**
     * Returns a string representation of the {@link RemoveTaskFromProjectResult} object.
     *
     * @return a string representation of the {@link RemoveTaskFromProjectResult} object, in the format of
     * "RemoveTaskFromProjectResult{task=TASK_MODEL}".
     */
    @Override
    public String toString() {
        return "RemoveTaskFromProjectResult{" +
                "taskList=" + taskList +
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

        public RemoveTaskFromProjectResult build() {
            return new RemoveTaskFromProjectResult(taskList);
        }
    }

}

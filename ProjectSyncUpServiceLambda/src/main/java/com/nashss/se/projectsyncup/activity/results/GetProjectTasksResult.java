package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class GetProjectTasksResult {
    private final List<TaskModel> taskList;

    private GetProjectTasksResult(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    public List<TaskModel> getTaskList() {
        return new ArrayList<>(taskList);
    }

    @Override
    public String toString() {
        return "GetProjectTasksResult{" +
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

        public GetProjectTasksResult build() {
            return new GetProjectTasksResult(taskList);
        }
    }
}


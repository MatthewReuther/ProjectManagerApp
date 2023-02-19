package com.nashss.se.projectsyncup.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RemoveTaskFromProjectRequest.Builder.class)
public class RemoveTaskFromProjectRequest {

    private final String taskId;
    private final String projectId;

    public RemoveTaskFromProjectRequest(String taskId, String projectId) {
        this.taskId = taskId;
        this.projectId = projectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getProjectId() {
        return projectId;
    }

    Override
    public String toString() {
        return "RemoveTaskFromProjectRequest{" +
                ", taskId='" + taskId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String taskId;
        private String projectId;

        public Builder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public RemoveTaskFromProjectRequest build() {
            return new RemoveTaskFromProjectRequest(taskId, projectId);
        }
    }
}
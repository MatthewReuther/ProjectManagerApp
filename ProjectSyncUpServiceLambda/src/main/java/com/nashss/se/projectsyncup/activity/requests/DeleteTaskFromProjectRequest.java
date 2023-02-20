package com.nashss.se.projectsyncup.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteTaskFromProjectRequest.Builder.class)
public class DeleteTaskFromProjectRequest {

    private final String taskId;
    private final String projectId;

    public DeleteTaskFromProjectRequest(String projectId, String taskId) {
        this.projectId = projectId;
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getProjectId() {
        return projectId;
    }


    public String toString() {
        return "RemoveTaskFromProjectRequest{" +
                ", projectId='" + projectId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String projectId;
        private String taskId;

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }


        public DeleteTaskFromProjectRequest build() {
            return new DeleteTaskFromProjectRequest(projectId, taskId);
        }
    }
}
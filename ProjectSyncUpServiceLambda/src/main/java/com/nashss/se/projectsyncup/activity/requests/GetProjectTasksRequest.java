package com.nashss.se.projectsyncup.activity.requests;

import com.nashss.se.projectsyncup.models.TaskModel;

public class GetProjectTasksRequest {

    private final String projectId;

    private GetProjectTasksRequest(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return "GetProjectTasksRequest{" +
                "projectId='" + projectId + '\'' +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;

        public Builder withProjectId( String projectId) {
            this.projectId = projectId;
            return this;
        }

        public GetProjectTasksRequest build() {
            return new GetProjectTasksRequest(projectId);
        }
    }

}

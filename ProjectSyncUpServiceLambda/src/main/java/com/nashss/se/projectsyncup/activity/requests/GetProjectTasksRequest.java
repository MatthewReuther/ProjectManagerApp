package com.nashss.se.projectsyncup.activity.requests;

import com.nashss.se.projectsyncup.models.TaskModel;

public class GetProjectTasksRequest {


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public String getProjectId() {
    }

    public static class Builder {

        public GetProjectTasksRequest build() {
            return new GetProjectTasksRequest();
        }
    }
}

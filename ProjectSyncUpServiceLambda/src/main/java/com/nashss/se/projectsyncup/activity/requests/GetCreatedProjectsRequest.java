package com.nashss.se.projectsyncup.activity.requests;

public class GetCreatedProjectsRequest {

    /**
     * The ID of the project.
     */
    private final String projectId;

    /**
     * Constructs a `GetCreatedProjectsRequest` object with the given project ID.
     *
     * @param projectId the ID of the project
     */
    private GetCreatedProjectsRequest(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Returns the project ID.
     *
     * @return the project ID
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Returns a string representation of this `GetProjectRequest` object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetCreatedProjectsRequest{" +
                "projectId='" + projectId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public GetCreatedProjectsRequest build() {

            return new GetCreatedProjectsRequest(projectId);
        }
    }

}

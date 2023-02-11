package com.nashss.se.projectsyncup.activity.requests;

/**
 * The `GetProjectRequest` class represents a request to retrieve a project.
 * It is used to provide the project ID in order to get the project details.
 *
 */
public class GetProjectRequest {

    /**
     * The ID of the project.
     */
    private final String projectId;

    /**
     * Constructs a `GetProjectRequest` object with the given project ID.
     *
     * @param projectId the ID of the project
     */
    private GetProjectRequest(String projectId) {
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
        return "GetProjectRequest{" +
                "projectId='" + projectId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;

        public Builder withId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public GetProjectRequest build() {
            return new GetProjectRequest(projectId);
        }
    }
}

package com.nashss.se.projectsyncup.activity.requests;

public class GetCreatedProjectsRequest {

    /**
     * The ID of the project.
     */
    private final String createdById;

    /**
     * Constructs a `GetCreatedProjectsRequest` object with the given createdById.
     *
     * @param createdById the ID of the project
     */
    public GetCreatedProjectsRequest(String createdById) {
        this.createdById = createdById;
    }

    /**
     * Returns the project ID.
     *
     * @return the project ID
     */
    public String getCreatedById() {
        return createdById;
    }

    /**
     * Returns a string representation of this `GetProjectRequest` object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetCreatedProjectsRequest{" +
                "createdById='" + createdById + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String createdById;

        public Builder withCreatedById(String createdById) {
            this.createdById = createdById;
            return this;
        }

        public GetCreatedProjectsRequest build() {

            return new GetCreatedProjectsRequest(createdById);
        }
    }

}

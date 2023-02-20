package com.nashss.se.projectsyncup.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


public class UpdateProjectRequest {
    private final String projectName;
    private final String projectDescription;
    private final String projectStatus;


    /**
     * Instantiates a new UpdateProjectRequest object.
     * Private to enforce user to use Builder to create a new instance of UpdateProjectRequest
     *
     * @param projectName        The name of the project
     * @param projectDescription The description of the project
     * @param projectStatus      The status of the project
     */

    private UpdateProjectRequest(String projectName, String projectDescription, String projectStatus) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
    }

    /**
     * Returns the name of the project.
     *
     * @return The name of the project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Returns the description of the project.
     *
     * @return The description of the project.
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Returns the status of the project.
     *
     * @return The status of the project.
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    @Override
    public String toString() {
        return "UpdateProjectRequest{" +
                "projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String projectName;
        private String projectDescription;
        private String projectStatus;

        public Builder withProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder withProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public Builder withProjectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
            return this;
        }

        public UpdateProjectRequest build() {
            return new UpdateProjectRequest(projectName, projectDescription, projectStatus);
        }

    }
}

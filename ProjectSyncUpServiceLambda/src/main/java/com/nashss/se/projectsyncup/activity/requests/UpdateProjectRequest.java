package com.nashss.se.projectsyncup.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateProjectRequest.Builder.class)
public class UpdateProjectRequest {

    private final String projectId;
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

    private UpdateProjectRequest(String projectId, String projectName,
                                 String projectDescription, String projectStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
    }

    /**
     * Returns the id of the project.
     *
     * @return The id of the project.
     */
    public String getProjectId() {
        return projectId;
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
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
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
        private String projectId;
        private String projectName;
        private String projectDescription;
        private String projectStatus;

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

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
            return new UpdateProjectRequest(projectId, projectName, projectDescription, projectStatus);
        }

    }
}

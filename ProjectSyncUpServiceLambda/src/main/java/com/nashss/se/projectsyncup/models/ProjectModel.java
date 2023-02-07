package com.nashss.se.projectsyncup.models;

import java.util.List;
import java.util.Objects;

import static com.nashss.se.projectsyncup.utils.CollectionUtils.copyToList;

/**
 * This class represents the model of a project.
 * It contains information about the project ID, project name,
 * project description, project status, who project was created by,
 * list of tasks in project and list of members associated with the playlist
 */
public class ProjectModel {
    private final String projectId;
    private final String projectName;
    private final String projectDescription;
    private final String projectStatus;
    private final String createdById;
    private final List<String> projectTasks;
    private final List<String> projectMembers;

    /**
     * Instantiates a new PlaylistModel object.
     * Private to enforce user to use Builder to create a new instance of CreateProjectRequest
     */

    private ProjectModel(String projectId, String projectName, String projectDescription,
                         String projectStatus, String createdById, List<String> projectTasks,
                         List<String> projectMembers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
        this.createdById = createdById;
        this.projectTasks = projectTasks;
        this.projectMembers = projectMembers;
    }

    /**
     * Returns the ID of the project.
     *
     * @return The ID of the project.
     */
    public String getId() {
        return projectId;
    }

    /**
     * Returns the name of the project.
     *
     * @return The name of the project.
     */
    public String getName() {
        return projectName;
    }

    /**
     * Returns the description of the project.
     *
     * @return The description of the project.
     */
    public String getDescription() {
        return projectDescription;
    }

    /**
     * Returns the status of the project.
     *
     * @return The status of the project.
     */
    public String getStatus() {
        return projectStatus;
    }

    /**
     * Returns the name of the person who created the project.
     *
     * @return The name of the person who created the project.
     */
    public String getCreatedBy() {
        return createdById;
    }

    /**
     * Returns a list of tasks associated with the project.
     *
     * @return A list of tasks associated with the project.
     */
    public List<String> getTasks() {
        return copyToList(projectTasks);
    }

    /**
     * Returns a list of members associated with the project.
     *
     * @return A list of members associated with the project.
     */
    public List<String> getProjectMembers() {
        return copyToList(projectMembers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectModel)) {
            return false;
        }
        ProjectModel that = (ProjectModel) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getDescription().equals(that.getDescription()) &&
                getStatus().equals(that.getStatus()) &&
                getCreatedBy().equals(that.getCreatedBy()) &&
                getTasks().equals(that.getTasks()) &&
                getProjectMembers().equals(that.getProjectMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(),
                getStatus(), getCreatedBy(), getTasks(), getProjectMembers());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String projectId;
        private String projectName;
        private String projectDescription;
        private String projectStatus;
        private String createdById;
        private List<String> projectTasks;
        private List<String> projectMembers;

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

        public Builder withCreatedById(String createdById) {
            this.createdById = createdById;
            return this;
        }

        public Builder withProjectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
            return this;
        }

        public Builder withProjectTasks(List<String> projectTasks) {
            this.projectTasks = copyToList(projectTasks);
            return this;
        }

        public Builder withProjectMembers(List<String> projectMembers) {
            this.projectMembers = copyToList(projectMembers);
            return this;
        }

        public ProjectModel build() {
            return new ProjectModel(projectId, projectName, projectDescription,
                    projectStatus, createdById, projectTasks, projectMembers);
        }
    }
}

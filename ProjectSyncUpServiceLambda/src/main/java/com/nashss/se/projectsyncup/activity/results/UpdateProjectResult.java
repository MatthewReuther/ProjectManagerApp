package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.ProjectModel;

public class updated {

    /**
     * The projectModel that was updated.
     */
    private final ProjectModel project;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param project The projectModel that was crupdatedeated.
     */
    private UpdateProjectResult(ProjectModel project) {
        this.project = project;
    }

    /**
     * Returns the projectModel that was updated.
     *
     * @return The projectModel that was updated.
     */
    public ProjectModel getProject() {
        return project;
    }

    /**
     * Returns a string representation of the {@link UpdateProjectResult} object.
     *
     * @return a string representation of the {@link UpdateProjectResult} object, in the format of
     * "UpdateProjectResult{project=PROJECT_MODEL}".
     */
    @Override
    public String toString() {
        return "UpdateProjectResult{" +
                "project=" + project +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private ProjectModel project;

        public Builder withProject(ProjectModel project) {
            this.project = project;
            return this;
        }

        public UpdateProjectResult build() {
            return new UpdateProjectResult(project);
        }
    }
}

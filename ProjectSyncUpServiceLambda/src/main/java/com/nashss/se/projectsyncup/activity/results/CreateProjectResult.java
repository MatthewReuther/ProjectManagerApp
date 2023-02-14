package com.nashss.se.projectsyncup.activity.results;

import com.nashss.se.projectsyncup.models.ProjectModel;

/**
 * The `CreateProjectResult` class represents the result of creating a projectModel.
 * It contains the projectModel that was created.
 */
public class CreateProjectResult {
    /**
     * The projectModel that was created.
     */
    private final ProjectModel project;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param project The projectModel that was created.
     */
    private CreateProjectResult(ProjectModel project) {
        this.project = project;
    }

    /**
     * Returns the projectModel that was created.
     *
     * @return The projectModel that was created.
     */
    public ProjectModel getProject() {
        return project;
    }

    /**
     * Returns a string representation of the {@link CreateProjectResult} object.
     *
     * @return a string representation of the {@link CreateProjectResult} object, in the format of
     * "CreateProjectResult{project=PROJECT_MODEL}".
     */
    @Override
    public String toString() {
        return "CreateProjectResult{" +
                "project=" + project +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private ProjectModel projectModel;

        public Builder withProject(ProjectModel projectModel) {
            this.projectModel = projectModel;
            return this;
        }

        public CreateProjectResult build() {
            return new CreateProjectResult(projectModel);
        }
    }
}

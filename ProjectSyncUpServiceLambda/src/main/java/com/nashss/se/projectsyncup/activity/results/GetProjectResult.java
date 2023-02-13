package com.nashss.se.projectsyncup.activity.results;
import com.nashss.se.projectsyncup.models.ProjectModel;

/**
 * The `GetProjectResult` class represents the result of a request to retrieve a project.
 * It contains the `ProjectModel` object that holds the details of the project.
 *
 */
public class GetProjectResult {
    /**
     * The `ProjectModel` object that holds the details of the project.
     */
    private final ProjectModel projectModel;

    /**
     * Constructs a `GetProjectResult` object with the given `ProjectModel` instance.
     *
     * @param projectModel the `ProjectModel` object
     */
    private GetProjectResult(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    /**
     * Returns the `ProjectModel` object.
     *
     * @return the `ProjectModel` object
     */
    public ProjectModel getProjectModel() {
        return projectModel;
    }

    /**
     * Returns a string representation of this `GetProjectResult` object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetProjectResult{" +
                "project=" + projectModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProjectModel projectModel;

        public Builder withProjectModel(ProjectModel projectModel) {
            this.projectModel = projectModel;
            return this;
        }

        public GetProjectResult build() {
            return new GetProjectResult(projectModel);
        }
    }
}

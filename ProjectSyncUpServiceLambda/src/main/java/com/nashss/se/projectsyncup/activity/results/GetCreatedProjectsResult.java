package com.nashss.se.projectsyncup.activity.results;
import com.nashss.se.projectsyncup.models.ProjectModel;

/**
 * The `GetCreatedProjectsResult` class represents the result of a request to retrieve a list of created projects.
 * It contains the `ProjectModel` object that holds the details of the project.
 *
 */
public class GetCreatedProjectsResult {
    /**
     * The `ProjectModel` object that holds the details of the project.
     */
    private final ProjectModel project;

    /**
     * Constructs a `GetCreatedProjectsResult` object with the given `ProjectModel` instance.
     *
     * @param project the `ProjectModel` object
     */
    private GetCreatedProjectsResult(ProjectModel project) {
        this.project = project;
    }

    /**
     * Returns the `ProjectModel` object.
     *
     * @return the `ProjectModel` object
     */
    public ProjectModel getProject() {

        return project;
    }

    /**
     * Returns a string representation of this `GetProjectResult` object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetCreatedProjectsResult{" +
                "project=" + project +
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

        public GetCreatedProjectsResult build() {

            return new GetCreatedProjectsResult(projectModel);
        }
    }
}

package com.nashss.se.projectsyncup.activity.results;
import com.nashss.se.projectsyncup.models.ProjectModel;
import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.List;

/**
 * The `GetCreatedProjectsResult` class represents the result of a request to retrieve a list of created projects.
 * It contains the `ProjectModel` object that holds the details of the project.
 *
 */
public class GetCreatedProjectsResult {
    /**
     * The `ProjectModel` object that holds the details of the project.
     */
    private final List<ProjectModel> createdProjects;

    /**
     * Constructs a `GetCreatedProjectsResult` object with the given `ProjectModel` instance.
     *
     * @param createdProjects the `ProjectModel` object
     */
    private GetCreatedProjectsResult(List<ProjectModel> createdProjects) {
        this.createdProjects = createdProjects;
    }

    /**
     * Returns the `ProjectModel` object.
     *
     * @return the `ProjectModel` object
     */
    public List<ProjectModel> getCreatedProjects() {
        return createdProjects;
    }

    /**
     * Returns a string representation of this `GetProjectResult` object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "GetCreatedProjectsResult{" +
                "createdProjects=" + createdProjects +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ProjectModel> createdProjects;

        public Builder withCreatedProjects(List<ProjectModel> createdProjects) {
            this.createdProjects = createdProjects;
            return this;
        }


        public GetCreatedProjectsResult build() {
            return new GetCreatedProjectsResult(createdProjects);
        }


    }
}

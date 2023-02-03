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
    private final ProjectModel projectModel;

    /**
     * Private constructor to enforce the use of the Builder class.
     *
     * @param projectModel The projectModel that was created.
     */
    private CreateProjectResult(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    /**
     * Returns the projectModel that was created.
     *
     * @return The projectModel that was created.
     */
    public ProjectModel getProjectModel() {
        return projectModel;
    }
}

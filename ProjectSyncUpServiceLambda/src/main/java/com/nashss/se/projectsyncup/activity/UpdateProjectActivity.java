package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.UpdateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.UpdateProjectResult;
import com.nashss.se.projectsyncup.converters.ModelConverter;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


/**
 * Implementation of the UpdateProjectActivity for the ProjectSyncUpService's UpdateProject API.
 *
 * This API allows the customer to update their saved project's information.
 */
public class UpdateProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;

    /**
     * Instantiates a new UpdateProjectActivity object.
     *
     * @param projectDao ProjectDao to access the projects table.
     */
    @Inject
    public UpdateProjectActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the incoming request by retrieving the project, updating it,
     * and persisting the project.
     *
     * It then returns the updated project.
     *
     *
     * @param updateProjectRequest request object containing the project ID, project name, and project Description
     *                              associated with it
     * @return updatePlaylistResult result object containing the API defined {@link ProjectModel}
     */
    public UpdateProjectResult handleRequest(final UpdateProjectRequest updateProjectRequest) {
        log.info("Received UpdateProjectRequest {}", updateProjectRequest);

        Project project = projectDao.getProject(updateProjectRequest.getProjectId());

        if (updateProjectRequest.getProjectName() != null && !updateProjectRequest.getProjectName().equals(project.getProjectName())) {
            project.setProjectName(updateProjectRequest.getProjectName());
        }

        if (updateProjectRequest.getProjectDescription() != null && !updateProjectRequest.getProjectDescription().equals(project.getProjectDescription())) {
            project.setProjectDescription(updateProjectRequest.getProjectDescription());
        }

        if (updateProjectRequest.getProjectStatus() != null && !updateProjectRequest.getProjectStatus().equals(project.getProjectStatus())) {
            project.setProjectStatus(updateProjectRequest.getProjectStatus());
        }

        project = projectDao.saveProject(project);

        return UpdateProjectResult.builder()
                .withProject(new ModelConverter().toProjectModel(project))
                .build();
    }

}


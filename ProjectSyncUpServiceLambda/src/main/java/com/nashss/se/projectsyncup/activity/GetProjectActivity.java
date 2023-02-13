package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.GetProjectRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectResult;
import com.nashss.se.projectsyncup.converters.ModelConverter;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;

import javax.inject.Inject;

/**
 * Implementation of the GetProjectActivity for the ProjectSyncUpService's GetProject API.
 * <p>
 * This API allows a member to get a get one of their saved projects.
 */

public class GetProjectActivity {

    private final ProjectDao projectDao;

    /**
     * Instantiates a new GetProjectActivity object.
     *
     * @param projectDao ProjectDao to access the projects table.
     */
    @Inject
    public GetProjectActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the incoming request by retrieving the project from the database.
     *
     * It then returns the project.
     *
     * If the project does not exist, this should throw a ProjectNotFoundException.
     *
     * @param getProjectRequest request object containing the project ID
     * @return getProjectResult result object containing the API defined {@link ProjectModel}
     */

    public GetProjectResult handleRequest(final GetProjectRequest getProjectRequest) {

        String requestedId = getProjectRequest.getProjectId();
        Project project = projectDao.getProject(requestedId);
        ProjectModel projectModel = new ModelConverter().toProjectModel(project);

        return GetProjectResult.builder()
                .withProjectModel(projectModel)
                .build();

    }

}

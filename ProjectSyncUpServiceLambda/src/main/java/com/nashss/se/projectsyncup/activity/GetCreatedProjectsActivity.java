package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.GetCreatedProjectsRequest;
import com.nashss.se.projectsyncup.activity.requests.GetProjectTasksRequest;
import com.nashss.se.projectsyncup.activity.results.GetCreatedProjectsResult;
import com.nashss.se.projectsyncup.activity.results.GetProjectTasksResult;
import com.nashss.se.projectsyncup.converters.ModelConverter;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;
import com.nashss.se.projectsyncup.models.TaskModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the GetProjectTasksActivity for the ProjectSyncUpService's GetProjectTasks API.
 *
 * This API allows the user to get the list of tasks of a saved project.
 */

public class GetCreatedProjectsActivity {

    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;

    /**
     * Instantiates a new GetProjectTasksActivity object.
     *
     * @param projectDao ProjectDao to access the project table.
     */
    @Inject
    public GetCreatedProjectsActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the incoming request by retrieving the project from the database.
     *
     * It then returns the project's task list.
     *
     * If the project does not exist, this should throw a ProjectNotFoundException.
     *
     * @param getCreatedProjectsRequest request object containing the project ID
     * @return getCreatedProjectsResult result object containing the member's list of CreatedProjectsAPI defined {@link ProjectModel}s
     */
    public GetCreatedProjectsResult handleRequest(final GetCreatedProjectsRequest getCreatedProjectsRequest) {
        log.info("Received GetCreatedProjectsRequest {}", getCreatedProjectsRequest);

        List<Project> createdProjects = projectDao.getAllCreatedProjects(getCreatedProjectsRequest.getCreatedById());

        List<ProjectModel> projectModels = new ArrayList<>();
        for (Project project : createdProjects) {
            ProjectModel projectModel = ModelConverter.toProjectModel(project);
            projectModels.add(projectModel);
        }

        return GetCreatedProjectsResult.builder()
                .withCreatedProjects(projectModels)
                .build();
    }

}

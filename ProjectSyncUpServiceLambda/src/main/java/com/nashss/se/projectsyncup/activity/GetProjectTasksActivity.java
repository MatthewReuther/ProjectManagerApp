package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.GetProjectTasksRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectTasksResult;
import com.nashss.se.projectsyncup.converters.ModelConverter;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.TaskModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Implementation of the GetProjectTasksActivity for the ProjectSyncUpService's GetProjectTasks API.
 *
 * This API allows the user to get the list of tasks of a saved project.
 */

public class GetProjectTasksActivity {

    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;

    /**
     * Instantiates a new GetProjectTasksActivity object.
     *
     * @param projectDao ProjectDao to access the project table.
     */
    @Inject
    public GetProjectTasksActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the incoming request by retrieving the project from the database.
     *
     * It then returns the project's task list.
     *
     * If the project does not exist, this should throw a ProjectNotFoundException.
     *
     * @param getProjectTasksRequest request object containing the project ID
     * @return getProjectTasksResult result object containing the project's list of API defined {@link TaskModel}s
     */
    public GetProjectTasksResult handleRequest(final GetProjectTasksRequest getProjectTasksRequest) {
        log.info("Received GetProjectTasksRequest {}", getProjectTasksRequest);

        Project project = projectDao.getProject(getProjectTasksRequest.getProjectId());
        List<TaskModel> taskModels = new ModelConverter().toTaskModelList(project.getProjectTasks());

        return GetProjectTasksResult.builder()
                .withTaskList(taskModels)
                .build();
    }

}

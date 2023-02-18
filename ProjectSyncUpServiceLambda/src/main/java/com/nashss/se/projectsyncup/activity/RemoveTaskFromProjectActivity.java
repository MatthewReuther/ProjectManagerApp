package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.RemoveTaskFromProjectRequest;
import com.nashss.se.projectsyncup.activity.requests.RemoveTaskFromProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class RemoveTaskFromProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;


    /**
     *
     * @param projectDao PlaylistDao to access the project table.
     */
    @Inject
    public RemoveTaskFromProjectActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     *
     * @param request request containing information on a task to be removed
     * @return the task being removed from the project
     */
    public RemoveTaskFromProjectResult handleRequest(final RemoveTaskFromProjectRequest request) {
        log.info("Received RemoveTaskFromProjectRequest {} ", request);

        String taskId = request.getTaskId();
        String projectId = request.getprojectId();

        Project project = projectDao.getProject(projectId);

        projectDao.saveProject(project);

        return RemoveTaskFromProjectResult.builder()
                .build();
    }

}

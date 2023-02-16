package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;

import com.nashss.se.projectsyncup.converters.ModelConverter;

import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;

import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.models.ProjectModel;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Implementation of the CreateProjectActivity for the ProjectSyncUpService's CreateProject API.
 *
 * This API allows the member to create a new project with no tasks.
 */
public class CreateProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;

    /**
     * Instantiates a new CreateProjectActivity object.
     *
     * @param projectDao ProjectDao to access the projects table.
     */
    @Inject
    public CreateProjectActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the incoming request by persisting a new project
     * with the provided project name and user ID from the request.
     *
     * It then returns the newly created project.
     *
     *
     * @param createProjectRequest request object containing the project name and user ID  associated with it
     * @return createProjectResult result object containing the API defined {@link ProjectModel}
     */

    public CreateProjectResult handleRequest(final CreateProjectRequest createProjectRequest) {
        log.info("Received CreateProjectRequest {} ", createProjectRequest);

//        List<Task> projectTasks = null;
//        if (createProjectRequest.getProjectTasks() != null) {
//            projectTasks = new HashSet<>(createProjectRequest.getProjectTasks());
//        }

        //Set<String> projectMembers = null;
//        if (createProjectRequest.getProjectMembers() != null) {
//            projectMembers = new HashSet<>(createProjectRequest.getProjectMembers());
//        }

        Project newProject = new Project();
        newProject.setProjectId(ProjectSyncUpServiceUtils.generateUniqueId());
        newProject.setProjectName(createProjectRequest.getProjectName());
        newProject.setProjectDescription(createProjectRequest.getProjectDescription());
        newProject.setProjectStatus(createProjectRequest.getProjectStatus());
        newProject.setCreatedById(createProjectRequest.getCreatedById());
        newProject.setProjectTasks(new ArrayList<>());
        newProject.setProjectMembers(null);

        projectDao.saveProject(newProject);

        ProjectModel projectModel = new ModelConverter().toProjectModel(newProject);
        return CreateProjectResult.builder()
                .withProject(projectModel)
                .build();
    }

}

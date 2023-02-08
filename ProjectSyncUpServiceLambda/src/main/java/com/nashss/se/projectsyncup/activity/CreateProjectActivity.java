package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;
import com.nashss.se.projectsyncup.converters.ModelConverter;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;

import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */

public class CreateProjectActivity {

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
     * <p>
     * It then returns the newly created project.
     * <p>
     *
     * @param createProjectRequest request object containing the project name and user ID  associated with it
     * @return createProjectResult result object containing the API defined {@link ProjectModel}
     */

    public CreateProjectResult handleRequest(final CreateProjectRequest createProjectRequest) {

        Set<String> projectTasks = null;
        if (createProjectRequest.getProjectTasks() != null) {
            projectTasks = new HashSet<>(createProjectRequest.getProjectTasks());
        }

        Set<String> projectMembers = null;
        if (createProjectRequest.getProjectMembers() != null) {
            projectMembers = new HashSet<>(createProjectRequest.getProjectMembers());
        }

        Project newProject = new Project();
        newProject.setProjectId(ProjectSyncUpServiceUtils.generateProjectId());
        newProject.setProjectName(createProjectRequest.getProjectName());
        newProject.setProjectDescription(createProjectRequest.getProjectDescription());
        newProject.setProjectStatus(createProjectRequest.getProjectStatus());
        newProject.setCreatedById(createProjectRequest.getCreatedById());
        newProject.setTasks(projectTasks);
        newProject.setProjectMembers(projectMembers);
        //newProject.setTasks(new HashSet<>());
        //newProject.setProjectMembers(new HashSet<>());

        projectDao.saveProject(newProject);


        ProjectModel projectModel = new ModelConverter().toProjectModel(newProject);
        return CreateProjectResult.builder()
                .withProject(projectModel)
                .build();
    }

}

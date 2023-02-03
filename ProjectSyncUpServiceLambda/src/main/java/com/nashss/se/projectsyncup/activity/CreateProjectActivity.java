package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.models.ProjectModel;

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
        CreateProjectResult createProjectResult = null;
        return createProjectResult;
    }

}

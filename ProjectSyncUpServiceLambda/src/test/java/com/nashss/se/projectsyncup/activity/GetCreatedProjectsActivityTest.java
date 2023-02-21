package com.nashss.se.projectsyncup.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import com.nashss.se.projectsyncup.activity.requests.GetCreatedProjectsRequest;
import com.nashss.se.projectsyncup.activity.results.GetCreatedProjectsResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;
import org.junit.jupiter.api.Test;

public class GetCreatedProjectsActivityTest {

    @Test
    public void handleRequest_getCreatedProjects_returnsListOfCreatedProjects() {
        // Create a mock project DAO
        ProjectDao projectDao = mock(ProjectDao.class);

        // Create a list of projects created by the user
        List<Project> createdProjects = new ArrayList<Project>();
        Project project1 = new Project();
        project1.setProjectId("1");
        project1.setCreatedById("user1");
        project1.setProjectName("Project 1");
        createdProjects.add(project1);
        Project project2 = new Project();
        project2.setProjectId("2");
        project2.setCreatedById("user1");
        project2.setProjectName("Project 2");
        createdProjects.add(project2);

        // Configure the mock DAO to return the list of created projects
        when(projectDao.getAllCreatedProjects("user1")).thenReturn(createdProjects);

        // Create a GetCreatedProjectsRequest with the user's ID
        GetCreatedProjectsRequest request = new GetCreatedProjectsRequest("user1");

        // Create a GetCreatedProjectsActivity object and call its handleRequest method
        GetCreatedProjectsActivity activity = new GetCreatedProjectsActivity(projectDao);
        GetCreatedProjectsResult result = activity.handleRequest(request);

        // Verify that the result contains the expected list of projects
        List<ProjectModel> projectModels = result.getCreatedProjects();
        assertEquals(2, projectModels.size());
        assertEquals("1", projectModels.get(0).getProjectId());
        assertEquals("Project 1", projectModels.get(0).getProjectName());
        assertEquals("2", projectModels.get(1).getProjectId());
        assertEquals("Project 2", projectModels.get(1).getProjectName());

    }

}

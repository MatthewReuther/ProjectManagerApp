package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.UpdateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.UpdateProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateProjectActivityTest {
    @Mock
    private ProjectDao projectDao;

    private UpdateProjectActivity updateProjectActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateProjectActivity = new UpdateProjectActivity(projectDao);
    }

    @Test
    public void handleRequest_updateStatus_updatesProjectStatus() {
        // GIVEN
        String expectedId = "projectId";
        String expectedName = "Expected Project Name";
        String expectedDescription = "Expected Description";
        String newProjectStatus = "Completed";

        UpdateProjectRequest request = UpdateProjectRequest.builder()
                .withProjectId(expectedId)
                .withProjectName(expectedName)
                .withProjectDescription(expectedDescription)
                .withProjectStatus(newProjectStatus)
                .build();

        Project originalProject = new Project();
        originalProject.setProjectName(expectedName);
        originalProject.setProjectDescription(expectedDescription);
        originalProject.setProjectStatus("In Progress");

        when(projectDao.getProject(expectedId)).thenReturn(originalProject);
        when(projectDao.saveProject(originalProject)).thenReturn(originalProject);

        // WHEN
        UpdateProjectResult result = updateProjectActivity.handleRequest(request);

        // THEN
        assertEquals(expectedName, result.getProject().getProjectName());
        assertEquals(expectedDescription, result.getProject().getProjectDescription());
        assertEquals(newProjectStatus, result.getProject().getProjectStatus());
    }
}
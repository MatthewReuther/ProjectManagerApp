package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateProjectActivityTest {
    @Mock
    private ProjectDao projectDao;

    private CreateProjectActivity createProjectActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createProjectActivity = new CreateProjectActivity(projectDao);
    }

    @Test
    public void handleRequest_withTasks_createsAndSavesProjectWithTasks() {

        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedDescription = "expectedDescription";
        String expectedStatus = "expectedStatus";
        String expectedCreatedId = "expectedCreatedById";
        List<String> expectedTasks = List.of("task 1");
        List<String> expectedMembers = List.of("member");

        CreateProjectRequest request = CreateProjectRequest.builder()
            .withProjectName(expectedName)
            .withProjectDescription(expectedDescription)
            .withCreatedById(expectedCreatedId)
            .withProjectStatus(expectedStatus)

            .build();

        // WHEN
        CreateProjectResult result = createProjectActivity.handleRequest(request);

        // THEN
        verify(projectDao).saveProject(any(Project.class));

        assertNotNull(result.getProject().getProjectId());
        assertEquals(expectedName, result.getProject().getProjectName());
        assertEquals(expectedDescription, result.getProject().getProjectDescription());
        assertEquals(expectedStatus, result.getProject().getProjectStatus());
        assertEquals(expectedCreatedId, result.getProject().getCreatedById());


    }
}

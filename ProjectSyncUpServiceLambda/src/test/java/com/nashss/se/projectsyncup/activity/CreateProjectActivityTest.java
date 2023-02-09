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

curl -X POST \
http://127.0.0.1:3000/projects \
    -H 'Authorization: Bearer eyJraWQiOiJJV1Y0N1BkbTdRN091b1pDdm5YdmVLVGZVQ1wvcTFwNkUxNGJQcVJydzV4dz0iLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiV3RiN1RaV1dxRXU0YVZuTTZNbmdnZyIsInN1YiI6IjdiMThiYjM5LTFkY2MtNDc4Zi1iODVjLWI4ODUyMTAxMGVkZCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0yLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMl90YWN4RkNLMEkiLCJjb2duaXRvOnVzZXJuYW1lIjoiN2IxOGJiMzktMWRjYy00NzhmLWI4NWMtYjg4NTIxMDEwZWRkIiwib3JpZ2luX2p0aSI6ImU0YmE0N2QxLWNiMzItNGEzMC05NzZjLWVjMjEwZWEyM2I4ZiIsImF1ZCI6IjVlZG1mOGQyb2M2NGhkb2xrZ2hjdWQzNWd2IiwiZXZlbnRfaWQiOiI3NjlkZDFiMi1lMzViLTRiZjEtOTk3NC1lZTUxZjNjMTg1MjAiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY3NTk2MjI3MCwibmFtZSI6Ik1hdHRoZXciLCJleHAiOjE2NzU5NjU4NzAsImlhdCI6MTY3NTk2MjI3MCwianRpIjoiNjdlMDEzOTMtODZkOS00NjYwLTk1MGEtOTk1ZjNmMGQ3Y2Y2IiwiZW1haWwiOiJtYXR0aGV3LnJldXRoZXJAZ21haWwuY29tIn0.JyCXkoGkWcT-HIaQzLTG1v4e781BEHmTmLOAFP5tzeW_kC-zmasvpKf4TzbgfX3HZk5lOVaQ5zEZmcC-AI4NwY0EmItiO0jo-9-BN_P6VKsjJpRu9R2bLsGMeZcqSkr1qTMIVBzZisJOC8T-4myx0Z8P5hiIq1Y8vtg06w2lr4-xc7b6kgQcbAcB9g_Z1AYzirznilP1YMPRxKQufYUNlsuj5CM5AUzhq-lE2p7eXl2n3bAiLnaHYthQxabJfeSG_jaAxLugp1ScqpwJiYOwNdrS27dpPb1uMjPg8iphpIklMOLIOZOLTALTfoDIqxODKakOHRSge_szmh9i-tv_qA' \
    -d '{
    "projectName": "Test Project",
    "projectDescription": "This is a test project",
    "projectStatus": "ACTIVE",
    "createdById": "1234",
    "projectTasks": [
    "Task 1",
    "Task 2"
    ],
    "projectMembers": [
    "User 1",
    "User 2"
    ]
}'

    @Test
    public void handleRequest_withTasks_createsAndSavesPlaylistWithTasks() {

        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedDescription = "expectedDescription";
        String expectedStatus = "expectedStatus";
        String expectedCreatedId = "expectedCreatedById";
        List<String> expectedTasks = List.of("task");
        List<String> expectedMembers = List.of("member");

        CreateProjectRequest request = CreateProjectRequest.builder()
            .withProjectName(expectedName)
            .withProjectDescription(expectedDescription)
            .withCreatedById(expectedCreatedId)
            .withProjectStatus(expectedStatus)
            .withProjectTasks(expectedTasks)
            .withProjectMembers(expectedMembers)
            .build();

        // WHEN
        CreateProjectResult result = createProjectActivity.handleRequest(request);

        // THEN
        verify(projectDao).saveProject(any(Project.class));

        assertNotNull(result.getProjectModel().getId());
        assertEquals(expectedName, result.getProjectModel().getName());
        assertEquals(expectedDescription, result.getProjectModel().getDescription());
        assertEquals(expectedStatus, result.getProjectModel().getStatus());
        assertEquals(expectedCreatedId, result.getProjectModel().getCreatedBy());
        assertEquals(expectedTasks, result.getProjectModel().getTasks());
        assertEquals(expectedMembers, result.getProjectModel().getProjectMembers());

    }
}

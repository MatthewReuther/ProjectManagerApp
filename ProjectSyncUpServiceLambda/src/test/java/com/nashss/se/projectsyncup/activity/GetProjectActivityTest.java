package com.nashss.se.projectsyncup.activity;

import com.google.common.collect.Sets;
import com.nashss.se.projectsyncup.activity.requests.GetProjectRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetProjectActivityTest {

    @Mock
    private ProjectDao projectDao;

    private GetProjectActivity getProjectActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getProjectActivity = new GetProjectActivity(projectDao);
    }

    @Test
    public void handleRequest_savedProjectFound_returnsProjectModelInResult() {

        // GIVEN
        String expectedId = ProjectSyncUpServiceUtils.generateProjectId();
        String expectedProjectName = "Test Project";
        String expectedProjectDescription = "This is a test project.";
        String expectedProjectStatus = "Active";
        String expectedCreatedById = "user1";
        List<String> expectedProjectTasks = Arrays.asList("Task 2", "Task 1");
        List<String> expectedProjectMembers = Arrays.asList("user2", "user3");

        Project project = new Project();
        project.setProjectId(expectedId);
        project.setProjectName(expectedProjectName);
        project.setProjectDescription(expectedProjectDescription);
        project.setProjectStatus(expectedProjectStatus);
        project.setCreatedById(expectedCreatedById);
        project.setTasks(Sets.newHashSet(expectedProjectTasks));
        project.setProjectMembers(Sets.newHashSet(expectedProjectMembers));

        when(projectDao.getProject(expectedId)).thenReturn(project);

        GetProjectRequest request = GetProjectRequest.builder()
                .withProjectId(expectedId)
                .build();

        // WHEN
        GetProjectResult result = getProjectActivity.handleRequest(request);

        // THEN
        assertEquals(expectedId, result.getProjectModel().getId());
        assertEquals(expectedProjectName, result.getProjectModel().getName());
        assertEquals(expectedProjectDescription, result.getProjectModel().getDescription());
        assertEquals(expectedProjectStatus, result.getProjectModel().getStatus());
        assertEquals(expectedCreatedById, result.getProjectModel().getCreatedBy());
        assertEquals(expectedProjectTasks, result.getProjectModel().getTasks());
        assertEquals(expectedProjectMembers, result.getProjectModel().getProjectMembers());
    }

}

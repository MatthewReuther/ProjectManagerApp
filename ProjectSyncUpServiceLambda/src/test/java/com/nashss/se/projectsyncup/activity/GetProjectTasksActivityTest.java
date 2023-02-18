package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.GetProjectTasksRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectTasksResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.test.helper.ProjectTaskTestHelper;
import com.nashss.se.projectsyncup.test.helper.ProjectTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetProjectTasksActivityTest {

    @Mock
    private ProjectDao projectDao;

    private GetProjectTasksActivity getProjectTasksActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getProjectTasksActivity = new GetProjectTasksActivity(projectDao);
    }

    @Test
    void handleRequest_playlistExistsWithSongs_returnsSongsInPlaylist() {
        // GIVEN
        Project project = ProjectTestHelper.generateProjectWithTasks(3);
        String projectId = project.getProjectId();
        GetProjectTasksRequest request = GetProjectTasksRequest.builder()
                .withProjectId(projectId)
                .build();

        when(projectDao.getProject(projectId)).thenReturn(project);

        // WHEN
        GetProjectTasksResult result = getProjectTasksActivity.handleRequest(request);

        // THEN
        ProjectTaskTestHelper.assertProjectTasksEqualTaskModels(project.getProjectTasks(), result.getTaskList());
    }

}

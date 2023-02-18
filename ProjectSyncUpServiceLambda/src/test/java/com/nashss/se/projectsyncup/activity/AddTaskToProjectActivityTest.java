package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.AddTaskToProjectRequest;
import com.nashss.se.projectsyncup.activity.results.AddTaskToProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.TaskDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.test.helper.ProjectTaskTestHelper;
import com.nashss.se.projectsyncup.test.helper.ProjectTestHelper;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddTaskToProjectActivityTest {

    @Mock
    private ProjectDao projectDao;

    @Mock
    private TaskDao taskDao;

    private AddTaskToProjectActivity addTaskToProjectActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        addTaskToProjectActivity = new AddTaskToProjectActivity(taskDao, projectDao);
    }

    @Test
    public void handleRequest_addTask_createsAndSavesTaskInTaskDao() {

        String taskName = "Task Name";
        String taskDescription = "Task Description";
        String taskDueDate = "Task Due Date";
        String taskAssignedUser = "Task Assignment User";
        String projectId = ProjectSyncUpServiceUtils.generateUniqueId();

        Project project = new Project();
        project.setProjectId(projectId);

        //WHEN
        when(projectDao.getProject(projectId)).thenReturn(project);

        AddTaskToProjectRequest request = new AddTaskToProjectRequest.Builder()
                .withTaskName(taskName)
                .withTaskDescription(taskDescription)
                .withTaskDueDate(taskDueDate)
                .withTaskAssignedUser(taskAssignedUser)
                .withProjectId(projectId)
                .build();

        //WHEN
        AddTaskToProjectResult result = addTaskToProjectActivity.handleRequest(request);

        //THEN
        verify(taskDao).saveTask(any(Task.class));

    }

}

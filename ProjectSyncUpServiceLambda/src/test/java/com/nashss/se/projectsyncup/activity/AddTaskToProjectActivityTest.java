package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.TaskDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.test.helper.ProjectTaskTestHelper;
import com.nashss.se.projectsyncup.test.helper.ProjectTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        addTaskToProjectActivity = new AddTaskToProjectActivity(projectDao, taskDao);
    }


//    @Test
//    void handleRequest_validRequest_addsTaskToProject() {
//        // GIVEN
//        // a non-empty playlist
//        Project originalProject = ProjectTestHelper.generateProjectWithTasks(3);
//        String projectId = originalProject.getProjectId();
//        String projectName = originalProject.getProjectName();
//        String projectDescription = originalProject.getProjectDescription();
//        String projectStatus = originalProject.getProjectStatus();
//        String projectCreatedBy = originalProject.getCreatedById();
//
//        // the new task to add to the playlist
//        Task taskToAdd = ProjectTaskTestHelper.generateTask(2);
//        String taskId = taskToAdd.getTaskId();
//        String taskName = taskToAdd.getTaskName();
//        String taskDescription = taskToAdd.getTaskDescription();
//        String taskAssignedUser = taskToAdd.getTaskAssignedUser();
//
//        when(projectDao.getProject(projectId)).thenReturn(originalProject);
//        when(projectDao.saveProject(originalProject)).thenReturn(originalProject);
//
//    }

}

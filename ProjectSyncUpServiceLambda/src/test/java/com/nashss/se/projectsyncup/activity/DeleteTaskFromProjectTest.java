package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.DeleteTaskFromProjectRequest;
import com.nashss.se.projectsyncup.activity.results.DeleteTaskFromProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.TaskDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteTaskFromProjectTest {

    @Mock
    private ProjectDao projectDao;

    @Mock
    private TaskDao taskDao;

    private DeleteTaskFromProjectActivity deleteTaskFromProjectActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        deleteTaskFromProjectActivity = new DeleteTaskFromProjectActivity(projectDao, taskDao);
    }

    @Test
    public void handleRequest_deleteTask_deleteTaskFromProject() {
        String taskId = "123";
        String projectId = "01";

        Task task = new Task();
        task.setTaskId(taskId);

        List<Task> projectTaskList = new ArrayList<>();
        projectTaskList.add(task);

        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectTasks(projectTaskList);

        when(projectDao.getProject(projectId)).thenReturn(project);

        DeleteTaskFromProjectRequest request = DeleteTaskFromProjectRequest.builder()
                .withTaskId(taskId)
                .withProjectId(project.getProjectId())
                .build();

        DeleteTaskFromProjectResult result = deleteTaskFromProjectActivity.handleRequest(request);

        verify(projectDao).getProject(projectId);
        verify(projectDao).saveProject(project);
        System.out.println(result);
        assertTrue(project.getProjectTasks().isEmpty());
        System.out.println(project.getProjectTasks());
    }

    @Test
    public void handleRequest_deleteTaskFromListOfTasks_deleteTaskFromProject() {

        String taskIdToRemove = "123";
        String projectId = "01";

        Task task1 = new Task();
        task1.setTaskId("456");

        Task task2 = new Task();
        task2.setTaskId(taskIdToRemove);

        Task task3 = new Task();
        task3.setTaskId("789");

        List<Task> projectTaskList = new ArrayList<>();
        projectTaskList.add(task1);
        projectTaskList.add(task2);
        projectTaskList.add(task3);

        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectTasks(projectTaskList);

        when(projectDao.getProject(projectId)).thenReturn(project);

        DeleteTaskFromProjectRequest request = DeleteTaskFromProjectRequest.builder()
                .withTaskId(taskIdToRemove)
                .withProjectId(project.getProjectId())
                .build();

        DeleteTaskFromProjectResult result = deleteTaskFromProjectActivity.handleRequest(request);

        verify(projectDao).getProject(projectId);
        verify(projectDao).saveProject(project);
        assertTrue(project.getProjectTasks().size() == 2);
        assertFalse(project.getProjectTasks().contains(task2));
        System.out.println(project.getProjectTasks());
    }
}


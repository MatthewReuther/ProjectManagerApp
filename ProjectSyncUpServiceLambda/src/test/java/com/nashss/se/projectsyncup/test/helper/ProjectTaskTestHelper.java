package com.nashss.se.projectsyncup.test.helper;

import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.models.TaskModel;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTaskTestHelper {

    private ProjectTaskTestHelper() {
    }


    public static Task generateTask(int sequenceNumber) {
        Task projectTask = new Task();
        projectTask.setTaskId(projectTask.getProjectId());
        projectTask.setTaskName("taskName" + sequenceNumber);
        projectTask.setTaskDescription("taskDescription" + sequenceNumber);
        projectTask.setTaskDueDate("taskDueDate" + sequenceNumber);
        projectTask.setTaskAssignedUser("taskAssignedUser" + sequenceNumber);
        projectTask.setProjectId(projectTask.getProjectId());
        return projectTask;
    }

    public static void assertProjectTasksEqualTaskModels(List<Task> projectTasks, List<TaskModel> taskModels) {
        assertEquals(projectTasks.size(),
                taskModels.size(),
                String.format("Expected project tasks (%s) and task models (%s) to match",
                        projectTasks,
                        taskModels));
        for (int i = 0; i < projectTasks.size(); i++) {
            assertProjectTaskEqualsTaskModel(
                    projectTasks.get(i),
                    taskModels.get(i),
                    String.format("Expected %dth project task (%s) to match corresponding task model (%s)",
                            i,
                            projectTasks.get(i),
                            taskModels.get(i)));
        }
    }

    public static void assertProjectTaskEqualsTaskModel(Task projectTask, TaskModel taskModel) {
        String message = String.format("Expected project task %s to match task model %s", projectTask, taskModel);
        assertProjectTaskEqualsTaskModel(projectTask, taskModel, message);
    }

    public static void assertProjectTaskEqualsTaskModel(Task projectTask, TaskModel taskModel, String message) {

        assertEquals(projectTask.getTaskId(), taskModel.getTaskId(), message);
        assertEquals(projectTask.getTaskName(), taskModel.getTaskName(), message);
        assertEquals(projectTask.getTaskDescription(), taskModel.getTaskDescription(), message);
        assertEquals(projectTask.getTaskDueDate(), taskModel.getTaskDueDate(), message);
        assertEquals(projectTask.getTaskAssignedUser(), taskModel.getTaskAssignedUser(), message);
        assertEquals(projectTask.getProjectId(), taskModel.getProjectId(), message);

    }

}

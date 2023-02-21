package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.DeleteTaskFromProjectRequest;
import com.nashss.se.projectsyncup.activity.results.DeleteTaskFromProjectResult;
import com.nashss.se.projectsyncup.converters.ModelConverter;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.TaskDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.models.TaskModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class DeleteTaskFromProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    /**
     * @param projectDao ProjectDao to access the project table.
     * @param taskDao TaskDao to access the task table
     */
    @Inject
    public DeleteTaskFromProjectActivity(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao = taskDao;
    }

    /**
     *
     * @param deleteTaskFromProjectRequest deleteTaskFromProjectRequest containing information on a task to be removed
     * @return the task being removed from the project
     */

    public DeleteTaskFromProjectResult handleRequest(final DeleteTaskFromProjectRequest deleteTaskFromProjectRequest) {
        log.info("Received DeleteTaskFromProjectResult {} ", deleteTaskFromProjectRequest);

        String taskId = deleteTaskFromProjectRequest.getTaskId();
        String projectId = deleteTaskFromProjectRequest.getProjectId();

        // Get the project object
        Project project = projectDao.getProject(projectId);

        // Get the list of tasks from the project
        List<Task> taskList = project.getProjectTasks();

        // Find the task to delete
        Task taskToDelete = null;
        for (Task task : taskList) {
            if (task.getTaskId().equals(taskId)) {
                taskToDelete = task;
                break;
            }
        }

        if (taskToDelete != null) {
            // Delete the task from the database
            taskDao.deleteTask(taskToDelete);

            // Remove the task from the list of project tasks
            taskList.remove(taskToDelete);

            // Update the project object with the modified list of tasks
            project.setProjectTasks(taskList);

            // Save the project to the database
            projectDao.saveProject(project);
        }

        // Convert the list of remaining tasks in the project to a list of task models
        List<TaskModel> taskModelList = new ModelConverter().toTaskModelList(project.getProjectTasks());

        // Return the result with the updated project
        return DeleteTaskFromProjectResult.builder()
                .withProject(taskModelList)
                .build();
    }


}

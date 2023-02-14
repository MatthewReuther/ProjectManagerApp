package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.AddTaskToProjectRequest;
import com.nashss.se.projectsyncup.activity.results.AddTaskToProjectResult;

import com.nashss.se.projectsyncup.converters.ModelConverter;

import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.TaskDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;

import com.nashss.se.projectsyncup.models.TaskModel;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Implementation of the AddTaskToProjectActivity for the ProjectSyncUpService's AddTaskToProject API.
 *
 * This API allows the member (project manager) to add a task to their project.
 */
public class AddTaskToProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    /**
     * Instantiates a new AddTaskToProjectActivity object.
     *
     * @param projectDao ProjectDao to access the project table.
     * @param taskDao TaskDao to access the project_track table.
     */
    @Inject
    public AddTaskToProjectActivity(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao = taskDao;
    }

    /**
     * This method handles the incoming request by adding task to a project
     * and persisting the updated project.
     *
     * It then returns the updated tasks list of the project.
     *
     * If the project does not exist, this should throw a ProjectNotFoundException.
     *
     * @param addTaskToProjectRequest request object containing the project ID to retrieve the task data
     * @return addTaskToProjectResult result object containing the project's updated list of tasks
     *                                 API defined {@link TaskModel}s
     */
    public AddTaskToProjectResult handleRequest(final AddTaskToProjectRequest addTaskToProjectRequest) {
        log.info("Received AddTaskToProjectRequest {} ", addTaskToProjectRequest);

        Task newTask = new Task();
        newTask.setTaskId(ProjectSyncUpServiceUtils.generateUniqueId());
        newTask.setTaskName(addTaskToProjectRequest.getTaskName());
        newTask.setTaskDescription(addTaskToProjectRequest.getTaskDescription());
        newTask.setTaskDueDate(addTaskToProjectRequest.getTaskDueDate());
        newTask.setTaskAssignedUser(addTaskToProjectRequest.getTaskAssignedUser());

        Project project = projectDao.getProject(addTaskToProjectRequest.getProjectId());

        Set<String> taskList = project.getProjectTasks();

        if (taskList == null) {
            taskList = new HashSet<>();
        }

        String newTaskName = newTask.getTaskId() + ", " + newTask.getTaskName();

        taskList.add(newTaskName);
        project.setProjectTasks(taskList);
        projectDao.saveProject(project);

        TaskModel taskModel = new ModelConverter().toTaskModel(newTask);

        return AddTaskToProjectResult.builder()
                .withTaskModel(taskModel)
                .build();
    }

}



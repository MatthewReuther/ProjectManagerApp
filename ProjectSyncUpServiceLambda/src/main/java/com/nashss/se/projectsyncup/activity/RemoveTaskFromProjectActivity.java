package com.nashss.se.projectsyncup.activity;

import com.nashss.se.projectsyncup.activity.requests.RemoveTaskFromProjectRequest;
import com.nashss.se.projectsyncup.activity.results.RemoveTaskFromProjectResult;
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

public class RemoveTaskFromProjectActivity {
    private final Logger log = LogManager.getLogger();
    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    /**
     * @param projectDao ProjectDao to access the project table.
     * @param taskDao TaskDao to access the task table
     */
    @Inject
    public RemoveTaskFromProjectActivity(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao = taskDao;
    }

    /**
     *
     * @param request request containing information on a task to be removed
     * @return the task being removed from the project
     */
    public RemoveTaskFromProjectResult handleRequest(final RemoveTaskFromProjectRequest request) {
        log.info("Received RemoveTaskFromProjectRequest {} ", request);

        String taskId = request.getTaskId();
        String projectId = request.getProjectId();


        Project project = projectDao.getProject(projectId);
        List<Task> taskList = project.getProjectTasks();

        taskList.removeIf(task -> task.getTaskId().equals(taskId) &&
                task.getProjectId().equals(projectId));

        project.setProjectTasks(taskList);
        projectDao.saveProject(project);

        List<TaskModel> taskModelList = new ModelConverter().toTaskModelList(project.getProjectTasks());
        return RemoveTaskFromProjectResult.builder()
                .withProject(taskModelList)
                .build();
    }

}

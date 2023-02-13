package com.nashss.se.projectsyncup.converters;

import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.models.ProjectModel;
import com.nashss.se.projectsyncup.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Project} into a {@link ProjectModel} representation.
     *
     * @param project the project to convert
     * @return the converted project
     */
    public ProjectModel toProjectModel(Project project) {
        List<String> projectTasks = null;
        List<String> projectMembers = null;
        if (project.getProjectTasks() != null) {
            projectTasks = new ArrayList<>(project.getProjectTasks());
        }

        if (project.getProjectMembers() != null) {
            projectMembers = new ArrayList<>(project.getProjectMembers());
        }

        return ProjectModel.builder()
            .withProjectId(project.getProjectId())
            .withProjectName(project.getProjectName())
            .withProjectDescription(project.getProjectDescription())
            .withProjectStatus(project.getProjectStatus())
            .withCreatedById(project.getCreatedById())
            .withProjectTasks(projectTasks)
            .withProjectMembers(projectMembers)
            .build();

    }

    /**
     * Converts a provided {@link Task} into a {@link TaskModel} representation.
     * @param task the task to convert
     * @return the converted task
     */
    public TaskModel toTaskModel(Task task) {
        return TaskModel.builder()
            .withTaskId(task.getTaskId())
            .withTaskName(task.getTaskName())
            .withTaskDescription(task.getTaskDescription())
            .withTaskDueDate(task.getTaskDueDate())
            .withTaskAssignedUser(task.getTaskAssignedUser())
            .withCreatedById(task.getCreatedById())
            .build();
    }
}

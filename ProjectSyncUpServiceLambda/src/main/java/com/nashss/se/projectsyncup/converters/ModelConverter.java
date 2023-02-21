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
    public static ProjectModel toProjectModel(Project project) {

        List<String> projectMembers = null;

        if (project.getProjectMembers() != null) {
            projectMembers = new ArrayList<>(project.getProjectMembers());
        }

        return ProjectModel.builder()
            .withProjectId(project.getProjectId())
            .withProjectName(project.getProjectName())
            .withProjectDescription(project.getProjectDescription())
            .withProjectStatus(project.getProjectStatus())
            .withCreatedById(project.getCreatedById())
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
            .withProjectId(task.getProjectId())
            .build();
    }

    /**
     * Converts a list of Project Tasks to a list of TaskModels.
     *
     * @param projectTasks The Project Tasks to convert to TaskModels
     * @return The converted list of TaskModels
     */
    public List<TaskModel> toTaskModelList(List<Task> projectTasks) {
        List<TaskModel> taskModels = new ArrayList<>();

        for (Task projectTask : projectTasks) {
            taskModels.add(toTaskModel(projectTask));
        }

        return taskModels;
    }



}

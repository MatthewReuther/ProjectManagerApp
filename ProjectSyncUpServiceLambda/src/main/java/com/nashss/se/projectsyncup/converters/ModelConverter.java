package com.nashss.se.projectsyncup.converters;

import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;

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

}

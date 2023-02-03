package com.nashss.se.projectsyncup.models;

import java.util.List;

/**
 * This class represents the model of a project.
 * It contains information about the project ID, project name,
 * project description, project status, who project was created by,
 * list of tasks in project and list of members associated with the playlist
 */
public class ProjectModel {
    private final String id;
    private final String name;
    private final String description;
    private final String status;
    private final String createdBy;
    private final List<String> tasks;
    private final List<String> projectMembers;

    /**
     * Instantiates a new PlaylistModel object.
     * Private to enforce user to use Builder to create a new instance of CreateProjectRequest
     *
     * @param id             The id of the project
     * @param name           The name of the project
     * @param description    The description of the project
     * @param status         The status of the project (Not Started, In Progress, Completed)
     * @param createdBy      The ID of the user who created the project
     * @param tasks          A list of tasks associated with the project
     * @param projectMembers A list of team members who are associated with the project
     */

    private ProjectModel(String id, String name, String description,
                         String status, String createdBy, List<String> tasks,
                         List<String> projectMembers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.tasks = tasks;
        this.projectMembers = projectMembers;
    }

    /**
     * Returns the ID of the project.
     *
     * @return The ID of the project.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the project.
     *
     * @return The name of the project.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the project.
     *
     * @return The description of the project.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the project.
     *
     * @return The status of the project.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the name of the person who created the project.
     *
     * @return The name of the person who created the project.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Returns a list of tasks associated with the project.
     *
     * @return A list of tasks associated with the project.
     */
    public List<String> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of members associated with the project.
     *
     * @return A list of members associated with the project.
     */
    public List<String> getProjectMembers() {
        return projectMembers;
    }
}

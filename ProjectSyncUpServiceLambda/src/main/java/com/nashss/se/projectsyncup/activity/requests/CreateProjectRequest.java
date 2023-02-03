package com.nashss.se.projectsyncup.activity.requests;

import java.util.List;


/**
 * CreateProjectRequest implementation representing a request to create a project.
 * This class is used to create an object with the required information for creating a project.
 * The object can then be passed to the service to create the project.
 *
 */
public class CreateProjectRequest {
    private final String projectId;
    private final String projectName;
    private final String projectDescription;
    private final String projectStatus;
    private final String createdBy;
    private final List<String> projectTasks;
    private final List<String> projectMembers;

    /**
     * Instantiates a new CreateProjectRequest object.
     * Private to enforce user to use Builder to create a new instance of CreateProjectRequest
     *
     * @param projectId          The id of the project
     * @param projectName        The name of the project
     * @param projectDescription The description of the project
     * @param projectStatus      The status of the project (Not Started, In Progress, Completed)
     * @param createdBy          The ID of the user who created the project
     * @param projectTasks       A list of tasks associated with the project
     * @param projectMembers     A list of team members who are associated with the project
     */

    private CreateProjectRequest(String projectId, String projectName, String projectDescription,
                                 String projectStatus, String createdBy, List<String> projectTasks,
                                 List<String> projectMembers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
        this.createdBy = createdBy;
        this.projectTasks = projectTasks;
        this.projectMembers = projectMembers;
    }

    /**
     * Returns the ID of the project.
     *
     * @return The ID of the project.
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Returns the name of the project.
     *
     * @return The name of the project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Returns the description of the project.
     *
     * @return The description of the project.
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Returns the status of the project.
     *
     * @return The status of the project.
     */
    public String getProjectStatus() {
        return projectStatus;
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
    public List<String> getProjectTasks() {
        return projectTasks;
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

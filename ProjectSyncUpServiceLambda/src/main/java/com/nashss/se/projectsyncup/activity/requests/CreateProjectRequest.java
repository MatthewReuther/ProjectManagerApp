package com.nashss.se.projectsyncup.activity.requests;

import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.models.ProjectModel;

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
    private final String projectStatus;
    private final String createdBy;
    private final List<String> projectTasks;
    private final List<String> projectMembers;

    /**
     * Instantiates a new CreateProjectRequest object.
     * Private to enforce the use of the Builder Class
     * This design pattern is known as the "Builder pattern", and it is often used to create complex objects with multiple constructor arguments.
     * The Builder pattern allows for the creation of objects step-by-step, using setter methods to configure each parameter.
     * This is especially useful when there are many parameters involved, as it makes the code more readable and avoids issues with having to pass a large number of parameters to a constructor.
     *
     * @param projectId: The id of the project
     * @param projectName: The name of the project
     * @param projectStatus: The status of the project (Not Started, In Progress, Completed)
     * @param createdBy: The ID of the user who created the project
     * @param projectTasks: A list of tasks associated with the project
     * @param projectMembers: A list of team members who are associated with the project
     *
     */

    private CreateProjectRequest(String projectId, String projectName, String projectStatus, String createdBy, List<String> projectTasks, List<String> projectMembers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.createdBy = createdBy;
        this.projectTasks = projectTasks;
        this.projectMembers = projectMembers;
    }


    public String getProjectId() {
        return projectId;
    }


    public String getProjectName() {
        return projectName;
    }


    public String getProjectStatus() {
        return projectStatus;
    }


    public String getCreatedBy() {
        return createdBy;
    }


    public List<String> getProjectTasks() {
        return projectTasks;
    }


    public List<String> getProjectMembers() {
        return projectMembers;
    }
}

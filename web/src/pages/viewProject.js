import ProjectSyncUpClient from '../api/projectSyncUpClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view project page of the website.
 */
class ViewProject extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addProjectToPage', 'addTasksToPage', 'addTask', 'updateProject', 'deleteTask'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addProjectToPage);
        this.dataStore.addChangeListener(this.addTasksToPage);
        this.header = new Header(this.dataStore);
        console.log("viewProject constructor");
    }

    /**
     * Once the client is loaded, get the project metadata and task list.
     */
    async clientLoaded() {

        const urlParams = new URLSearchParams(window.location.search);
        const projectId = urlParams.get('projectId');
        document.getElementById('projectName').innerText = "Loading Project ...";
        const project = await this.client.getProject(projectId);
        this.dataStore.set('project', project);

        document.getElementById('projectTasksList').innerText = "Loading Project Tasks...";
        const projectTasks = await this.client.getProjectTasks(projectId);
        console.log("Project Task List:", projectTasks);
        this.dataStore.set('tasks', projectTasks);

    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        document.getElementById('addTask').addEventListener('click', this.addTask);
        document.getElementById('updateProject').addEventListener('click', this.updateProject);
        this.header.addHeaderToPage();
        this.client = new ProjectSyncUpClient();
        this.clientLoaded();
    }

    /**
     * When the project is updated in the datastore, update the project metadata on the page.
     */
    addProjectToPage() {
        const project = this.dataStore.get('project');
        if (project == null) {
            return;
        }

       if (project.projectStatus == null || project.projectStatus.length === 0) {
            // Display a message if there are no projects to show
            project.projectStatus = "Not Started"
        }

        document.getElementById('projectName').innerText = project.projectName;
        document.getElementById('projectDescription').innerText = project.projectDescription;
        document.getElementById('projectStatus').innerText = project.projectStatus;

        document.getElementById('newProjectName').value = project.projectName;
        document.getElementById('newProjectDescription').value = project.projectDescription;
        document.getElementById('newProjectStatus').value = project.projectStatus;
    }



    /**
     * Method to run when the add task project submit button is pressed. Call the ProjectSyncUpService to add a task to the
     * project.
     */

    async addTask() {
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const project = this.dataStore.get('project');
        if (project == null) {
            return;
        }

        document.getElementById('addTask').innerText = 'Adding...';

        const taskName = document.getElementById('taskName').value;
        const taskDescription = document.getElementById('taskDescription').value;
        const taskDueDate = document.getElementById('taskDueDate').value;
        const taskAssignedUser = document.getElementById('taskAssignedTo').value;
        const projectId = project.projectId;

        const projectTasks = await this.client.addTaskToProject(taskName, taskDescription, taskDueDate, taskAssignedUser, projectId, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('tasks', projectTasks);

        document.getElementById('addTask').innerText = 'Add Task';
        document.getElementById("add-task-form").reset();
        this.clientLoaded();
    }


    /**
     * Method to run when the add task project submit button is pressed. Call the ProjectSyncUpService to add a task to the
     * project.
     */

    async updateProject(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const project = this.dataStore.get('project');
        if (project == null) {
            return;
        }

        const updateButton = document.getElementById('updateProject');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Loading...';

        const projectNameInput = document.getElementById('newProjectName');
        const projectDescriptionInput = document.getElementById('newProjectDescription');
        const projectStatusInput = document.getElementById('newProjectStatus');

        const projectId = project.projectId;

        let projectName;
        if (projectNameInput.value) {
            projectName = projectNameInput.value;
        } else {
            projectName = project.projectName;
        }

        let projectDescription;
        if (projectDescriptionInput.value) {
            projectDescription = projectDescriptionInput.value;
        } else {
            projectDescription = project.projectDescription;
        }

        let projectStatus;
        if (projectStatusInput.value) {
            projectStatus = projectStatusInput.value;
        } else {
            projectStatus = project.projectStatus;
        }

        const updatedProject = await this.client.updateProject(projectId, projectName, projectDescription, projectStatus, (error) => {
            updateButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        document.getElementById('updateProject').innerText = 'Updating Project...';
        console.log('Project updated', updatedProject)

        // Update project object in data store with the new project data.
        this.dataStore.set('project', updatedProject);

        // Update the page with the new project data
        document.getElementById('newProjectName').value = updatedProject.projectName;
        document.getElementById('newProjectDescription').value = updatedProject.projectDescription;
        document.getElementById('newProjectStatus').value = updatedProject.projectStatus;

        document.getElementById('updateProject').innerText = 'Update Project';
        document.getElementById("update-project-form").reset();
        this.clientLoaded();
    }

    /**
     * When the tasks are updated in the datastore, update the list of tasks on the page.
     */
    addTasksToPage() {
        const tasks = this.dataStore.get('tasks')

        if (tasks == null) {
            return;
        }

        let taskHtml = '';
        let task;
        for (task of tasks) {
            taskHtml += `
                <div class="task">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="title">
                                <h5>Task Title:</h5>
                                <p>${task.taskName}<p>
                            </div>
                        </div>

                        <div class="col-md-5">
                            <div class="dueDate">
                                <h5>Date Due:</h5>
                                <p>${task.taskDueDate}<p>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="assignedTo">
                                <h5>Assigned:</h5>
                                <p>${task.taskAssignedUser}<p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="description">
                                <h5>Description:</h5>
                                <p>${task.taskDescription}<p>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <button class="delete-task btn btn-primary" id="delete-task-${task.taskId}">Delete</button>
                        </div>
                    </div>
                </div>
            `;
        }

        document.getElementById('projectTasksList').innerHTML = taskHtml;

        // Add dynamic event listener to each delete button
        const deleteButtons = document.querySelectorAll('.delete-task');
        deleteButtons.forEach((button) => {
            const taskId = button.id.split('-')[2];
            button.addEventListener('click', () => {
                this.deleteTask(taskId);
            });
        });
    }


    /**
     * Method to run when the remove song playlist submit button is pressed. Call the PartyPlaylist to remove a song to the
     * playlist.
     */
    async deleteTask(taskId) {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const project = this.dataStore.get('project');
        if (project == null) {
            return;
        }

        const deleteButton = document.getElementById('delete-task-' + taskId);
        const origButtonText = deleteButton.innerText;
        deleteButton.innerText = 'Loading...';

        const confirmDelete = confirm("Are you sure you want to delete this task?");
        if (confirmDelete) {
            const projectTasks = await this.client.deleteTaskFromProject(taskId, project.projectId, (error) => {
                deleteButton.innerText = origButtonText;
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            this.dataStore.set('tasks', projectTasks);
            this.clientLoaded();
        }
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewProject = new ViewProject();
    viewProject.mount();
};

window.addEventListener('DOMContentLoaded', main);
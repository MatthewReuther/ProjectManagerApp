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
        this.bindClassMethods(['clientLoaded', 'mount', 'addProjectToPage', 'addCreatedProjectsToPage', 'addTasksToPage', 'addTask', 'updateProject', 'deleteTask'], this);
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

        document.getElementById('projectTasksList').innerText = "(loading tasks...)";
        const projectTasks = await this.client.getProjectTasks(projectId);
        console.log("Project Task List:", projectTasks);
        this.dataStore.set('tasks', projectTasks);

        document.getElementById('createdProjectsList').innerText = "(loading your projects...)";
        const createdProjectsList = await this.client.getCreatedProjects();
        this.dataStore.set('projects', createdProjectsList);
        this.addCreatedProjectsToPage();
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        document.getElementById('addTask').addEventListener('click', this.addTask);
        document.getElementById('deleteTask').addEventListener('click', this.deleteTask);
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

        document.getElementById('projectName').innerText = project.projectName;
        document.getElementById('projectDescription').innerText = project.projectDescription;
        document.getElementById('projectStatus').innerText = project.projectStatus;
        document.getElementById('projectOwner').innerText = project.createdById;

    }


    /**
     * When the project is updated in the datastore, update the project metadata on the page.
     */
    addCreatedProjectsToPage() {
        const projects = this.dataStore.get('projects');

        console.log()
        if (projects == null) {
            return;
        }

            let projectHtml = '';
            let project;
            for (project of projects) {
                projectHtml += `

                    <div class="project">
                        <h2>${project.projectName}</h2>
                        <p>${project.projectDescription}</p>
                    </div>
                `;
                 document.getElementById('createdProjectsList').innerHTML = projectHtml;
            }

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
                <li class="task">
                    <span class="title">${task.taskName}</span>
                    <span class="description">${task.taskDescription}</span>
                    <span class="dueDate">${task.taskDueDate}</span>
                    <span class="assignedTo">${task.taskAssignedUser}</span>
                </li>
            `;
        }

        document.getElementById('projectTasksList').innerHTML = taskHtml;
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
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        document.getElementById('updateProject').innerText = 'Updating Project...';
        console.log('Project updated', updatedProject)

        // Update project object in data store with the new project data.
        this.dataStore.set('project', updatedProject);

        document.getElementById('updateProject').innerText = 'Update Project';
        document.getElementById("update-project-form").reset();
        this.clientLoaded();
    }



    /**
     * Method to run when the remove song playlist submit button is pressed. Call the PartyPlaylist to remove a song to the
     * playlist.
     */
    async deleteTask() {
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const project = this.dataStore.get('project');
        if (project == null) {
            return;
        }

        document.getElementById('deleteTask').innerText = 'Deleting...';
        const taskId = document.getElementById('taskId').value;
        const projectId = project.projectId;

        const projectTasks = await this.client.deleteTaskFromProject(taskId, projectId, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('tasks', projectTasks);

        document.getElementById('deleteTask').innerText = 'Delete Task';
        document.getElementById("add-task-form").reset();
        this.clientLoaded();
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
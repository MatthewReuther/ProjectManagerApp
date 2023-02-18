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
        this.bindClassMethods(['clientLoaded', 'mount', 'addProjectToPage', 'addTasksToPage', 'addTask'], this);
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
        console.log("ProjectTasks:", projectTasks);
        this.dataStore.set('tasks', projectTasks);
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        document.getElementById('addTask').addEventListener('click', this.addTask);
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
        document.getElementById('projectOwner').innerText = project.createdById;

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
        console.log("ProjectTask:", tasks);
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

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewProject = new ViewProject();
    viewProject.mount();
};

window.addEventListener('DOMContentLoaded', main);

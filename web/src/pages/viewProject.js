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
        this.bindClassMethods(['clientLoaded', 'mount', 'addProjectToPage', 'addTasksToPage', 'addProject'], this);
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

        document.getElementById('projectTasks').innerText = "(loading tasks...)";
        const tasks = await this.client.getProjectTasks(projectId);
        this.dataStore.set('projectTasks', projectTasks);

    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        document.getElementById('addTask').addEventListener('click', this.addSong);

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

//        const projectTasksText = document.getElementById('projectTasks').value;

//        let projectTasks;
//        if (projectTasksText.length < 1) {
//            projectTasks = null;
//        } else {
//            projectTasks = projectTasksText.split(/\s*,\s*/);
//        }

        let taskHtml = '';
        let task;
        for (task of project.projectTasks) {
            taskHtml += '<div class="task">' + task + '</div>';
        }
        document.getElementById('projectTasks').innerHTML = taskHtml;

    }



    /**
     * When the tasks are updated in the datastore, update the list of tasks on the page.
     */
    addTasksToPage() {
        const projectTasks = this.dataStore.get('projectTasks')

        if (projectTasks == null) {
            return;
        }

        let projectTaskHtml = '';
        let projectTask;
        for (projectTask of projectTasks) {
            projectTaskHtml += `
                <li class="projectTask">
                    <span class="title">${projectTask.taskName}</span>
                    <span class="description">${projectTask.taskDescription}</span>
                    <span class="dueDate">${projectTask.taskDueDate}</span>
                    <span class="assignedTo">${projectTask.taskAssignedUser}</span>
                </li>
            `;
        }
        document.getElementById('projectTasks').innerHTML = projectTaskHtml;
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
        const projectId = project.projectId;
        const taskName = document.getElementById('taskName').value;
        const taskDescription = document.getElementById('taskDescription').value;
        const taskDueDate = document.getElementById('taskDueDate').value;
        const createdById = document.getElementById('createdById').value;
        const taskAssignedUser = document.getElementById('taskAssignedTo').value;


        const projectTasks = await this.client.addTaskToProject(projectId, taskName, taskDescription, taskDueDate, createdById, taskAssignedUser (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('projectTasks', projectTasks);

        document.getElementById('addTask').innerText = 'Add Task';
        document.getElementById("add-task-form").reset();
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

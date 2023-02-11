import ProjectSyncUpClient from '../api/projectSyncUpClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create project page of the website.
 */
class CreateProject extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewProject'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewProject);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new ProjectSyncUpClient();
    }

    /**
     * Method to run when the create project submit button is pressed. Call the ProjectSyncUpService to create the project.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const projectName = document.getElementById('projectName').value;
        const projectDescription = document.getElementById('projectDescription').value;

//        const projectTasksText = document.getElementById('projectTasks').value;

//        let projectTasks;
//        if (projectTasksText.length < 1) {
//            projectTasks = null;
//        } else {
//            projectTasks = projectTasksText.split(/\s*,\s*/);
//        }

        const project = await this.client.createProject(projectName, projectDescription, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('project', project);
    }

    /**
     * When the project is updated in the datastore, redirect to the view project overview page.
     */
    redirectToViewProject() {
        const project = this.dataStore.get('project');
        if (project != null) {
            window.location.href = `/project.html?projectId=${project.projectId}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createProject = new CreateProject();
    createProject.mount();
};

window.addEventListener('DOMContentLoaded', main);

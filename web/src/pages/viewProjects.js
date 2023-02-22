import ProjectSyncUpClient from '../api/projectSyncUpClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view project page of the website.
 */
class ViewProjects extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submit', 'redirectToViewProject', 'addCreatedProjectsToPage'], this);
        this.dataStore = new DataStore();
//        this.dataStore.addChangeListener(this.addProjectToPage);
        this.dataStore.addChangeListener(this.redirectToViewProject);
        this.header = new Header(this.dataStore);
        console.log("viewProjects constructor");
    }

    /**
     * Once the client is loaded, get the project metadata and task list.
     */
    async clientLoaded() {

//        const urlParams = new URLSearchParams(window.location.search);
//        const projectId = urlParams.get('projectId');
//        const project = await this.client.getProject(projectId);
//        this.dataStore.set('project', project);

        document.getElementById('createdProjectsList').innerText = "(loading your projects...)";
        const createdProjectsList = await this.client.getCreatedProjects();
        this.dataStore.set('projects', createdProjectsList);
        this.addCreatedProjectsToPage();
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new ProjectSyncUpClient();
        this.clientLoaded();
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

        const project = await this.client.createProject(projectName, projectDescription, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        console.log("project:", project);
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
                    <a href="/project.html?projectId=${project.projectId}">${project.projectId}</a>
                </div>
            `;
        }
        document.getElementById('createdProjectsList').innerHTML = projectHtml;


    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewProjects = new ViewProjects();
    viewProjects.mount();
};

window.addEventListener('DOMContentLoaded', main);

import ProjectSyncUpClient from '../api/projectSyncUpClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view project page of the website.
 */
class ViewProject extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addProjectToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addProjectToPage);
        this.header = new Header(this.dataStore);

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
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {

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

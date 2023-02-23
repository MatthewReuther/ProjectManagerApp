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
        this.bindClassMethods(['clientLoaded', 'mount', 'addCreatedProjectsToPage'], this);
        this.dataStore = new DataStore();
//        this.dataStore.addChangeListener(this.addProjectToPage);
        this.header = new Header(this.dataStore);
        console.log("viewProject constructor");
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
        this.header.addHeaderToPage();
        this.client = new ProjectSyncUpClient();
        this.clientLoaded();
    }



    /**
     * When the project is updated in the datastore, update the project metadata on the page.
     */
    addCreatedProjectsToPage(maxProjects = 3) {
      const projects = this.dataStore.get('projects');

      if (projects == null) {
        return;
      }

      let projectHtml = '';
      let projectCount = 0;
      for (const project of projects) {
        if (projectCount >= maxProjects) {
          break;
        }
        projectHtml += `
          <div class="project">
            <h2>${project.projectName}</h2>
            <p>${project.projectDescription}</p>
            <a href="/project.html?projectId=${project.projectId}">View Project</a>
          </div>
        `;
        projectCount++;
      }

      document.getElementById('createdProjectsList').innerHTML = projectHtml;
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

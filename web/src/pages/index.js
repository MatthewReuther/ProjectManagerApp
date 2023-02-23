import ProjectSyncUpClient from '../api/projectSyncUpClient';
import Authenticator from '../api/authenticator';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create project page of the website.
 */
class Index extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("Index constructor");
        // Create a enw datastore with an initial "empty" state.
//        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        // Add the header to the page and load the ProjectSyncUpClient.
        this.header.addHeaderToPage();
        this.client = new ProjectSyncUpClient();

        // Get a reference to the dashboard button.

        // Check if the user is logged in.
        this.loginChecker();
    }

    async loginChecker() {
        const dashboardButton = document.getElementById('dashboard-button');

        // Check if the user is logged in.
        const authenticator = new Authenticator();
        const isLoggedIn = await authenticator.isUserLoggedIn();

        // Show or hide the dashboard button based on the user's login status.
        if (isLoggedIn) {
            dashboardButton.style.display = 'block';
            window.location.href = 'dashboard.html'; // Replace 'dashboard.html' with the URL of your destination page
        } else {
            dashboardButton.style.display = 'none';
        }
    }



}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);
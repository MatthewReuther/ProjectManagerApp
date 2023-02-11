import ProjectSyncUpClient from '../api/projectSyncUpClient';
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

        // Create a enw datastore with an initial "empty" state.
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the ProjectSyncUpClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.

        this.header.addHeaderToPage();

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



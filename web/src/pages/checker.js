import ProjectSyncUpClient from '../api/projectSyncUpClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the check if user is logged in.
 */
class Checker extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'redirectToPage'], this);
        this.dataStore = new DataStore();
        this.authenticator = new Authenticator();
    }

    async mount() {
        const userLoggedIn = await this.authenticator.isUserLoggedIn();
        if (userLoggedIn) {
            window.location.href = `/dashboard.html`;
        } else {
            await this.authenticator.login();
            window.location.href = `/index.html`;
        }
    }
}

const main = async () => {
    const checker = new Checker();
    checker.mount();
};

window.addEventListener('DOMContentLoaded', main);
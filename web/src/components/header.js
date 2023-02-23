import ProjectSyncUpClient from '../api/projectSyncUpClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new ProjectSyncUpClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const userInfo = this.createUserInfoForHeader(currentUser);

        const login = document.getElementById('userLogin');
//        header.appendChild(siteTitle);
        login.appendChild(userInfo);
    }

//    createSiteTitle() {
//        const homeButton = document.createElement('a');
//        homeButton.classList.add('header_home');
//        homeButton.href = 'index.html';
//        homeButton.innerText = 'Homepage';
//
//        const siteTitle = document.createElement('div');
//        siteTitle.classList.add('site-title');
//        siteTitle.appendChild(homeButton);
//
//        return siteTitle;
//    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('a');
        userInfo.classList.add('user', 'nav-link');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}



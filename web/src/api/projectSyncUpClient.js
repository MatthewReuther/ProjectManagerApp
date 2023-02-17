import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the ProjectSyncUpService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
  export default class ProjectSyncUpClient extends BindingClass {

      constructor(props = {}) {
          super();

          const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getProject', 'getProjectTasks', 'createProject'];
          this.bindClassMethods(methodsToBind, this);

          this.authenticator = new Authenticator();;
          this.props = props;

          axios.defaults.baseURL = process.env.API_BASE_URL;
          this.axiosClient = axios;
          this.clientLoaded();
      }

      /**
       * Run any functions that are supposed to be called once the client has loaded successfully.
       */
      clientLoaded() {
          if (this.props.hasOwnProperty("onReady")) {
              this.props.onReady(this);
          }
      }

      /**
       * Get the identity of the current user
       * @param errorCallback (Optional) A function to execute if the call fails.
       * @returns The user information for the current user.
       */
      async getIdentity(errorCallback) {
          try {
              const isLoggedIn = await this.authenticator.isUserLoggedIn();

              if (!isLoggedIn) {
                  return undefined;
              }

              return await this.authenticator.getCurrentUserInfo();
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }

      async login() {
          this.authenticator.login();
      }

      async logout() {
          this.authenticator.logout();
      }

      async getTokenOrThrow(unauthenticatedErrorMessage) {
          const isLoggedIn = await this.authenticator.isUserLoggedIn();
          if (!isLoggedIn) {
              throw new Error(unauthenticatedErrorMessage);
          }

          return await this.authenticator.getUserToken();
      }

      /**
       * Gets the project for the given ID.
       * @param id Unique identifier for a project
       * @param errorCallback (Optional) A function to execute if the call fails.
       * @returns The project's metadata.
       */
       async getProject(projectId, errorCallback) {
            try {
                const response = await this.axiosClient.get(`projects/${projectId}`);
                return response.data.project;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
       }


       /**
        * Get the tasks of a given project by the projectId.
        * @param projectId Unique identifier for a project
        * @param errorCallback (Optional) A function to execute if the call fails.
        * @returns The list of songs on a playlist.
        */
       async getProjectTasks(projectId, errorCallback) {
           try {
               const response = await this.axiosClient.get(`projects/${projectId}/tasks`);
               return response.data.taskList;
           } catch (error) {
               this.handleError(error, errorCallback)
           }
       }

      /**
       * Create a new project owned by the current member.
       * @param projectName The name of the project to create.
       * @param projectDescription The description of the project to create.
       * @param projectTasks the tasks associated with the project.
       * @param errorCallback (Optional) A function to execute if the call fails.
       * @returns The project that has been created.
       */
      async createProject(projectName, projectDescription, errorCallback) {
          try {
              const token = await this.getTokenOrThrow("Only authenticated users can create projects.");
              const response = await this.axiosClient.post(`projects`, {
                  projectName: projectName,
                  projectDescription: projectDescription
              }, {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
              });
              console.log(response.data.project)
              return response.data.project;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }

      /**
       * Add a task to a project.
       * @param projectId The id of the project to add a task to.
       * @param taskDescription The description of the task.
       * @param taskDueDate The due date of the task.
       * @param createdById The manager who created the task.
       * @param taskAssignedUser The member assigned to complete the task.
       * @returns The list of tasks in a project.
       */
      async addTaskToProject(taskName, taskDescription, taskDueDate, taskAssignedUser, projectId, errorCallback) {
          try {
              const token = await this.getTokenOrThrow("Only authenticated users can add a task to a project.");
              const response = await this.axiosClient.post(`projects/${projectId}/tasks`, {
                  taskName: taskName,
                  taskDescription: taskDescription,
                  taskDueDate: taskDueDate,
                  taskAssignedUser: taskAssignedUser,
                  projectId: projectId
              }, {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
              });
              console.log("data:", response.data)
              return response.data.taskList;
          } catch (error) {
              this.handleError(error, errorCallback)
          }
      }

      /**
       * Helper method to log the error and run any error functions.
       * @param error The error received from the server.
       * @param errorCallback (Optional) A function to execute if the call fails.
       */
      handleError(error, errorCallback) {
          console.error(error);

          const errorFromApi = error?.response?.data?.error_message;
          if (errorFromApi) {
              console.error(errorFromApi)
              error.message = errorFromApi;
          }

          if (errorCallback) {
              errorCallback(error);
          }
      }
  }




# Design Document

## _Project Title_ Design

## 1. Problem Statement

_The objective of this project is to create a web application using Java, that helps streamline collaboration among team members working on projects together. The app will allow managers to create projects and assign tasks, while team members can track, prioritize and update progress of their assigned tasks, resulting in an efficient tool that increases project transparency for teams while working together._

## 2. Use Cases

U1. As a manager, I want to create a new, empty project with a given name.

U2. As a manager, I want to create a new, empty task with a given task name, description, due date, and list of assigned team members.

U3. As a manager, I want to add a task, to a project with a given name.

U4. As a manager, I want to update a task with different information.

U5. As a manager, I want to delete a task with a given ID.

U6. As a manager, I want to view all projects I have created.

U7. As a team member, I want to retrieve all tasks assigned to me.

U8. As a team member, I want to retrieve all tasks assigned to me in a prioritized order (due date).

U9. As a team member, I want to be able to update the progress of specific task (Not Started, In Progress, Complete).
U10. As a team member, I want to be able to track the amount of time I spend on each task in order to accurately bill for my work or manage my own productivity.

## 3. Stretch Goals
U10. As a team member, I want to be able to track the amount of time I spend on each task in order to accurately bill for my work or manage my own productivity.

U11. As a team member, I want to be able to comment on a specific task I am assigned to.

U13. As a manager, I want to be able to share and collaborate files and documents with my team.

## 4. Project Scope

### 4.1. In Scope

_Which parts of the problem defined in Sections 1 and 2 will you solve with this design? This should include the base functionality of your product. What pieces are required for your product to work?_

_The functionality described above should be what your design is focused on. You do not need to include the design for any out of scope features or expansions._

### 4.2. Out of Scope

_Based on your problem description in Sections 1 and 2, are there any aspects you are not planning to solve? Do potential expansions or related problems occur to you that you want to explicitly say you are not worrying about now? Feel free to put anything here that you think your team can't accomplish in the unit, but would love to do with more time._

_The functionality here does not need to be accounted for in your design._

# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._

# 6. API

## 6.1. Public Models

_Define the data models your service will expose in its responses via your *`-Model`* package. These will be equivalent to the *`PlaylistModel`* and *`SongModel`* from the Unit 3 project._


```
// ProjectModel
String projectId;
String projectName;
String projectStatus;
List<String> userIds;
```

```
// TaskModel
String taskId;
String taskName;
String taskDescription;
String taskDueDate;
String taskStatus;
List<String> taskAssignedUsers;
Integer taskTimer;
```

```
// UserModel
String userId;
String userName;
String userRole;
List<String> taskIds;
```

## 6.2. _Create Project Endpoint_
* Accepts `POST` requests to `/projects`
* Accepts data to create a new Project with a given name, project status, and an optional list of tasks. Returns the new project, including a unique project ID assigned by the Project Management Service.

## 6.2. _Create Task Endpoint_
* Accepts `POST` requests to `/tasks`
* Accepts data to create a new Task with a given task name, description, due date, task status, and list of assigned team members. Returns the new Task, including a unique task ID assigned by the Project Management Service.

## 6.2. _Add Task To Project Endpoint_
* Accepts `PUT` requests to `/projects/:id/tasks`
* Accepts a project ID and task ID and adds the task to the specified project. 
  * If the project is not found, will throw a ProjectNotFoundException 
  * If the given task ID doesn't exist, will throw an ``TaskNotFoundException``

## 6.2. _Update Task Information Endpoint_
* Accepts `PUT` requests to `/tasks/:id`
* Accepts a task ID and updated task information, Returns the updated the task with the new information.
    * If the given task ID doesn't exist, will throw an ``TaskNotFoundException``

## 6.2. _Delete Task From Project Endpoint_
* Accepts `DELETE` requests to `/tasks/:id`
* Accepts a task ID, deletes the task with that ID from project.
    * If the given task ID doesn't exist, will throw an ``TaskNotFoundException``

## 6.2. _View All Projects Endpoint_
* Accepts `GET` requests to `/projects`
* Accepts a project ID, returns a list of projects created by that Manager.
    * If the given project ID doesn't exist, will throw an ``ProjectNotFoundException``
    * If the given Manager has not created any projects, an empty list will be returned

## 6.2. _View All Assigned Tasks Endpoint_
* Accepts `GET` requests to ` /tasks/assigned`
* Retrieves a list of all tasks assigned to given user ID.
* Accepts a user ID, returns a list of all tasks assigned to that user.
    * If the given user ID doesn't exist, will throw an ``UserNotFoundException``
    * If the given user does not have any tasks, an empty list is returned.

## 6.2. _View Assigned Tasks By Due Date Endpoint_
* Accepts `GET` requests to ` /tasks/assigned/prioritized`
* Retrieves a list of all tasks assigned to given user ID.
  * Returns the song list in default playlist order
    * If the optional order parameter is provided, this API will return the task list in ``order``, reverse order, or by Due Date, based on the value of ``order``
      * DEFAULT - same as default behavior, returns tasks in ordered assigned by Manager
      * REVERSED - returns project tasks in reversed order 
      * DUEDATE - returns project tasks in order of due date.
  * If the given project ID is found, but contains no tasks, the tasks list will be empty
  * If the given project ID doesn't exist, will throw an ``ProjectNotFoundException``

## 6.2. _Update Task Progress Endpoint_
* Accepts `PUT` requests to `/tasks/:id/progress`
* Accepts a task ID and a new progress status (Not Started, In Progress, Complete), updates the ``taskStatus`` of the task with that ID.
    * If the given task ID doesn't exist, will throw an ``TaskNotFoundException``

## 6.2. _Track Time Spent on Task Endpoint_
* Accepts `PUT` requests to `/tasks/:id/time`
* Accepts a task ID and a new time spent on task, updates the ``taskTimer`` of the task with that ID.
    * If the given task ID doesn't exist, will throw an ``TaskNotFoundException``


# 7. Tables
### 7.1. `Projects`
```
projectId // Partition Key, String
projectId // String
projectName // String
createdBy // String
tasks // List<String>
```

### 7.2. `Tasks`
```
taskId // Partition Key, String
taskName // String
taskDescription // String
assignedTo // List<String> userId-assignedTo-index partition key
dueDate // String userId-assignedTo-index sort key
status // String
timeSpent // Number
```

### 7.2. `Users`
```
userId // Partition Key, String
userName // String
userName // String
userRole // String
```

# 8. Pages
- Login Page
- Manager Homepage
- Team Member Homepage
- [Wire Frame](https://www.figma.com/file/7vq0CO2AfrBKIRMwxEdqRr/Project-Management-App?node-id=0%3A1&t=NWXwFL70VY3kkXQx-1)

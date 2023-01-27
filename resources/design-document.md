# Design Document

## Project Management Application

### Problem Statement

The objective of this project is to create a web application using Java, that helps streamline collaboration among team members working on projects together. The app will allow managers to create projects and assign tasks, while team memberss can track, prioritize and update progress of their assigned tasks, resulting in an efficient tool that increases project transparency for teams while working together.

# Project Scope

### Use Cases (Goals)

- U1. As a manager, I want to be able to create tasks and assign tasks to specific team member/members.

- U2. As a manager, I want to be able to edit a task.

- U3. As a manager, I want to be able to delete tasks.

- U4. As a manager I want to be able to view the progress of tasks specific tasks.

- U5. As a manager I want to be able to set due dates for tasks.

- U6. As a team member, I want to be able to prioritize all tasks assigned to me by due date.

- U7. As a team member, I want to be able to track the progress of specific task (Not Started, In Progress, Complete).

- U8. As a team member, I want to be able to view tasks assigned to me.

- U9. As a team member, I want to be able to view the tasks assigned to other team members so that I can understand the overall progress of a project.

- U10*. As a manager, I want to be able to share and collaborate files and documents with my team.

- U11*. As a team member, I want to be able to track the amount of time I spend on each task in order to accurately bill for my work or manage my own productivity.

### Stretch Goals
- Adding the ability for team members to create and assign tasks to other team members.
- Integrating a calendar view for tasks to make it easier to visualize deadlines.
- Adding a feature for attaching files to tasks.

# Proposed Architecture Overview

## DynamoDB Tables
- ManagersTable
    - managerId : String (Partition Key)
    - managerName : String
    - managerPassword : String


- TeamMembersTable
    - memberId : String (Partition Key)
    - memberName : String
    - memberPassword : String


- TasksTable
    - taskId : String (Partition Key)
    - dueDate : String (Sort Key)
    - taskTitle : String
    - taskDescription : String
    - assignedTo : List<String>
    - assignedBy : String
    - taskStatus : String
    - ***(Everything below here is hopeful to get done)***
    - taskFiles : List<String>

- AssignedTasksTable
    - taskId : String (Partition Key)
    - userId : String (Sort Key)

- TaskTimersTable
    - taskId : String (Partition Key)
    - userId : String (Sort Key)
    - taskTimer : Number

- TaskFilesTable
    - fileId : String (Partition Key)
    - fileName : String
    - fileUrl : String
    - taskId : String


## API Endpoints

### 1. Create Task
- Description: Allows managers to create a new task and assign it to one or more team members.
- HTTP Method: `POST`
- Path: `/tasks`
- Request Body: <br>
  ```{"title": "Task Title",``` <br>
  ```"description": "Task Description", ```<br>
  ```"assigned_to": ["userId_1", "userId_2"], ```<br>
  ```"assigned_by": "managerId", ```<br>
  ```"status": "open", ```<br>
  ```"due_date": "yyyy-mm-dd", ```<br>
  ```"files": ["fileID_1", "fileID_2"]}```<br>
- Response Body: <br>
  ``{"task_id": "generated_task_id"}``
- Errors: <br>
  ``{"error": "Missing required fields" }``

### 2. Update Task
- Description: Allows managers to update the details of a task.
- HTTP Method: `PUT`
- Path: `/tasks/{task_id}`
- Request Body: <br>
  ```{"title": "Updated Task Title",``` <br>
  ``` "description": "Updated Task Description", ```<br>
  ```"assigned_to": ["user_id_1", "user_id_2"], ```<br>
  ```"status": "in progress", ```<br>
  ```"due_date": "yyyy-mm-dd"} ```<br>
- Response Body: <br>
  ``{"message": "Task updated successfully"}``
- Errors: <br>
  ``{"error": "Task not found"}``

### 3. Delete Task
- Description: Allows managers to delete a task.
- HTTP Method: `DELETE`
- Path: `/tasks/{task_id}`
- Request Body: <br>
  ```{ "message": "Task deleted successfully"}```
- Response Body: <br>
  ``{"message": "Task deleted successfully"}``
- Errors: <br>
  ``{"error": "Task not found"}``

### 4. Get Task
- Description: Allows managers and team members to view the details of a task.
- HTTP Method: `GET`
- Path: `/tasks/{task_id}`
- Response Body: <br>
  ```{ "task": { "id": "task_id", ``` <br>
  ``` "title": "Task Title", ```<br>
- ``` "description": "Task Description", ```<br>
  ```"assigned_to": ["user_id_1", "user_id_2"], ```<br>
  ```"assigned_by": "manager_id", ```<br>
  ```"status": "open", ```<br>
  ```"due_date": "yyyy-mm-dd"} ```<br>
- Errors: <br>
  ``{"error": "Task not found"}``

### 4. Get Assigned Tasks
- Description: Allows team members to view all tasks assigned to them..
- HTTP Method: `GET`
- Path: `/tasks/assigned/{user_id}`
- Response Body: <br>
  ```{ "tasks": [ {  ``` <br>
  ```"id": "task_id_1", ``` <br>
  ```"title": "Task Title 1", ```<br>
  ```"description": "Task Description 1", ```<br>
  ```"assigned_by": "manager_id_1", ```<br>
  ```"status": "open", ```<br>
  ```"due_date": "yyyy-mm-dd"} ```<br>
  ```"files": ["file_id_1", "file_id_2"] },  ```<br>
  ```"{ id": "task_id_2", ``` <br>
  ```"title": "Task Title 2", ```<br>
  ```"description": "Task Description 2", ```<br>
  ```"assigned_by": "manager_id_2", ```<br>
  ```"status": "in progress", ```<br>
  ```"due_date": "yyyy-mm-dd"} ```<br>
  ```"files": ["file_id_3"] ```<br>
  ```}]}" ```<br>
- Errors: <br>
  ``{"error": "Task not found"}``

### 5. Set Task Status
- Description: Allows team members to update the status of a task.
- HTTP Method: `PUT`
- Path: `/tasks/{task_id}/status`
- Request Body: <br>
  ```{ "status": "completed" } ``` <br>
- Response Body: <br>
  ```{  "message": "Task status updated successfully" } ``` <br>
- Errors: <br>
  ``{"error": "Task not found"}``

Do I create ones for the others as well.?

# 8. Pages
- Login Page
- Manager Homepage
- Team Member Homepage
- [Wire Frame](https://www.figma.com/file/7vq0CO2AfrBKIRMwxEdqRr/Project-Management-App?node-id=0%3A1&t=NWXwFL70VY3kkXQx-1)

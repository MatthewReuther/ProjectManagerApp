#Find Cognito Authentication Token
##Login
Login to website through cognito

##Create New Project
1. Once created run brows inspector
2. Go to Network
3. Select ```cognito-idp.us-east-2.amazonaws.com```
4. Go to Preview tab 
5. Open AuthenticationResult
6. Copy IdToken

## CreateProject
```
curl -X POST \
    http://127.0.0.1:3000/projects \
    -H 'Authorization: Bearer eyJraWQiOiJJV1Y0N1BkbTdRN091b1pDdm5YdmVLVGZVQ1wvcTFwNkUxNGJQcVJydzV4dz0iLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiV3RiN1RaV1dxRXU0YVZuTTZNbmdnZyIsInN1YiI6IjdiMThiYjM5LTFkY2MtNDc4Zi1iODVjLWI4ODUyMTAxMGVkZCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0yLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMl90YWN4RkNLMEkiLCJjb2duaXRvOnVzZXJuYW1lIjoiN2IxOGJiMzktMWRjYy00NzhmLWI4NWMtYjg4NTIxMDEwZWRkIiwib3JpZ2luX2p0aSI6ImU0YmE0N2QxLWNiMzItNGEzMC05NzZjLWVjMjEwZWEyM2I4ZiIsImF1ZCI6IjVlZG1mOGQyb2M2NGhkb2xrZ2hjdWQzNWd2IiwiZXZlbnRfaWQiOiI3NjlkZDFiMi1lMzViLTRiZjEtOTk3NC1lZTUxZjNjMTg1MjAiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY3NTk2MjI3MCwibmFtZSI6Ik1hdHRoZXciLCJleHAiOjE2NzU5NjU4NzAsImlhdCI6MTY3NTk2MjI3MCwianRpIjoiNjdlMDEzOTMtODZkOS00NjYwLTk1MGEtOTk1ZjNmMGQ3Y2Y2IiwiZW1haWwiOiJtYXR0aGV3LnJldXRoZXJAZ21haWwuY29tIn0.JyCXkoGkWcT-HIaQzLTG1v4e781BEHmTmLOAFP5tzeW_kC-zmasvpKf4TzbgfX3HZk5lOVaQ5zEZmcC-AI4NwY0EmItiO0jo-9-BN_P6VKsjJpRu9R2bLsGMeZcqSkr1qTMIVBzZisJOC8T-4myx0Z8P5hiIq1Y8vtg06w2lr4-xc7b6kgQcbAcB9g_Z1AYzirznilP1YMPRxKQufYUNlsuj5CM5AUzhq-lE2p7eXl2n3bAiLnaHYthQxabJfeSG_jaAxLugp1ScqpwJiYOwNdrS27dpPb1uMjPg8iphpIklMOLIOZOLTALTfoDIqxODKakOHRSge_szmh9i-tv_qA' \
    -d '{
    "projectName": "Big Project",
    "projectDescription": "This is a test project",
    "projectStatus": "ACTIVE",
    "createdById": "1234",
    "projectTasks": [
    "Task 1",
    "Task 2"
    ],
    "projectMembers": [
    "User 1",
    "User 2"
    ]
}'
```

## GetProjectById
```
curl -X GET http://127.0.0.1:3000/projects/F0PoBCeg 
```

## AddTaskToProject
```
curl -X POST \
    http://127.0.0.1:3000/projects/xpa3y/tasks \
    -H 'Authorization: Bearer eyJraWQiOiJJV1Y0N1BkbTdRN091b1pDdm5YdmVLVGZVQ1wvcTFwNkUxNGJQcVJydzV4dz0iLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiV3RiN1RaV1dxRXU0YVZuTTZNbmdnZyIsInN1YiI6IjdiMThiYjM5LTFkY2MtNDc4Zi1iODVjLWI4ODUyMTAxMGVkZCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0yLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMl90YWN4RkNLMEkiLCJjb2duaXRvOnVzZXJuYW1lIjoiN2IxOGJiMzktMWRjYy00NzhmLWI4NWMtYjg4NTIxMDEwZWRkIiwib3JpZ2luX2p0aSI6ImU0YmE0N2QxLWNiMzItNGEzMC05NzZjLWVjMjEwZWEyM2I4ZiIsImF1ZCI6IjVlZG1mOGQyb2M2NGhkb2xrZ2hjdWQzNWd2IiwiZXZlbnRfaWQiOiI3NjlkZDFiMi1lMzViLTRiZjEtOTk3NC1lZTUxZjNjMTg1MjAiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY3NTk2MjI3MCwibmFtZSI6Ik1hdHRoZXciLCJleHAiOjE2NzU5NjU4NzAsImlhdCI6MTY3NTk2MjI3MCwianRpIjoiNjdlMDEzOTMtODZkOS00NjYwLTk1MGEtOTk1ZjNmMGQ3Y2Y2IiwiZW1haWwiOiJtYXR0aGV3LnJldXRoZXJAZ21haWwuY29tIn0.JyCXkoGkWcT-HIaQzLTG1v4e781BEHmTmLOAFP5tzeW_kC-zmasvpKf4TzbgfX3HZk5lOVaQ5zEZmcC-AI4NwY0EmItiO0jo-9-BN_P6VKsjJpRu9R2bLsGMeZcqSkr1qTMIVBzZisJOC8T-4myx0Z8P5hiIq1Y8vtg06w2lr4-xc7b6kgQcbAcB9g_Z1AYzirznilP1YMPRxKQufYUNlsuj5CM5AUzhq-lE2p7eXl2n3bAiLnaHYthQxabJfeSG_jaAxLugp1ScqpwJiYOwNdrS27dpPb1uMjPg8iphpIklMOLIOZOLTALTfoDIqxODKakOHRSge_szmh9i-tv_qA' \
    -d '{
    "projectId": "xpa3y",
    "taskName": "Testing",
    "taskDescription": taskDescription,
    "taskDueDate": taskDueDate,
    "taskStatus": In Progress,
    "taskAssignedUser": Mark,
    "taskTimer": taskTimer,
}'
```

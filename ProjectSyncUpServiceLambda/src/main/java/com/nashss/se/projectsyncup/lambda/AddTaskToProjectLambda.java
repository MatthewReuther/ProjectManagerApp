package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.AddTaskToProjectRequest;
import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.AddTaskToProjectResult;

/**
 * AddTaskToProjectLambda class extends the LambdaActivityRunner and implements the RequestHandler interface.
 * This class provides the implementation for the handleRequest method, which is used to create a new project.
 */
public class AddTaskToProjectLambda extends LambdaActivityRunner<AddTaskToProjectRequest, AddTaskToProjectResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddTaskToProjectRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddTaskToProjectRequest> input, Context context) {
        return super.runActivity(
                () -> {
                AddTaskToProjectRequest unauthenticatedRequest = input.fromBody(AddTaskToProjectRequest.class);
                return input.fromUserClaims(claims ->
                    AddTaskToProjectRequest.builder()
                        .withTaskName(unauthenticatedRequest.getTaskName())
                        .withTaskDescription(unauthenticatedRequest.getTaskDescription())
                        .withTaskDueDate(unauthenticatedRequest.getTaskDueDate())
                        .withTaskAssignedUser(unauthenticatedRequest.getTaskAssignedUser())
                        .build());
            }, (request, serviceComponent) ->
                    serviceComponent.provideAddTaskToProjectActvity().handleRequest(request)
        );
    }
}

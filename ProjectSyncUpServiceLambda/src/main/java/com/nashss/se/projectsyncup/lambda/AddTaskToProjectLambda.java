package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.AddTaskToProjectRequest;
import com.nashss.se.projectsyncup.activity.results.AddTaskToProjectResult;

/**
 * AddTaskToProjectLambda class extends the LambdaActivityRunner and implements the RequestHandler interface.
 * This class provides the implementation for the handleRequest method, which is used to create a new project.
 */
public class AddTaskToProjectLambda extends LambdaActivityRunner<AddTaskToProjectRequest, AddTaskToProjectResult>
        implements RequestHandler<LambdaRequest<AddTaskToProjectRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<AddTaskToProjectRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromBody(AddTaskToProjectRequest.class),
                (request, serviceComponent) ->
                        serviceComponent.provideAddTaskToProjectActvity().handleRequest(request)
        );
    }
}

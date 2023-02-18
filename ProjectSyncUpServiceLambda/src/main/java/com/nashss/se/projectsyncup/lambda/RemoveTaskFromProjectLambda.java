package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.RemoveTaskFromProjectRequest;
import com.nashss.se.projectsyncup.activity.requests.RemoveTaskFromProjectResult;

/**
 * RemoveTaskFromProjectLambda class extends the LambdaActivityRunner and implements the RequestHandler interface.
 * This class provides the implementation for the handleRequest method, which is used to remove a task from a project.
 */
public class RemoveTaskFromProjectLambda
        extends LambdaActivityRunner<RemoveTaskFromProjectRequest, RemoveTaskFromProjectResult>
        implements RequestHandler<LambdaRequest<RemoveTaskFromProjectRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<RemoveTaskFromProjectRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromBody(RemoveTaskFromProjectRequest.class),
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveTaskFromProjectActivity().handleRequest(request)
        );
    }
}


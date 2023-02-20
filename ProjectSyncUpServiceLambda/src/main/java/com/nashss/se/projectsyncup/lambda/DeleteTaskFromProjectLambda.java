package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.DeleteTaskFromProjectRequest;
import com.nashss.se.projectsyncup.activity.results.DeleteTaskFromProjectResult;

/**
 * RemoveTaskFromProjectLambda class extends the LambdaActivityRunner and implements the RequestHandler interface.
 * This class provides the implementation for the handleRequest method, which is used to remove a task from a project.
 */
public class DeleteTaskFromProjectLambda
        extends LambdaActivityRunner<DeleteTaskFromProjectRequest, DeleteTaskFromProjectResult>
        implements RequestHandler<LambdaRequest<DeleteTaskFromProjectRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteTaskFromProjectRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteTaskFromProjectRequest.builder()
                                .withTaskId(path.get("taskId"))
                                .withProjectId(path.get("projectId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteTaskFromProjectActivity().handleRequest(request)
        );
    }
}


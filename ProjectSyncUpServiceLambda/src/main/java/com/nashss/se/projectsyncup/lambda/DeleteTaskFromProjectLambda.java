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
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteTaskFromProjectRequest>, LambdaResponse> {

    /**
     * Takes in an AuthenticatedLambdaRequest and Context returns a LambdaResponse.
     * handleRequest used to create a new project using the super class's runActivity method.
     * Input first converted to a CreateProjectRequest object, then used to create new CreateProjectRequest.
     * New CreateProjectRequest then passed to handleRequest of CreateProjectActivity created new project.
     *
     * @param input   An AuthenticatedLambdaRequest containing the request to create a new project.
     * @param context The context in which this method is executed.
     * @return A LambdaResponse indicating the result of the request.
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteTaskFromProjectRequest> input, Context context) {
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


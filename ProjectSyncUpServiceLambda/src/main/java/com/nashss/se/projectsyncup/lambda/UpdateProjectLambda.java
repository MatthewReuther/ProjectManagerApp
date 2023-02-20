package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.UpdateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.UpdateProjectResult;


/**
 * UpdateProjectLambda class extends the LambdaActivityRunner and implements the RequestHandler interface.
 * This class provides the implementation for the handleRequest method, which is used to create a new project.
 */
public class UpdateProjectLambda
        extends LambdaActivityRunner<UpdateProjectRequest, UpdateProjectResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateProjectRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateProjectRequest> input, Context context) {
        return super.runActivity(
                () -> {
                UpdateProjectRequest unauthenticatedRequest = input.fromBody(UpdateProjectRequest.class);
                return input.fromUserClaims(claims ->
                    UpdateProjectRequest.builder()
                        .withProjectId(unauthenticatedRequest.getProjectId())
                        .withProjectName(unauthenticatedRequest.getProjectName())
                        .withProjectDescription(unauthenticatedRequest.getProjectDescription())
                        .withProjectStatus(unauthenticatedRequest.getProjectStatus())
                        .build());
                },
                (request, serviceComponent) ->
                    serviceComponent.provideUpdateProjectActivity().handleRequest(request)
        );
    }
}
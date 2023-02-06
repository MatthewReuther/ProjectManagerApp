package com.nashss.se.projectsyncup.lambda;

import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateProjectLambda
        extends LambdaActivityRunner<CreateProjectRequest, CreateProjectResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateProjectRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateProjectRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateProjectRequest unauthenticatedRequest = input.fromBody(CreateProjectRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateProjectRequest.builder()
                                    .withProjectName(unauthenticatedRequest.getProjectName())
                                    .withProjectDescription(unauthenticatedRequest.getProjectDescription())
                                    .withCreatedById(claims.get("createdById"))
                                    .withProjectStatus(unauthenticatedRequest.getProjectStatus())
                                    .withProjectTasks(unauthenticatedRequest.getProjectTasks())
                                    .withProjectMembers(unauthenticatedRequest.getProjectMembers())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateProjectActivity().handleRequest(request)
        );
    }
}

package com.nashss.se.projectsyncup.lambda;

import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import com.nashss.se.projectsyncup.activity.results.CreateProjectResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * CreateProjectLambda class extends the LambdaActivityRunner and implements the RequestHandler interface.
 * This class provides the implementation for the handleRequest method, which is used to create a new project.
 */
public class CreateProjectLambda
        extends LambdaActivityRunner<CreateProjectRequest, CreateProjectResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateProjectRequest>, LambdaResponse> {

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
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateProjectRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateProjectRequest unauthenticatedRequest = input.fromBody(CreateProjectRequest.class);
            return input.fromUserClaims(claims ->
                CreateProjectRequest.builder()
                        .withProjectName(unauthenticatedRequest.getProjectName())
                        .withProjectDescription(unauthenticatedRequest.getProjectDescription())
                        .withCreatedById(claims.get("name"))
//                        .withCreatedById(claims.get("sub"))
//                        .withProjectStatus(unauthenticatedRequest.getProjectStatus())
//                        .withProjectTasks(unauthenticatedRequest.getProjectTasks())
//                        .withProjectMembers(unauthenticatedRequest.getProjectMembers())
                        .build());
            }, (request, serviceComponent) ->
                        serviceComponent.provideCreateProjectActivity().handleRequest(request)
        );
    }
}

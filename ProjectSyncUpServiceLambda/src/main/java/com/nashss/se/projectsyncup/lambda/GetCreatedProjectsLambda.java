package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.GetCreatedProjectsRequest;
import com.nashss.se.projectsyncup.activity.requests.GetProjectRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectResult;


/**
 * The `GetCreatedProjectsLambda` class is an implementation of the AWS Lambda function that retrieves a project.
 * This class extends the `LambdaActivityRunner` and implements the `RequestHandler` interface.
 *
 */
public class GetCreatedProjectsLambda
        extends LambdaActivityRunner<GetProjectRequest, GetProjectResult>
        implements RequestHandler<LambdaRequest<GetProjectRequest>, LambdaResponse> {

    /**
     * Handles a request to retrieve a project by using the provided `GetCreatedProjectsRequest` instance.
     * This method delegates the request handling to the `LambdaActivityRunner` superclass.
     *
     * @param input the `LambdaRequest` object that holds the `GetProjectRequest` instance
     * @param context the AWS Lambda context object
     * @return the result of the request as a `LambdaResponse` object
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetCreatedProjectsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetProjectRequest.builder()
                                .withProjectId(path.get("projectId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetProjectActivity().handleRequest(request)
                );
    }
}

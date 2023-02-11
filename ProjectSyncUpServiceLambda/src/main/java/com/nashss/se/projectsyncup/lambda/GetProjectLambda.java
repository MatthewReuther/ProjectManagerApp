package com.nashss.se.projectsyncup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.projectsyncup.activity.requests.GetProjectRequest;
import com.nashss.se.projectsyncup.activity.results.GetProjectResult;


public class GetProjectLambda {
            extends LambdaActivityRunner<GetProjectRequest, GetProjectResult>
            implements RequestHandler<LambdaRequest<GetProjectRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetProjectRequest> input, Context context) {

            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetProjectRequest.builder()

                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetProjectActivity().handleRequest(request)
            );
        }
    }

}

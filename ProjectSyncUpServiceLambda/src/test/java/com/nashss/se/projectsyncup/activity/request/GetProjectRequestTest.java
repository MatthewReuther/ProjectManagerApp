package com.nashss.se.projectsyncup.activity.request;

import com.nashss.se.projectsyncup.activity.requests.GetProjectRequest;

import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetProjectRequestTest {

    @Test
    public void handleRequest_getProjectById_returnsProjectWithGivenId() {
        String projectId = ProjectSyncUpServiceUtils.generateProjectId();

        GetProjectRequest request = GetProjectRequest.builder()
                .withProjectId(projectId)
                .build();

        assertEquals(projectId, request.getProjectId());
    }

}

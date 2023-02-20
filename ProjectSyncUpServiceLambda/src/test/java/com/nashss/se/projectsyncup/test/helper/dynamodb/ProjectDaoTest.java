package com.nashss.se.projectsyncup.test.helper.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.projectsyncup.dynamodb.ProjectDao;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProjectDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private ProjectDao projectDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        projectDao = new ProjectDao(dynamoDBMapper);
    }
    @Test
    public void getProject_withProjectId_callsMapperWithPartitionKey() {
        // GIVEN
        String projectId = ProjectSyncUpServiceUtils.generateUniqueId();
        when(dynamoDBMapper.load(Project.class, projectId)).thenReturn(new Project());

        // WHEN
        Project project = projectDao.getProject(projectId);

        // THEN
        assertNotNull(project);
        verify(dynamoDBMapper).load(Project.class, projectId);

    }
}

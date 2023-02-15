package com.nashss.se.projectsyncup.converters;

import com.google.common.collect.Sets;
import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.models.ProjectModel;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;
import org.junit.jupiter.api.Test;

import static com.nashss.se.projectsyncup.utils.CollectionUtils.copyToSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toProjectModel_withTaskAndMembers_convertsProject() {

        Project project = new Project();
        project.setProjectId(ProjectSyncUpServiceUtils.generateUniqueId());
        project.setProjectName("name");
        project.setProjectDescription("exprectedDescription");
        project.setProjectStatus("Not Started");
        project.setCreatedById("expectedUser");
//        project.setProjectTasks(Sets.newHashSet("Update Links", "Remove Border..."));
//        project.setProjectMembers(Sets.newHashSet("Mark", "Ben"));

        ProjectModel projectModel = modelConverter.toProjectModel(project);
        assertEquals(project.getProjectId(), projectModel.getProjectId());
        assertEquals(project.getProjectName(), projectModel.getProjectName());
        assertEquals(project.getProjectDescription(), projectModel.getProjectDescription());
        assertEquals(project.getProjectStatus(), projectModel.getProjectStatus());
        assertEquals(project.getCreatedById(), projectModel.getCreatedById());
        assertEquals(project.getProjectStatus(), projectModel.getProjectStatus());
//        assertEquals(project.getProjectTasks(), copyToSet(projectModel.getProjectTasks()));
//        assertEquals(project.getProjectMembers(), copyToSet(projectModel.getProjectMembers()));

    }
}

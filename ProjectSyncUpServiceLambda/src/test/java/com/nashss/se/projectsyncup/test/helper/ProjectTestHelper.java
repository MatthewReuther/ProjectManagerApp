package com.nashss.se.projectsyncup.test.helper;

import com.nashss.se.projectsyncup.dynamodb.models.Project;
import com.nashss.se.projectsyncup.dynamodb.models.Task;
import com.nashss.se.projectsyncup.utils.ProjectSyncUpServiceUtils;

import java.util.ArrayList;
import java.util.List;

public final class ProjectTestHelper {
//    private ProjectTestHelper() {
//    }
//
//    public static Project generateProject() {
//        return generateProjectWithTasks(1);
//    }
//
//    public static Project generateProjectWithTasks(int amountOfTasks) {
//        Project project = new Project();
//
//        project.setProjectId(ProjectSyncUpServiceUtils.generateUniqueId());
//        project.setProjectName("Website Re-design");
//        project.setProjectDescription("New Website Design");
//        project.setProjectStatus("In Progress");
//        project.setCreatedById("Mark");
//
//
//        List<Task> projectTasks = new ArrayList<>();
//        for (int i = 0; i < amountOfTasks; i++) {
//            projectTasks.add(ProjectTaskTestHelper.generateTask(i));
//        }
//        project.setProjectTasks(projectTasks);
//        project.setProjectMembers(null);
//
//        return project;
//    }
}
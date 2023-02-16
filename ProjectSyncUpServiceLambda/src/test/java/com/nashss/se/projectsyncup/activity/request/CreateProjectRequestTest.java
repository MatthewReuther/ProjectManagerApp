package com.nashss.se.projectsyncup.activity.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.projectsyncup.activity.requests.CreateProjectRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateProjectRequestTest {


    @Test
    public void testJsonDeserialize() throws IOException {
        // Define the expected values
        String expectedProjectName = "Test Project";
        String expectedProjectDescription = "This is a test project.";
        String expectedProjectStatus = "Active";
        String expectedCreatedById = "user1";
//        List<String> expectedProjectTasks = Arrays.asList("Task 1", "Task 2");
//        List<String> expectedProjectMembers = Arrays.asList("user2", "user3");

        // Define the JSON string
        String json = "{" +
                "\"projectName\":\"" + expectedProjectName + "\"," +
                "\"projectDescription\":\"" + expectedProjectDescription + "\"," +
                "\"projectStatus\":\"" + expectedProjectStatus + "\"," +
                "\"createdById\":\"" + expectedCreatedById + "\"" +
//                "\"projectTasks\":[\"" + String.join("\",\"", expectedProjectTasks) + "\"]," +
//                "\"projectMembers\":[\"" + String.join("\",\"", expectedProjectMembers) + "\"]" +
                "}";

        // Use the ObjectMapper to deserialize the JSON string
        ObjectMapper mapper = new ObjectMapper();
        CreateProjectRequest request = mapper.readValue(json, CreateProjectRequest.class);

        // Assert that the deserialized values match the expected values
        assertEquals(expectedProjectName, request.getProjectName());
        assertEquals(expectedProjectDescription, request.getProjectDescription());
        assertEquals(expectedProjectStatus, request.getProjectStatus());
        assertEquals(expectedCreatedById, request.getCreatedById());
//        assertEquals(expectedProjectTasks, request.getProjectTasks());
//        assertEquals(expectedProjectMembers, request.getProjectMembers());
    }
}

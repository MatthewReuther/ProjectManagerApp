package com.nashss.se.projectsyncup.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for Project Sync Up service.
 */
public final class ProjectSyncUpServiceUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ProjectSyncUpServiceUtils() {
    }

    /**
     * Generates a random alphanumeric string of 8 characters to be used as a project ID.
     *
     * @return the generated project ID
     */
    public static String generateProjectId() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}

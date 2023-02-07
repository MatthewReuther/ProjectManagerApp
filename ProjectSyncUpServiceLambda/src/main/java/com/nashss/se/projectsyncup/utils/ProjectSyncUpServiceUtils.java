package com.nashss.se.projectsyncup.utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class ProjectSyncUpServiceUtils {

    private ProjectSyncUpServiceUtils() {
    }

    public static String generateProjectId() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}

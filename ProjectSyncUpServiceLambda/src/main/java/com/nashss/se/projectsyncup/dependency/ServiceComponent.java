package com.nashss.se.projectsyncup.dependency;

import com.nashss.se.projectsyncup.activity.CreateProjectActivity;

import javax.inject.Singleton;

@Singleton
public interface ServiceComponent {
    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */
    CreateProjectActivity provideCreateProjectActivity();
}

package com.nashss.se.projectsyncup.activity.results;
import com.nashss.se.projectsyncup.models.ProjectModel;

public class GetProjectResult {
    private final ProjectModel projectModel;

    private GetProjectResult(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    @Override
    public String toString() {
        return "GetProjectResult{" +
                "project=" + projectModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProjectModel projectModel;

        public Builder withPlaylist(ProjectModel projectModel) {
            this.projectModel = projectModel;
            return this;
        }

        public GetProjectResult build() {
            return new GetProjectResult(projectModel);
        }
    }
}

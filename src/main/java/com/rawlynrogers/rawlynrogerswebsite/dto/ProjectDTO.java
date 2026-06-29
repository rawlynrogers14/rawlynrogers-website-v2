package com.rawlynrogers.rawlynrogerswebsite.dto;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO {

    private Long id;
    private String title;
    private String description;
    private String technologies;
    private String githubLink;
    private String projectDate;

    private List<Long> contributorIds = new ArrayList<>();
    private List<Long> slideshowIds = new ArrayList<>();

    public ProjectDTO() {
    }

    public ProjectDTO(Long id,
                      String title,
                      String description,
                      String technologies,
                      String githubLink,
                      String projectDate,
                      List<Long> contributorIds,
                      List<Long> slideshowIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.githubLink = githubLink;
        this.projectDate = projectDate;
        this.contributorIds = contributorIds;
        this.slideshowIds = slideshowIds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public List<Long> getContributorIds() {
        return contributorIds;
    }

    public List<Long> getSlideshowIds() {
        return slideshowIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public void setContributorIds(List<Long> contributorIds) {
        this.contributorIds = contributorIds;
    }

    public void setSlideshowIds(List<Long> slideshowIds) {
        this.slideshowIds = slideshowIds;
    }
}
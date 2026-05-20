package com.rawlynrogers.rawlynrogerswebsite.dto;

public class ProjectDTO {

    private Long id;
    private String title;
    private String description;
    private String technologies;
    private String githubLink;
    private String projectDate;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String title, String description, String technologies, String githubLink, String projectDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.githubLink = githubLink;
        this.projectDate = projectDate;
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
}
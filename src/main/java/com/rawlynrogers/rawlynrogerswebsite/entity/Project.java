package com.rawlynrogers.rawlynrogerswebsite.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String technologies;

    private String githubLink;

    private String projectDate;

    @ManyToMany
    @JoinTable(
            name = "project_contributors",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<Contact> contributors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "project_slideshow_media",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private List<Media> slideshow = new ArrayList<>();

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public List<Contact> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contact> contributors) {
        this.contributors = contributors;
    }

    public List<Media> getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(List<Media> slideshow) {
        this.slideshow = slideshow;
    }
}
package com.rawlynrogers.rawlynrogerswebsite.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {

    private Long id;
    private String name;
    private Boolean active = false;

    private Long contactId;

    private List<Long> projectIds = new ArrayList<>();

    public ProfileDTO() {
    }

    public ProfileDTO(Long id,
                      String name,
                      Boolean active,
                      Long contactId,
                      List<Long> projectIds) {

        this.id = id;
        this.name = name;
        this.active = active != null ? active : false;
        this.contactId = contactId;
        this.projectIds = projectIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public void setActive(Boolean active) {
        this.active = active != null ? active : false;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }
}
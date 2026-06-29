package com.rawlynrogers.rawlynrogerswebsite.dto;

import java.util.ArrayList;
import java.util.List;

public class ContactDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String githubLink;
    private String linkedinLink;

    private Long profileImageId;
    private Long resumePdfId;
    private Long cvPdfId;

    private String aboutMe;

    private List<Long> slideshowIds = new ArrayList<>();

    public ContactDTO() {
    }

    public ContactDTO(Long id,
                      String firstName,
                      String middleName,
                      String lastName,
                      String email,
                      String githubLink,
                      String linkedinLink,
                      Long profileImageId,
                      Long resumePdfId,
                      Long cvPdfId,
                      String aboutMe,
                      List<Long> slideshowIds) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.githubLink = githubLink;
        this.linkedinLink = linkedinLink;
        this.profileImageId = profileImageId;
        this.resumePdfId = resumePdfId;
        this.cvPdfId = cvPdfId;
        this.aboutMe = aboutMe;
        this.slideshowIds = slideshowIds;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getGithubLink() { return githubLink; }
    public String getLinkedinLink() { return linkedinLink; }
    public Long getProfileImageId() { return profileImageId; }
    public Long getResumePdfId() { return resumePdfId; }
    public Long getCvPdfId() { return cvPdfId; }
    public String getAboutMe() { return aboutMe; }
    public List<Long> getSlideshowIds() { return slideshowIds; }

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }
    public void setLinkedinLink(String linkedinLink) { this.linkedinLink = linkedinLink; }
    public void setProfileImageId(Long profileImageId) { this.profileImageId = profileImageId; }
    public void setResumePdfId(Long resumePdfId) { this.resumePdfId = resumePdfId; }
    public void setCvPdfId(Long cvPdfId) { this.cvPdfId = cvPdfId; }
    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }
    public void setSlideshowIds(List<Long> slideshowIds) { this.slideshowIds = slideshowIds; }
}
package com.rawlynrogers.rawlynrogerswebsite.dto;

public class ContactDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String githubLink;
    private String linkedinLink;
    private String profileImageUrl;
    private String resumePdfUrl;
    private String cvPdfUrl;
    private String aboutMe;

    public ContactDTO() {
    }

    public ContactDTO(Long id, String firstName, String middleName, String lastName,
                      String email, String githubLink, String linkedinLink,
                      String profileImageUrl, String resumePdfUrl,
                      String cvPdfUrl, String aboutMe) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.githubLink = githubLink;
        this.linkedinLink = linkedinLink;
        this.profileImageUrl = profileImageUrl;
        this.resumePdfUrl = resumePdfUrl;
        this.cvPdfUrl = cvPdfUrl;
        this.aboutMe = aboutMe;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getGithubLink() { return githubLink; }
    public String getLinkedinLink() { return linkedinLink; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public String getResumePdfUrl() { return resumePdfUrl; }
    public String getCvPdfUrl() { return cvPdfUrl; }
    public String getAboutMe() { return aboutMe; }

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }
    public void setLinkedinLink(String linkedinLink) { this.linkedinLink = linkedinLink; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    public void setResumePdfUrl(String resumePdfUrl) { this.resumePdfUrl = resumePdfUrl; }
    public void setCvPdfUrl(String cvPdfUrl) { this.cvPdfUrl = cvPdfUrl; }
    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }
}
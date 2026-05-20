package com.rawlynrogers.rawlynrogerswebsite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private String email;
    private String githubLink;
    private String linkedinLink;
    private String profileImageUrl;

    private String resumePdfUrl;
    private String cvPdfUrl;

    @Column(length = 5000)
    private String aboutMe;

    public Contact() {
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
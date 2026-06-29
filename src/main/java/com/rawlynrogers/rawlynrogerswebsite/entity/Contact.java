package com.rawlynrogers.rawlynrogerswebsite.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "profile_image_id")
    private Media profileImage;

    @ManyToOne
    @JoinColumn(name = "resume_pdf_id")
    private Media resumePdf;

    @ManyToOne
    @JoinColumn(name = "cv_pdf_id")
    private Media cvPdf;

    @Column(length = 5000)
    private String aboutMe;

    @ManyToMany
    @JoinTable(
            name = "contact_slideshow_media",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private List<Media> slideshow = new ArrayList<>();

    public Contact() {
    }

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getGithubLink() { return githubLink; }
    public String getLinkedinLink() { return linkedinLink; }
    public Media getProfileImage() { return profileImage; }
    public Media getResumePdf() { return resumePdf; }
    public Media getCvPdf() { return cvPdf; }
    public String getAboutMe() { return aboutMe; }
    public List<Media> getSlideshow() { return slideshow; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setGithubLink(String githubLink) { this.githubLink = githubLink; }
    public void setLinkedinLink(String linkedinLink) { this.linkedinLink = linkedinLink; }
    public void setProfileImage(Media profileImage) { this.profileImage = profileImage; }
    public void setResumePdf(Media resumePdf) { this.resumePdf = resumePdf; }
    public void setCvPdf(Media cvPdf) { this.cvPdf = cvPdf; }
    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }
    public void setSlideshow(List<Media> slideshow) { this.slideshow = slideshow; }
}
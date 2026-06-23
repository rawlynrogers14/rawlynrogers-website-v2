import { useEffect, useState } from "react";
import { getContact } from "../api/contactApi";
import "./Resume.css";

function Resume() {
  const [contact, setContact] = useState(null);
  const backendUrl = "http://localhost:8080";

  useEffect(() => {
    getContact()
      .then((data) => setContact(data))
      .catch((error) => console.error("Failed to load resume links:", error));
  }, []);

  const resumeUrl = contact?.resumePdfUrl
    ? `${backendUrl}${contact.resumePdfUrl}`
    : "";

  const cvUrl = contact?.cvPdfUrl
    ? `${backendUrl}${contact.cvPdfUrl}`
    : "";

  return (
    <section className="page resume-container">
      <div className="resume-header">
        <h1>Resume & CV</h1>
        <p>
          Computer Engineering graduate, U.S. Army veteran, and Security+
          certified professional with experience in software development,
          backend systems, networking, cybersecurity, and full-stack web
          applications.
        </p>
      </div>

      <div className="resume-card">
        <h2>Professional Resume</h2>
        <p>
          A concise, recruiter-friendly resume focused on my technical skills,
          education, military IT experience, software projects, and professional
          qualifications.
        </p>

        {resumeUrl && (
          <a
            href={resumeUrl}
            className="download-button"
            target="_blank"
            rel="noreferrer"
          >
            Open Resume
          </a>
        )}
      </div>

      <div className="resume-card">
        <h2>Curriculum Vitae</h2>
        <p>
          A longer-form CV with expanded details on my education, projects,
          certifications, military experience, technical skills, and
          accomplishments.
        </p>

        {cvUrl && (
          <a
            href={cvUrl}
            className="download-button"
            target="_blank"
            rel="noreferrer"
          >
            Open CV
          </a>
        )}
      </div>
    </section>
  );
}

export default Resume;
import { useEffect, useState } from "react";
import { getContactById } from "../api/contactApi";
import { getActiveProfile } from "../api/profileApi";
import { getMediaById } from "../api/mediaApi";
import "./Resume.css";

function Resume() {
  const [resumePdf, setResumePdf] = useState(null);
  const [cvPdf, setCvPdf] = useState(null);
  const backendUrl = "";

  useEffect(() => {
    document.title = "Resume";
  }, []);

  useEffect(() => {
    async function loadResumeData() {
      try {
        const activeProfile = await getActiveProfile();

        if (activeProfile?.contactId) {
          const contact = await getContactById(activeProfile.contactId);

          if (contact?.resumePdfId) {
            const resume = await getMediaById(contact.resumePdfId);
            setResumePdf(resume);
          }

          if (contact?.cvPdfId) {
            const cv = await getMediaById(contact.cvPdfId);
            setCvPdf(cv);
          }
        }
      } catch (error) {
        console.error("Failed to load resume links:", error);
      }
    }

    loadResumeData();
  }, []);

  return (
    <section className="page resume-container">
      <div className="resume-header">
        <h1>Resume</h1>
      </div>

      {resumePdf?.filePath && (
        <div className="resume-card">
          <h2>Professional Resume</h2>

          {resumePdf.description && <p>{resumePdf.description}</p>}

          <a
            href={`${backendUrl}${resumePdf.filePath}`}
            className="download-button"
            target="_blank"
            rel="noreferrer"
          >
            Open Resume
          </a>
        </div>
      )}

      {cvPdf?.filePath && (
        <div className="resume-card">
          <h2>Curriculum Vitae</h2>

          {cvPdf.description && <p>{cvPdf.description}</p>}

          <a
            href={`${backendUrl}${cvPdf.filePath}`}
            className="download-button"
            target="_blank"
            rel="noreferrer"
          >
            Open CV
          </a>
        </div>
      )}
    </section>
  );
}

export default Resume;
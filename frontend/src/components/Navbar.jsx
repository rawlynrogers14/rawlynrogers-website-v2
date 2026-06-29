import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getContactById } from "../api/contactApi";
import { getActiveProfile } from "../api/profileApi";
import "./Navbar.css";

function Navbar() {
  const [profile, setProfile] = useState(null);
  const [contact, setContact] = useState(null);

  useEffect(() => {
    async function loadNavbarData() {
      try {
        const activeProfile = await getActiveProfile();
        setProfile(activeProfile);

        if (activeProfile?.contactId) {
          const profileContact = await getContactById(activeProfile.contactId);
          setContact(profileContact);
        }
      } catch (err) {
        console.error("Navbar data failed to load:", err);
      }
    }

    loadNavbarData();
  }, []);

  const brandName =
    contact?.firstName && contact?.lastName
      ? `${contact.firstName} ${contact.lastName}`
      : profile?.name || "Rawlyn Rogers";

  return (
    <nav className="navbar">
      <Link to="/" className="navbar-brand">
        {brandName}
      </Link>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/projects">Projects</Link>
        <Link to="/resume">Resume</Link>
      </div>

      <div className="contact-buttons">
        {contact?.email && <a href={`mailto:${contact.email}`}>Email Me</a>}

        {contact?.linkedinLink && (
          <a href={contact.linkedinLink} target="_blank" rel="noreferrer">
            LinkedIn
          </a>
        )}

        {contact?.githubLink && (
          <a href={contact.githubLink} target="_blank" rel="noreferrer">
            GitHub
          </a>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
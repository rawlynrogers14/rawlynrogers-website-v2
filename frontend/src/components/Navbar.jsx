import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getContact } from "../api/contactApi";
import "./Navbar.css";

function Navbar() {
  const [contact, setContact] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getContact()
      .then((data) => {
        console.log("Contact stored in state:", data);
        setContact(data);
      })
      .catch((err) => {
        console.error("Contact API failed:", err);
        setError(err);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  return (
    <nav className="navbar">
      <Link to="/" className="navbar-brand">
        {contact
          ? `${contact.firstName} ${contact.lastName}`
          : "Rawlyn Rogers"}
      </Link>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/projects">Projects</Link>
        <Link to="/resume">Resume</Link>
      </div>

      <div className="contact-buttons">
        {loading && <span>Loading contact...</span>}

        {error && <span>Contact failed to load</span>}

        {!loading && !error && contact && (
          <>
            <a href={`mailto:${contact.email}`}>Email Me</a>

            <a href={contact.linkedinLink} target="_blank" rel="noreferrer">
              LinkedIn
            </a>

            <a href={contact.githubLink} target="_blank" rel="noreferrer">
              GitHub
            </a>
          </>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
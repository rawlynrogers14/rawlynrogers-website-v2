import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getContact } from "../api/contactApi";
import "./Home.css";

function Home() {
  const [contact, setContact] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getContact()
      .then((data) => setContact(data))
      .catch((error) => console.error("Failed to load contact:", error))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <section className="page home-container">
        <p>Loading about me...</p>
      </section>
    );
  }

  return (
    <section className="page home-container">
      <div className="about-section">
        {contact?.profileImageUrl && (
          <img
            src={`http://localhost:8080${contact.profileImageUrl}`}
            alt={`${contact.firstName} ${contact.lastName}`}
            className="profile-image"
          />
        )}

        <div className="about-content">
          <h1>About Me</h1>
          <p>{contact?.aboutMe}</p>
        </div>
      </div>

      <div className="slideshow-section">

        <div className="slideshow-placeholder">
          Future slideshow component
        </div>
      </div>
    </section>
  );
}

export default Home;
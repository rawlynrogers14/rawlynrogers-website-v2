import { useEffect, useState } from "react";
import { getContact } from "../api/contactApi";

function Home() {
  const [contact, setContact] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getContact()
      .then((data) => {
        setContact(data);
      })
      .catch((error) => {
        console.error("Failed to load contact:", error);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <section className="page">
        <p>Loading about me...</p>
      </section>
    );
  }

  return (
    <section className="page">
      <h1>About Me</h1>

      {contact?.profileImageUrl && (
        <img
          src={`http://localhost:8080${contact.profileImageUrl}`}
          alt={`${contact.firstName} ${contact.lastName}`}
          className="profile-image"
        />
      )}

      <p>{contact?.aboutMe}</p>

      <div className="slideshow-placeholder">
        Slideshow of personal/professional pictures will go here.
      </div>
    </section>
  );
}

export default Home;
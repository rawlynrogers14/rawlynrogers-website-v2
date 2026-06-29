import { useEffect, useState } from "react";
import { getContactById } from "../api/contactApi";
import { getActiveProfile } from "../api/profileApi";
import { getMediaById } from "../api/mediaApi";
import ImageCarousel from "../components/ImageCarousel";
import "./Home.css";

function Home() {
  const [contact, setContact] = useState(null);
  const [loading, setLoading] = useState(true);
  const [profileImage, setProfileImage] = useState(null);
  const [slideshowImages, setSlideshowImages] = useState([]);

  useEffect(() => {
    async function loadHomeData() {
      try {
        const activeProfile = await getActiveProfile();

        if (activeProfile?.contactId) {
          const profileContact = await getContactById(activeProfile.contactId);
          setContact(profileContact);

          if (profileContact?.profileImageId) {
            const image = await getMediaById(profileContact.profileImageId);
            setProfileImage(image);
          }
          if (profileContact?.slideshowIds?.length > 0) {
            const slideshowMedia = await Promise.all(
              profileContact.slideshowIds.map((mediaId) => getMediaById(mediaId))
            );

            const imageMedia = slideshowMedia.filter((media) =>
              media.fileType?.startsWith("image/")
            );

            setSlideshowImages(imageMedia);
          }

        }
      } catch (error) {
        console.error("Failed to load home data:", error);
      } finally {
        setLoading(false);
      }
    }
    loadHomeData();
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
        {profileImage?.filePath && (
          <img
            src={`http://localhost:8080${profileImage.filePath}`}
            alt={`${contact.firstName || ""} ${contact.lastName || ""}`}
            className="profile-image"
          />
        )}

        {contact?.aboutMe && (
          <div className="about-content">
            <h1>About Me</h1>
            <p>{contact.aboutMe}</p>
          </div>
        )}
      </div>

      {slideshowImages.length > 0 && (
        <div className="slideshow-section">
          <ImageCarousel images={slideshowImages} />
        </div>
      )}
    </section>
  );
}

export default Home;
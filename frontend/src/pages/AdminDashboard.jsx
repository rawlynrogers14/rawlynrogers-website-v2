import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { adminApi, logout } from "../api/adminApi";
import { adminEntities } from "../admin/adminEntities";
import EntityManager from "../admin/EntityManager";
import { ADMIN_CONFIG } from "../admin/adminConfig";
import "./admin.css";

export default function AdminDashboard() {
  const navigate = useNavigate();
  const entityKeys = Object.keys(adminEntities);

  const [activeEntityKey, setActiveEntityKey] = useState(entityKeys[0]);
  const [showDisclaimer, setShowDisclaimer] = useState(true);
  const [profiles, setProfiles] = useState([]);
  const [activeProfileId, setActiveProfileId] = useState("");
  const [profileError, setProfileError] = useState("");
  const [activeProfileMessage, setActiveProfileMessage] = useState("");

  const activeEntity = adminEntities[activeEntityKey];

  useEffect(() => {
    loadProfiles();
  }, []);

  useEffect(() => {
    if (!activeProfileMessage) return;

    const timer = setTimeout(() => {
      setActiveProfileMessage("");
    }, 5000);

    return () => clearTimeout(timer);
  }, [activeProfileMessage]);

  async function loadProfiles() {
    try {
      setProfileError("");

      const data = await adminApi.getAll(adminEntities.profiles);
      const profileList = Array.isArray(data) ? data : [data];

      setProfiles(profileList);

      const activeProfile = profileList.find((profile) => profile.active);

      if (activeProfile) {
        setActiveProfileId(String(activeProfile.id));
      }
    } catch (err) {
      console.error(err);
      setProfileError("Could not load profiles.");
    }
  }

  async function handleActiveProfileChange(profileId) {
    try {
      setProfileError("");
      setActiveProfileMessage("");

      const oldProfile = profiles.find(
        (profile) => String(profile.id) === String(activeProfileId)
      );

      const newProfile = profiles.find(
        (profile) => String(profile.id) === String(profileId)
      );

      setActiveProfileId(profileId);

      await adminApi.activateProfile(profileId);

      setActiveProfileMessage(
        `Active profile changed from ${oldProfile?.name || "none"} to ${
          newProfile?.name || "selected profile"
        }.`
      );

      await loadProfiles();
    } catch (err) {
      console.error(err);
      setProfileError("Could not update active profile.");
    }
  }

  function handleLogout() {
    logout();
    navigate("/admin/login");
  }

  return (
    <main className="admin-dashboard-page">
      {showDisclaimer && (
        <div className="admin-modal-backdrop">
          <div className="admin-disclaimer-modal">
            <h2>{ADMIN_CONFIG.disclaimerTitle}</h2>

            <p>{ADMIN_CONFIG.disclaimer}</p>

            <button onClick={() => setShowDisclaimer(false)}>
              {ADMIN_CONFIG.acknowledgeButton}
            </button>
          </div>
        </div>
      )}

      <header className="admin-top-bar">
        <div className="admin-top-left">
          <h1>{ADMIN_CONFIG.title}</h1>

          <label className="admin-active-profile-select">
            Active Profile
            <select
              value={activeProfileId}
              onChange={(e) => handleActiveProfileChange(e.target.value)}
            >
              <option value="">Select active profile</option>

              {profiles.map((profile) => (
                <option key={profile.id} value={profile.id}>
                  {profile.name || `Profile #${profile.id}`}
                </option>
              ))}
            </select>
          </label>
        </div>

        <button className="admin-logout-button" onClick={handleLogout}>
          Logout
        </button>
      </header>

      {profileError && <p className="admin-error">{profileError}</p>}
      {activeProfileMessage && (
        <p className="admin-success">{activeProfileMessage}</p>
      )}

      <section className="admin-control-bar">
        <label>
          Select Record Type
          <select
            value={activeEntityKey}
            onChange={(e) => setActiveEntityKey(e.target.value)}
          >
            {entityKeys.map((key) => (
              <option key={key} value={key}>
                {adminEntities[key].label}
              </option>
            ))}
          </select>
        </label>
      </section>

     <EntityManager entity={activeEntity} />
    </main>
  );
}
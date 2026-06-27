import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { logout } from "../api/adminApi";
import { adminEntities } from "../admin/adminEntities";
import EntityManager from "../admin/EntityManager";
import { ADMIN_CONFIG } from "../admin/adminConfig";
import "./admin.css";

export default function AdminDashboard() {
  const navigate = useNavigate();
  const entityKeys = Object.keys(adminEntities);

  const [activeEntityKey, setActiveEntityKey] = useState(entityKeys[0]);
  const [showDisclaimer, setShowDisclaimer] = useState(true);

  const activeEntity = adminEntities[activeEntityKey];

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
        <h1>{ADMIN_CONFIG.title}</h1>

        <button className="admin-logout-button" onClick={handleLogout}>
          Logout
        </button>
      </header>

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
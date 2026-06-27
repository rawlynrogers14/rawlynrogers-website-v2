import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/adminApi";
import "./admin.css";

export default function AdminLogin() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: "",
    password: "",
  });

  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");

    try {
      await login(form.username, form.password);
      navigate("/admin");
    } catch {
      setError("Invalid username or password.");
    }
  }

  return (
    <main className="admin-login-page">
      <form className="admin-login-card" onSubmit={handleSubmit}>
        <div className="admin-login-header">
          <p className="admin-login-eyebrow">Rawlyn Rogers</p>
          <h1>Admin Portal</h1>
          <p>Sign in to manage portfolio records.</p>
        </div>

        {error && <p className="admin-error">{error}</p>}

        <label className="admin-field">
          <span>Username</span>
          <input
            type="text"
            value={form.username}
            onChange={(e) =>
              setForm({
                ...form,
                username: e.target.value,
              })
            }
            autoComplete="username"
          />
        </label>

        <label className="admin-field">
          <span>Password</span>

          <div className="admin-password-wrapper">
            <input
              type={showPassword ? "text" : "password"}
              value={form.password}
              onChange={(e) =>
                setForm({
                  ...form,
                  password: e.target.value,
                })
              }
              autoComplete="current-password"
            />

            <button
              type="button"
              className="admin-password-toggle"
              onClick={() => setShowPassword(!showPassword)}
              aria-label={showPassword ? "Hide password" : "Show password"}
            >
              {showPassword ? "🙈" : "👁️"}
            </button>
          </div>
        </label>

        <button className="admin-login-button" type="submit">
          Sign In
        </button>
      </form>
    </main>
  );
}
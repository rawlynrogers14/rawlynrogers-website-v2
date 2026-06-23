import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar">
      <h2>Rawlyn Rogers</h2>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/projects">Projects</Link>
        <Link to="/resume">Resume</Link>
      </div>

      <div className="contact-buttons">
        <a href="mailto:rawlynrogers@hotmail.com">Email Me</a>
        <a href="https://www.linkedin.com/in/rawlynrogers" target="_blank">
          LinkedIn
        </a>
        <a href="https://github.com/rawlynrogers14" target="_blank">
          GitHub
        </a>
      </div>
    </nav>
  );
}

export default Navbar;
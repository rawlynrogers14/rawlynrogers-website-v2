import { useState } from "react";

const projects = [
  {
    title: "Portfolio Website",
    date: "2026",
    contributors: "Rawlyn Rogers",
    about:
      "A full-stack portfolio website built with React, Spring Boot, PostgreSQL, and AWS.",
    design:
      "Designed with a React frontend, REST API backend, PostgreSQL database, authentication, media uploads, and admin controls.",
    results:
      "Created a scalable personal website to showcase projects, resume documents, and professional experience.",
  },
  {
    title: "Hand Tracking in VR Cockpits",
    date: "2025",
    contributors: "Senior Design Team",
    about:
      "A virtual reality cockpit interaction system using hand tracking and sensor-based input.",
    design:
      "Used Python, C#, Unity, camera-based tracking, and sensor integration to improve VR interaction accuracy.",
    results:
      "Improved hand interaction testing and demonstrated a working prototype for flight-training environments.",
  },
];

function Projects() {
  const [openProject, setOpenProject] = useState(null);

  return (
    <section className="page">
      <h1>Projects</h1>

      {projects.map((project, index) => (
        <div className="project-card" key={index}>
          <button
            className="project-title"
            onClick={() =>
              setOpenProject(openProject === index ? null : index)
            }
          >
            {project.title}
          </button>

          {openProject === index && (
            <div className="project-details">
              <p><strong>Date:</strong> {project.date}</p>
              <p><strong>Contributors:</strong> {project.contributors}</p>

              <h3>About the Project</h3>
              <p>{project.about}</p>

              <h3>Project Design</h3>
              <p>{project.design}</p>

              <h3>Results</h3>
              <p>{project.results}</p>

              <div className="slideshow-placeholder">
                Project slideshow pictures will go here.
              </div>
            </div>
          )}
        </div>
      ))}
    </section>
  );
}

export default Projects;
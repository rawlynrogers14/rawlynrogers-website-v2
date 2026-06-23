import { useEffect, useState } from "react";
import { getProjects } from "../api/projectApi";
import "./Projects.css";

function Projects() {
  const [projects, setProjects] = useState([]);
  const [openProjectId, setOpenProjectId] = useState(null);

  useEffect(() => {
    getProjects()
      .then((data) => {
        const sortedProjects = [...data].sort((a, b) => {
          return new Date(b.projectDate) - new Date(a.projectDate);
        });

        setProjects(sortedProjects);
      })
      .catch((error) => {
        console.error("Failed to load projects:", error);
      });
  }, []);

  function toggleProject(projectId) {
    setOpenProjectId(openProjectId === projectId ? null : projectId);
  }

  return (
    <section className="page">
      <h1>Projects</h1>

      {projects.map((project) => (
        <div className="project-card" key={project.id}>
          <button
                className="project-title"
                onClick={() => toggleProject(project.id)}
            >
                <span className="project-title-text">
                    {project.title}
                </span>

                <span className="project-title-date">
                    {project.projectDate}
                </span>
          </button>

          {openProjectId === project.id && (
            <div className="project-expanded">

                <div className="project-info">

                    <div className="project-section">
                        <h3>Description</h3>
                        <p>{project.description}</p>
                    </div>

                    <div className="project-section">
                        <h3>Tech Stack</h3>
                        <p>{project.technologies}</p>
                    </div>

                    {project.githubLink && (
                        <div className="project-section">
                            <h3>GitHub</h3>

                            <a
                                href={project.githubLink}
                                target="_blank"
                                rel="noreferrer"
                            >
                                View Repository
                            </a>
                        </div>
                    )}

                </div>

                <div className="project-slideshow">

                    <div className="slideshow-box">
                        Images Coming Soon
                    </div>

                </div>

            </div>
          )}
        </div>
      ))}
    </section>
  );
}

export default Projects;
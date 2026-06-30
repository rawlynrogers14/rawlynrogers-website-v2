import { useEffect, useState } from "react";
import { getProjectById } from "../api/projectApi";
import { getActiveProfile } from "../api/profileApi";
import { getMediaById } from "../api/mediaApi";
import ImageCarousel from "../components/ImageCarousel";
import "./Projects.css";


function Projects() {
  const [projects, setProjects] = useState([]);
  const [openProjectId, setOpenProjectId] = useState(null);

  useEffect(() => {
    document.title = "Projects";
  }, []);

  useEffect(() => {
    async function loadProfileProjects() {
      try {
        const activeProfile = await getActiveProfile();

        if (!activeProfile?.projectIds?.length) {
          setProjects([]);
          return;
        }

        const profileProjects = await Promise.all(
          activeProfile.projectIds.map(async (projectId) => {
            const project = await getProjectById(projectId);

            let slideshowImages = [];

            if (project.slideshowIds?.length > 0) {
              const slideshowMedia = await Promise.all(
                project.slideshowIds.map((mediaId) => getMediaById(mediaId))
              );

              slideshowImages = slideshowMedia.filter((media) =>
                media.fileType?.startsWith("image/")
              );
            }

            return {
              ...project,
              slideshowImages,
            };
          })
        );

        const sortedProjects = profileProjects.sort((a, b) => {
          return new Date(b.projectDate) - new Date(a.projectDate);
        });

        setProjects(sortedProjects);
      } catch (error) {
        console.error("Failed to load profile projects:", error);
      }
    }

    loadProfileProjects();
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
            <span className="project-title-text">{project.title}</span>
            <span className="project-title-date">{project.projectDate}</span>
          </button>

          {openProjectId === project.id && (
            <div className="project-expanded">
              <div className="project-info">
                {project.description && (
                  <div className="project-section">
                    <h3>Description</h3>
                    <p>{project.description}</p>
                  </div>
                )}

                {project.technologies && (
                  <div className="project-section">
                    <h3>Tech Stack</h3>
                    <p>{project.technologies}</p>
                  </div>
                )}

                {project.githubLink && (
                  <div className="project-section">
                    <h3>GitHub</h3>
                    <a
                      href={project.githubLink}
                      className="project-button"
                      target="_blank"
                      rel="noreferrer"
                    >
                      View Repository
                    </a>
                  </div>
                )}
              </div>

              {project.slideshowImages?.length > 0 && (
                <div className="project-slideshow">
                  <ImageCarousel images={project.slideshowImages} />
                </div>
              )}
            </div>
          )}
        </div>
      ))}
    </section>
  );
}

export default Projects;
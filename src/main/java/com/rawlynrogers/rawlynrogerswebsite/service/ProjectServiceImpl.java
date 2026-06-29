package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ProjectDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Profile;
import com.rawlynrogers.rawlynrogerswebsite.entity.Contact;
import com.rawlynrogers.rawlynrogerswebsite.entity.Media;
import com.rawlynrogers.rawlynrogerswebsite.entity.Project;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProfileRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ContactRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.MediaRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProfileRepository profileRepository;
    private final ProjectRepository projectRepository;
    private final ContactRepository contactRepository;
    private final MediaRepository mediaRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              ContactRepository contactRepository,
                              MediaRepository mediaRepository,
                              ProfileRepository profileRepository) {
        this.projectRepository = projectRepository;
        this.contactRepository = contactRepository;
        this.mediaRepository = mediaRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        return convertToDTO(project);
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project savedProject = projectRepository.save(project);

        return convertToDTO(savedProject);
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO updatedProjectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        existingProject.setTitle(updatedProjectDTO.getTitle());
        existingProject.setDescription(updatedProjectDTO.getDescription());
        existingProject.setTechnologies(updatedProjectDTO.getTechnologies());
        existingProject.setGithubLink(updatedProjectDTO.getGithubLink());
        existingProject.setProjectDate(updatedProjectDTO.getProjectDate());

        if (updatedProjectDTO.getContributorIds() != null) {
            List<Contact> contributors = contactRepository.findAllById(updatedProjectDTO.getContributorIds());
            existingProject.setContributors(contributors);
        } else {
            existingProject.getContributors().clear();
        }

        if (updatedProjectDTO.getSlideshowIds() != null) {
            List<Media> slideshow = mediaRepository.findAllById(updatedProjectDTO.getSlideshowIds());
            existingProject.setSlideshow(slideshow);
        } else {
            existingProject.getSlideshow().clear();
        }

        Project savedProject = projectRepository.save(existingProject);

        return convertToDTO(savedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        List<Profile> profiles = profileRepository.findAll();

        for (Profile profile : profiles) {
            profile.getProjects().remove(existingProject);
        }

        profileRepository.saveAll(profiles);

        projectRepository.delete(existingProject);
    }

    private ProjectDTO convertToDTO(Project project) {

        List<Long> contributorIds = project.getContributors()
                .stream()
                .map(Contact::getId)
                .toList();

        List<Long> slideshowIds = project.getSlideshow()
                .stream()
                .map(Media::getId)
                .toList();

        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechnologies(),
                project.getGithubLink(),
                project.getProjectDate(),
                contributorIds,
                slideshowIds
        );
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = new Project();

        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setTechnologies(projectDTO.getTechnologies());
        project.setGithubLink(projectDTO.getGithubLink());
        project.setProjectDate(projectDTO.getProjectDate());

        if (projectDTO.getContributorIds() != null) {
            List<Contact> contributors = contactRepository.findAllById(projectDTO.getContributorIds());
            project.setContributors(contributors);
        }

        if (projectDTO.getSlideshowIds() != null) {
            List<Media> slideshow = mediaRepository.findAllById(projectDTO.getSlideshowIds());
            project.setSlideshow(slideshow);
        }

        return project;
    }
}
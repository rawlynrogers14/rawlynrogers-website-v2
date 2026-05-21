package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.ProjectDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Project;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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

        Project savedProject = projectRepository.save(existingProject);

        return convertToDTO(savedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        projectRepository.delete(existingProject);
    }

    private ProjectDTO convertToDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechnologies(),
                project.getGithubLink(),
                project.getProjectDate()
        );
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = new Project();

        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setTechnologies(projectDTO.getTechnologies());
        project.setGithubLink(projectDTO.getGithubLink());
        project.setProjectDate(projectDTO.getProjectDate());

        return project;
    }
}
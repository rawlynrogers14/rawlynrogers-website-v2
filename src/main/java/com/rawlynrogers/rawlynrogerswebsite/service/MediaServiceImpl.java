package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.MediaDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Media;
import com.rawlynrogers.rawlynrogerswebsite.entity.Contact;
import com.rawlynrogers.rawlynrogerswebsite.entity.Project;
import com.rawlynrogers.rawlynrogerswebsite.repository.ContactRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.ProjectRepository;
import com.rawlynrogers.rawlynrogerswebsite.repository.MediaRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaServiceImpl implements MediaService {

    private final ContactRepository contactRepository;
    private final ProjectRepository projectRepository;
    private final MediaRepository mediaRepository;

    public MediaServiceImpl(MediaRepository mediaRepository,
                            ContactRepository contactRepository,
                            ProjectRepository projectRepository) {
        this.mediaRepository = mediaRepository;
        this.contactRepository = contactRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<MediaDTO> getAllMedia() {
        return mediaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MediaDTO uploadMedia(MultipartFile file, String description) {
        try {
            String fileName = file.getOriginalFilename();

            if (fileName == null || fileName.isBlank()) {
                throw new RuntimeException("Invalid file name.");
            }

            String fileType = file.getContentType();

            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path savedFilePath = uploadPath.resolve(fileName);

            file.transferTo(savedFilePath.toFile());

            String fileUrl = "/uploads/" + fileName;

            Media media = new Media(
                    fileName,
                    fileType,
                    fileUrl,
                    description
            );

            Media savedMedia = mediaRepository.save(media);

            return convertToDTO(savedMedia);

        } catch (IOException e) {
            throw new RuntimeException("Could not save uploaded file: " + e.getMessage());
        }
    }

    @Override
    public void deleteMedia(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found."));

        List<Contact> contacts = contactRepository.findAll();

        for (Contact contact : contacts) {
            if (contact.getProfileImage() != null &&
                    contact.getProfileImage().getId().equals(id)) {
                contact.setProfileImage(null);
            }

            if (contact.getResumePdf() != null &&
                    contact.getResumePdf().getId().equals(id)) {
                contact.setResumePdf(null);
            }

            if (contact.getCvPdf() != null &&
                    contact.getCvPdf().getId().equals(id)) {
                contact.setCvPdf(null);
            }

            contact.getSlideshow().remove(media);
        }

        contactRepository.saveAll(contacts);

        List<Project> projects = projectRepository.findAll();

        for (Project project : projects) {
            project.getSlideshow().remove(media);
        }

        projectRepository.saveAll(projects);

        try {
            String fileName = media.getFilePath().replace("/uploads/", "");
            Path filePath = Paths.get("uploads").resolve(fileName);

            Files.deleteIfExists(filePath);

            mediaRepository.delete(media);

        } catch (IOException e) {
            throw new RuntimeException("Could not delete media file.");
        }
    }
    private MediaDTO convertToDTO(Media media) {
        return new MediaDTO(
                media.getId(),
                media.getFileName(),
                media.getFileType(),
                media.getFilePath(),
                media.getDescription(),
                media.getUploadedAt()
        );
    }
}
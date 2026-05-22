package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.MediaDTO;
import com.rawlynrogers.rawlynrogerswebsite.entity.Media;
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

    private final MediaRepository mediaRepository;

    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
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
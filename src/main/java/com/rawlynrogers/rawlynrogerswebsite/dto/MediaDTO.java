package com.rawlynrogers.rawlynrogerswebsite.dto;

import java.time.LocalDateTime;

public class MediaDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private String description;
    private LocalDateTime uploadedAt;

    public MediaDTO() {
    }

    public MediaDTO(Long id,
                    String fileName,
                    String fileType,
                    String filePath,
                    String description,
                    LocalDateTime uploadedAt) {

        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.description = description;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
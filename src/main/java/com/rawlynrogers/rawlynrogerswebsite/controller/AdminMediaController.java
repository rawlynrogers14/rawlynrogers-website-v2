package com.rawlynrogers.rawlynrogerswebsite.controller;

import com.rawlynrogers.rawlynrogerswebsite.dto.MediaDTO;
import com.rawlynrogers.rawlynrogerswebsite.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/media")
public class AdminMediaController {

    private final MediaService mediaService;

    public AdminMediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MediaDTO> uploadMedia(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {

        return ResponseEntity.ok(mediaService.uploadMedia(file, description));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaDTO> updateMedia(
            @PathVariable Long id,
            @RequestBody MediaDTO mediaDTO) {

        return ResponseEntity.ok(mediaService.updateMedia(id, mediaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}
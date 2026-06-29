package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.MediaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    List<MediaDTO> getAllMedia();

    MediaDTO getMediaById(Long id);

    MediaDTO uploadMedia(MultipartFile file, String description);

    MediaDTO updateMedia(Long id, MediaDTO mediaDTO);

    void deleteMedia(Long id);

}
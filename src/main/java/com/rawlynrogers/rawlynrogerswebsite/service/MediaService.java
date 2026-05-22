package com.rawlynrogers.rawlynrogerswebsite.service;

import com.rawlynrogers.rawlynrogerswebsite.dto.MediaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    List<MediaDTO> getAllMedia();

    MediaDTO uploadMedia(MultipartFile file, String description);

    void deleteMedia(Long id);

}
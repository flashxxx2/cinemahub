package tech.itpark.cinemahub.service.api;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.cinemahub.model.dto.MediaUploadDto;

@Service
public interface MediaService {
    MediaUploadDto upload(MultipartFile file);

    void parseAndSave(String path);
}

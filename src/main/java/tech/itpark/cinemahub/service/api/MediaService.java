package tech.itpark.cinemahub.service.api;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.cinemahub.model.dto.MediaUploadDto;
import tech.itpark.cinemahub.model.entity.CinemaEntity;

import java.util.List;
import java.util.Optional;

@Service
public interface MediaService {
    MediaUploadDto upload(MultipartFile file);

    void parseAndSave(String path);

    List<CinemaEntity> getTop();

    Optional<CinemaEntity> getById(int id);
}

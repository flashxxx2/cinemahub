package tech.itpark.cinemahub.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.cinemahub.model.dto.MediaUploadDto;
import tech.itpark.cinemahub.model.entity.CinemaEntity;
import tech.itpark.cinemahub.service.api.MediaService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/media")
@RestController
@RequiredArgsConstructor
public class MediaController {

    private final Log log = LogFactory.getLog(getClass());
    private final MediaService service;

    @PostMapping
    public MediaUploadDto upload(@RequestParam MultipartFile file) {
        log.debug(file.getOriginalFilename());
        return service.upload(file);
    }

    @PostMapping("/parse")
    public void parseAndSave(@RequestBody MediaUploadDto dto) {
        service.parseAndSave(dto.getPath());
    }

    @GetMapping("/getTop")
    public List<CinemaEntity> getTop() {
        return service.getTop();
    }

    @GetMapping("/getById/{id}")
    public Optional<CinemaEntity> getTop(@PathVariable int id) {
        return service.getById(id);
    }

}

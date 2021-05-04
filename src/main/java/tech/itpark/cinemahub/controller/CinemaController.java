package tech.itpark.cinemahub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.cinemahub.model.dto.CinemaDto;
import tech.itpark.cinemahub.service.api.CinemaService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService service;

    @GetMapping("/getTop")
    public List<CinemaDto> getTop() {
        return service.getTop();
    }

    @GetMapping("/getById/{id}")
    public CinemaDto getTop(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/getGenres")
    public Set<String> getGenres() {
        return service.getGenres();
    }

    @GetMapping("/getCompanies")
    public Set<String> getCompanies() {
        return service.getCompanies();
    }


    @GetMapping("/getTopFilmsByGenre")
    public List<String> getTopFilmsByGenre(@RequestParam String genre) {
        return service.getTopFilmsByGenre(genre);
    }

    @GetMapping("/getFilmsByCompany/{id}")
    public List<String> getFilmsByCompany(@PathVariable int id) {
        return service.getFilmsByCompany(id);
    }

    @GetMapping("/getAll")
    public List<CinemaDto> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") long page,
            @RequestParam Optional<Integer> limit
    ) {
        return service.getAll(page, limit.orElse(CinemaService.MAX_PER_PAGE));
    }
}

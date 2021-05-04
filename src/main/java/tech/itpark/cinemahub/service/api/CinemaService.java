package tech.itpark.cinemahub.service.api;

import org.springframework.stereotype.Service;
import tech.itpark.cinemahub.model.dto.CinemaDto;

import java.util.List;
import java.util.Set;

@Service
public interface CinemaService {

    int MAX_PER_PAGE = 10;

    List<CinemaDto> getTop();

    CinemaDto getById(int id);

    List<String> getFilmsByCompany(int id);

    List<CinemaDto> getAll(long perPage, int limit);

    Set<String> getGenres();

    Set<String> getCompanies();

    List<String> getTopFilmsByGenre(String genre);
}

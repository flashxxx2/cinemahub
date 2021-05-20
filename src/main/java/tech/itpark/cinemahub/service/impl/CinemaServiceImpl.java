package tech.itpark.cinemahub.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.cinemahub.mapper.Mapper;
import tech.itpark.cinemahub.model.dto.CinemaDto;
import tech.itpark.cinemahub.repository.CinemaRepository;
import tech.itpark.cinemahub.service.api.CinemaService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository repository;
    private final int MAX_CAPACITY_SIZE = 20;

    @Override
    public Set<String> getGenres() {
        Gson gson = new Gson();
        Set<String> set = new HashSet<>();
        String stringContent = repository.getGenres();
        String content = stringContent.replace("\"\"", "\"");
        String[] split = content.split(",(?=[^\\}]*(?:\\{|$))");
        for (int i = 0; i < split.length; i++) {

            String replace = getString(split[i]);
            if (!replace.isEmpty()) {
                JsonObject jsonObject = gson.fromJson(replace, JsonObject.class);
                JsonElement name = jsonObject.get("name");
                set.add(name.getAsString());
            }
        }
        return set;
    }

    @Override
    public Set<String> getCompanies() {
        Gson gson = new Gson();
        Set<String> set = new HashSet<>();
        String stringContent = repository.getCompanies();
        String content = stringContent.replace("\"\"", "\"");
        String[] split = content.split(",(?=[^\\}]*(?:\\{|$))");
        for (int i = 0; i < split.length; i++) {

            String replace = getString(split[i]);
            if (!replace.isEmpty()) {
                JsonObject jsonObject = gson.fromJson(replace, JsonObject.class);
                JsonElement name = jsonObject.get("name");
                set.add(name.getAsString());
            }
        }
        return set;
    }

    @Override
    public List<String> getTopFilmsByGenre(String genre) {
        Gson gson = new Gson();
        List<String> titles = new ArrayList<>();
        final var list = repository.getTopFilmsByGenre();
        for (String s : list) {
            String genres = s.replace("\"\"", "\"");
            String[] strings = genres.split("(?=[^\")]),(?=\"|\\])");

            //Если массив меньше 1, значит у фильма не указан жанр
            if (strings.length > 1) {
                String[] split = strings[1].split(",(?=[^\\}]*(?:\\{|$))");

                for (String value : split) {
                    String replace = getString(value);
                    if (!replace.isEmpty()) {
                        JsonObject jsonObject = gson.fromJson(replace, JsonObject.class);
                        JsonElement name = jsonObject.get("name");
                        if (name.getAsString().equals(genre)) {
                            titles.add(strings[0]);
                        }
                    }
                }
            }
            if (titles.size() > MAX_CAPACITY_SIZE - 1) {
                break;
            }
        }
        return titles;
    }

    @Override
    public List<String> getFilmsByCompany(int filmId) {
        Gson gson = new Gson();
        List<String> titles = new ArrayList<>();
        final var list = repository.getFilmsByCompany();
        for (String s : list) {
            String genres = s.replace("\"\"", "\"");
            String[] strings = genres.split("(?=[^\")]),(?=\"|\\])");

            //Если массив меньше 1, значит у фильма не указана компания
            if (strings.length > 1) {
                String[] split = strings[1].split(",(?=[^\\}]*(?:\\{|$))");

                for (String value : split) {
                    String replace = getString(value);
                    if (!replace.isEmpty()) {
                        JsonObject jsonObject = gson.fromJson(replace, JsonObject.class);
                        JsonElement id = jsonObject.get("id");
                        if (id.getAsInt() == filmId) {
                            titles.add(strings[0]);
                        }
                    }
                }
            }
        }
        return titles;
    }

    private String getString(String value) {
        return value.replace("\"[", "")
                .replace("]\"", "")
                .replace("]\"", "")
                .replace("]", "")
                .replace("[", "").trim();
    }

    private final Mapper mapper;

    @Override
    public List<CinemaDto> getAll(long perPage, int limit) {
        return mapper.toDtoList(repository.getAll(perPage, limit));
    }

    @Override
    public List<CinemaDto> getTop() {
        return mapper.toDtoList(repository.getTop());
    }

    @Override
    public CinemaDto getById(int id) {
        return mapper.toDto(repository.getById(id).orElseThrow(EntityNotFoundException::new));
    }
}

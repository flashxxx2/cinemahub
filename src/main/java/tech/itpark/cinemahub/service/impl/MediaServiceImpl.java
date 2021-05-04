package tech.itpark.cinemahub.service.impl;

import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.itpark.cinemahub.exception.CanNotParseException;
import tech.itpark.cinemahub.exception.MediaUploadException;
import tech.itpark.cinemahub.exception.UnsupportedMediaTypeException;
import tech.itpark.cinemahub.model.dto.MediaUploadDto;
import tech.itpark.cinemahub.model.entity.CinemaEntity;
import tech.itpark.cinemahub.repository.MediaRepository;
import tech.itpark.cinemahub.service.api.MediaService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final Path path = Path.of("C:/tmp");
    private final Map<String, String> supportedMediaTypeExtensions = Map.of(
            "text/plain", ".csv",
            MediaType.IMAGE_PNG_VALUE, ".png"
    );
    private final Tika tika = new Tika();
    private final MediaRepository repository;

    @Override
    public MediaUploadDto upload(MultipartFile file) {
        try {
            final var type = tika.detect(file.getInputStream());
            final var extension = Optional.ofNullable(
                    supportedMediaTypeExtensions.get(type)
            ).orElseThrow(UnsupportedMediaTypeException::new);

            final var name = UUID.randomUUID() + extension;

            final var formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            final var today = formatter.format(OffsetDateTime.now());
            final var webPath = today + "/" + name;
            final var resolved = path.resolve(today);
            Files.createDirectories(resolved);
            file.transferTo(resolved.resolve(name));
            return new MediaUploadDto(webPath);
        } catch (IOException e) {
            throw new MediaUploadException(e);
        }
    }

    @Override
    public void parseAndSave(String path) {

        //Расинг CSV файла по указанному пути и сохранение в БД из него
        ArrayList<CinemaEntity> cinemas = new ArrayList<>();
        List<String> fileLines;
        try {
            fileLines = Files.readAllLines(Paths.get(path));

            for (int j = 1; j < fileLines.size(); j++) {
                String[] splitedText = fileLines.get(j).split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                CinemaEntity cinema = new CinemaEntity();
                cinema.setBudget(splitedText[0]);
                cinema.setGenres(splitedText[1]);
                cinema.setHomepage(splitedText[2]);
                cinema.setId(Long.parseLong(splitedText[3]));
                cinema.setKeywords(splitedText[4]);
                cinema.setOriginalLanguage(splitedText[5]);
                cinema.setOriginalTitle(splitedText[6]);
                cinema.setOverview(splitedText[7]);
                cinema.setPopularity(splitedText[8]);
                cinema.setProductionCompanies(splitedText[9]);
                cinema.setProductionCountries(splitedText[10]);
                cinema.setReleaseDate(splitedText[11]);
                cinema.setRevenue(splitedText[12]);
                cinema.setRuntime(splitedText[13]);
                cinema.setSpokenLanguages(splitedText[14]);
                cinema.setStatus(splitedText[15]);
                cinema.setTagline(splitedText[16]);
                cinema.setTitle(splitedText[17]);
                cinema.setVoteAverage(splitedText[18]);
                cinema.setVoteCount(splitedText[19]);
                cinemas.add(cinema);
            }
        } catch (IOException e) {
            throw new CanNotParseException();
        }
        repository.save(cinemas);
    }
}

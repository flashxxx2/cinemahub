package tech.itpark.cinemahub;

import tech.itpark.cinemahub.model.entity.CinemaEntity;
import tech.itpark.cinemahub.repository.CinemaRepository;
import tech.itpark.cinemahub.service.impl.MediaServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\amaximov\\IdeaProjects\\ultimate\\Cinemahub\\src\\test\\resource\\tmdb_5000_movies.csv";
        List<CinemaEntity> products = ParseProductCsv(filePath);
        //Тут вызов метода или прямая обработка полученных продуктов
    }

    //Расинг CSV файла по указанному пути и получение продуктов из него
    private static List<CinemaEntity> ParseProductCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<CinemaEntity> cinemas = new ArrayList<CinemaEntity>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
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
        return cinemas;
    }

}

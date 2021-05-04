package tech.itpark.cinemahub;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tech.itpark.cinemahub.model.entity.CinemaEntity;
import tech.itpark.cinemahub.repository.CinemaRepository;
import tech.itpark.cinemahub.service.impl.MediaServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\amaximov\\IdeaProjects\\ultimate\\Cinemahub\\src\\test\\resource\\tmdb_5000_movies.csv";
//        List<CinemaEntity> products = ParseProductCsv(filePath);

        getGenres("\"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 80, \"\"name\"\": \"\"Crime\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 80, \"\"name\"\": \"\"Crime\"\"}, {\"\"id\"\": 18, \"\"name\"\": \"\"Drama\"\"}, {\"\"id\"\": 53, \"\"name\"\": \"\"Thriller\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 18, \"\"name\"\": \"\"Drama\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 16, \"\"name\"\": \"\"Animation\"\"}, {\"\"id\"\": 10751, \"\"name\"\": \"\"Family\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 10751, \"\"name\"\": \"\"Family\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 35, \"\"name\"\": \"\"Comedy\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 53, \"\"name\"\": \"\"Thriller\"\"}, {\"\"id\"\": 80, \"\"name\"\": \"\"Crime\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 37, \"\"name\"\": \"\"Western\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}, {\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 10751, \"\"name\"\": \"\"Family\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 35, \"\"name\"\": \"\"Comedy\"\"}, {\"\"id\"\": 878, \"\"name\"\": \"\"Science Fiction\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 28, \"\"name\"\": \"\"Action\"\"}, {\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}]\"\",\n" +
                "  \"\"[{\"\"id\"\": 12, \"\"name\"\": \"\"Adventure\"\"}, {\"\"id\"\": 14, \"\"name\"\": \"\"Fantasy\"\"}]\"\"");


    }

    //Расинг CSV файла по указанному пути и получение продуктов из него
    private static List<CinemaEntity> ParseProductCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<CinemaEntity> cinemas = new ArrayList<CinemaEntity>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (int j = 1; j < fileLines.size(); j++) {
            String[] splitedText = fileLines.get(j).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

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

    public static Set<String> getGenres(String stringContent) {
        Gson gson = new Gson();
        Set<String> set = new HashSet<>();
        String content = stringContent.replace("\"\"", "\"");
        String[] split = content.split(",(?=[^\\}]*(?:\\{|$))");
        for (String s : split) {
            String replace = s.replace("\"[", "").trim();
            String replace2 = replace.replace("]\"", "").trim();
            JsonObject jsonObject = gson.fromJson(replace2, JsonObject.class);
            JsonElement name = jsonObject.get("name");
            set.add(name.getAsString());
        }
        return set;

    }

}

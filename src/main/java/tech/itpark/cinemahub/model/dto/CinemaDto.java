package tech.itpark.cinemahub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDto {

    private Long id;
    private String budget;
    private String genres;
    private String homepage;
    private String keywords;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String popularity;
    private String productionCompanies;
    private String productionCountries;
    private String releaseDate;
    private String revenue;
    private String runtime;
    private String spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private String voteAverage;
    private String voteCount;
}

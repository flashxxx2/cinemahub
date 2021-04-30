package tech.itpark.cinemahub.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="films")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CinemaEntity {

    @Id
    Long id;
    @Column
    private String budget;
    @Column
    private String genres;
    @Column
    private String homepage;
    @Column
    private String keywords;
    @Column(name = "original_language")
    private String originalLanguage;
    @Column(name = "original_title")
    private String originalTitle;
    @Column
    private String overview;
    @Column
    private String popularity;
    @Column(name = "production_companies")
    private String productionCompanies;
    @Column(name = "production_countries")
    private String productionCountries;
    @Column(name = "release_date")
    private String releaseDate;
    @Column
    private String revenue;
    @Column
    private String runtime;
    @Column(name = "spoken_languages")
    private String spokenLanguages;
    @Column
    private String status;
    @Column
    private String tagline;
    @Column
    private String title;
    @Column(name = "vote_average")
    private String voteAverage;
    @Column(name = "vote_count")
    private String voteCount;

    @Override
    public String toString() {
        return "CinemaEntity{" +
                "id=" + id +
                ", budget='" + budget + '\'' +
                ", genres='" + genres + '\'' +
                ", homepage='" + homepage + '\'' +
                ", keywords='" + keywords + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity='" + popularity + '\'' +
                ", productionCompanies='" + productionCompanies + '\'' +
                ", productionCountries='" + productionCountries + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", revenue='" + revenue + '\'' +
                ", runtime='" + runtime + '\'' +
                ", spokenLanguages='" + spokenLanguages + '\'' +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", voteCount='" + voteCount + '\'' +
                '}';
    }
}

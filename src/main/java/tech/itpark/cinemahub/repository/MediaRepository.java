package tech.itpark.cinemahub.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import tech.itpark.cinemahub.model.entity.CinemaEntity;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MediaRepository {
    private final NamedParameterJdbcOperations template;

    public MediaRepository(DataSource ds) {
        template = new NamedParameterJdbcTemplate(ds);
    }

    public void save(List<CinemaEntity> cinemas) {
        final var keyHolder = new GeneratedKeyHolder();
        final var params = new MapSqlParameterSource();

        for (CinemaEntity cinema : cinemas) {

            params.addValue("id", cinema.getId());
            params.addValue("budget", cinema.getBudget());
            params.addValue("genres", cinema.getGenres());
            params.addValue("homepage", cinema.getHomepage());
            params.addValue("keywords", cinema.getKeywords());
            params.addValue("original_language", cinema.getOriginalLanguage());
            params.addValue("original_title", cinema.getOriginalTitle());
            params.addValue("overview", cinema.getOverview());
            params.addValue("popularity", cinema.getPopularity());
            params.addValue("production_companies", cinema.getProductionCompanies());
            params.addValue("production_countries", cinema.getProductionCountries());
            params.addValue("release_date", cinema.getReleaseDate());
            params.addValue("revenue", cinema.getRevenue());
            params.addValue("runtime", cinema.getRuntime());
            params.addValue("spoken_languages", cinema.getSpokenLanguages());
            params.addValue("status", cinema.getStatus());
            params.addValue("tagline", cinema.getTagline());
            params.addValue("title", cinema.getTitle());
            params.addValue("vote_average", cinema.getVoteAverage());
            params.addValue("vote_count", cinema.getVoteCount());

            template.update(
                    // language=PostgreSQL
                    "INSERT INTO films(id, budget, genres, " +
                            "homepage, keywords, original_language, " +
                            "original_title, overview, popularity, " +
                            "production_companies, production_countries, release_date, " +
                            "revenue, runtime, spoken_languages, " +
                            "status, tagline, title, " +
                            "vote_average, vote_count) " +
                            "VALUES (:id, :budget, :genres, :homepage, " +
                            ":keywords, :original_language, :original_title, " +
                            ":overview, :popularity, :production_companies, " +
                            ":production_countries, :release_date, :revenue," +
                            ":runtime, :spoken_languages, :status, " +
                            ":tagline, :title, :vote_average, " +
                            ":vote_count)",
                    params,
                    keyHolder
            );
        }
    }
}

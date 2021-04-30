package tech.itpark.cinemahub.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import tech.itpark.cinemahub.model.entity.CinemaEntity;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CinemaRepository {
    private final NamedParameterJdbcOperations template;

    private final RowMapper<CinemaEntity> rowMapper = (rs, i) -> new CinemaEntity(
            rs.getLong("id"),
            rs.getString("budget"),
            rs.getString("genres"),
            rs.getString("homepage"),
            rs.getString("keywords"),
            rs.getString("original_language"),
            rs.getString("original_title"),
            rs.getString("overview"),
            rs.getString("popularity"),
            rs.getString("production_companies"),
            rs.getString("production_countries"),
            rs.getString("release_date"),
            rs.getString("revenue"),
            rs.getString("runtime"),
            rs.getString("spoken_languages"),
            rs.getString("status"),
            rs.getString("tagline"),
            rs.getString("title"),
            rs.getString("vote_average"),
            rs.getString("vote_count")
    );

    public CinemaRepository(DataSource ds) {
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

    public List<CinemaEntity> getTop() {
        return template.query(
                // language=PostgreSQL
                "SELECT id, budget, genres, homepage, " +
                        "keywords, original_language, original_title, " +
                        "overview, popularity, production_companies," +
                        " production_countries, release_date, revenue, " +
                        "runtime, spoken_languages, status, " +
                        "tagline, title, vote_average, vote_count FROM films ORDER BY popularity DESC LIMIT 20",
                rowMapper
        );
    }

    public Optional<CinemaEntity> getById(int id) {
        // language=PostgreSQL
        String sql = "SELECT id, budget, genres, homepage," +
                " keywords, original_language, original_title," +
                " overview, popularity, production_companies," +
                "production_countries, release_date, revenue, " +
                "runtime, spoken_languages, status," +
                "tagline, title, vote_average," +
                " vote_count FROM films where id = :id";
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(
                // language=PostgreSQL
                sql,
                Map.of("id", id),
                rowMapper
        )));
    }
}

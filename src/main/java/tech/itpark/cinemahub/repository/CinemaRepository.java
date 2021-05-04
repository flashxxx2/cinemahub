package tech.itpark.cinemahub.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.cinemahub.model.entity.CinemaEntity;

import javax.sql.DataSource;
import java.util.*;

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

    public List<CinemaEntity> getAll(long perPage, int limit) {
        String sql = String.format("SELECT id, budget, genres, homepage," +
                "keywords, original_language, original_title," +
                "overview, popularity, production_companies," +
                "production_countries, release_date, revenue," +
                "runtime, spoken_languages, status," +
                "tagline, title, vote_average, vote_count " +
                "FROM films ORDER BY popularity DESC LIMIT %s OFFSET %s", limit, limit*perPage);
        return template.query(
                sql,
                rowMapper
        );
    }

    public String getGenres() {
        String sql = "SELECT genres FROM films";
        List<String> list = new ArrayList<>();
        template.query(
                sql,
                (rs, i) -> {
                    final var value = rs.getObject("genres");
                    return list.add(value.toString());
                }
        );
        return list.toString();
    }

    public String getCompanies() {
        String sql = "SELECT production_companies FROM films";
        List<String> list = new ArrayList<>();
        template.query(
                sql,
                (rs, i) -> {
                    final var value = rs.getObject("production_companies");
                    return list.add(value.toString());
                }
        );
        return list.toString();
    }

    public List<String> getTopFilmsByGenre() {
        String sql = "SELECT title, genres FROM films ORDER BY popularity DESC";
        List<String> list = new ArrayList<>();
        template.query(
                sql,
                (rs, i) -> {
                    final var key = rs.getObject("title");
                    final var value = rs.getObject("genres");
                    return list.add(key.toString() + "," + value.toString());
                }
        );
        return list;
    }

    public List<String> getFilmsByCompany() {
        String sql = "SELECT title, production_companies FROM films ORDER BY release_date DESC";
        List<String> list = new ArrayList<>();
        template.query(
                sql,
                (rs, i) -> {
                    final var key = rs.getObject("title");
                    final var value = rs.getObject("production_companies");
                    return list.add(key.toString() + "," + value.toString());
                }
        );
        return list;
    }
}

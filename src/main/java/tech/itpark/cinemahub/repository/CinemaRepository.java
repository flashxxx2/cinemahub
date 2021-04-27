package tech.itpark.cinemahub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.itpark.cinemahub.model.entity.CinemaEntity;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
}

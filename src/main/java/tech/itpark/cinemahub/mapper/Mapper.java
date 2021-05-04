package tech.itpark.cinemahub.mapper;

import tech.itpark.cinemahub.model.dto.CinemaDto;
import tech.itpark.cinemahub.model.entity.CinemaEntity;

import java.util.List;

@org.mapstruct.Mapper
public interface Mapper {

    CinemaDto toDto(CinemaEntity entity);

    CinemaEntity toEntity(CinemaDto dto);

    List<CinemaDto> toDtoList(List<CinemaEntity> cinemaEntities);
}

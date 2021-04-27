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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

}

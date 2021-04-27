package tech.itpark.cinemahub.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name="cinema")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CinemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

}

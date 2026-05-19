package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "genre_id")
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "genre")
    private Set<Course> courses = new HashSet<>();
}

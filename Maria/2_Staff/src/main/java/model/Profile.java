package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profile {

    @Id
    @EqualsAndHashCode.Include
    private long id;

    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "staff_id")
    @MapsId
    @ToString.Exclude
    private Staff staff;
}

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
@Table(name = "instructors")
public class Instructor {

    @Id
    @Column(name = "instructor_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @ElementCollection
    @CollectionTable(
            name = "instructor_phones",
            joinColumns = @JoinColumn(name = "instructor_id")
    )
    @Column(name = "phone_number")
    private Set<String> phones;

    private String major;

    private String degree;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses = new HashSet<>();
}

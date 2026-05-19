package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String address;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();
}

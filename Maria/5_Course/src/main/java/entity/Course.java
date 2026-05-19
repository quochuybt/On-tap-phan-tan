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
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id")
    private String id;

    private String name;

    private String description;

    @Column(name = "tuition_fee")
    private double tuitionFee;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();
}

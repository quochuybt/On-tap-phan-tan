package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @Column(nullable = true)
    private double score;

    @Enumerated(EnumType.STRING)
    private EnrollStatus status;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @Embeddable
    public static class EnrollmentId implements Serializable {
        private String student_id;

        private String course_id;

        @Column(name = "enroll_date")
        private LocalDate enrollDate;
    }
}

package dto;

import entity.EnrollStatus;
import entity.Enrollment;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class EnrollmentDTO implements Serializable {

    private String courseId;

    private String courseName;

    private String studentId;
    private String studentName;
    private LocalDate enrollDate;

    private double score;

    private EnrollStatus status;

}

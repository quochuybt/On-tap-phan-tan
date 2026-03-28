package iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollment {
    private String studentId;
    private String courseId;
    private String semester;
    private double grade;
}

package dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class CourseStatDTO implements Serializable {
    private String courseName;
    private String instructorName;
    private String genreName;
    private double tuitionFee;
    private long countStudent;
}

package iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassInfo {
    private String name;
    private String teacher;
    private int room;
    private String start_time;
    private String end_time;

    private List<Student> student;
}

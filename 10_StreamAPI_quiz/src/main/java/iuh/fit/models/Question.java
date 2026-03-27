package iuh.fit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    private String question_id;
    private String text;
    private List<String> options;
    private String correct_answer;
}

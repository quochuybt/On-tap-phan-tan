package fit.iuh.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quiz {
    private String quiz_id;
    private String name;
    private int score;

    private List<Question> questions;
    private Category category;
}

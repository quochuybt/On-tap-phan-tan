package fit.iuh;

import fit.iuh.models.Quiz;
import fit.iuh.utils.JsonUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Quiz> quizzes = JsonUtils.listQuizzes("C001","json/quizzes.json");
        quizzes.forEach(System.out::println);
        JsonUtils.writeQuizzesToJson(quizzes,"json/quizzes2.json");
    }
}

package iuh.fit;

import iuh.fit.models.Quiz;
import iuh.fit.utils.JsonUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Quiz> quizzes = JsonUtils.listQuizzes("C001","json/quizzes.json");
        quizzes.forEach(System.out::println);
        JsonUtils.writeQuizToJson(quizzes,"json/quizzes2.json");
    }
}

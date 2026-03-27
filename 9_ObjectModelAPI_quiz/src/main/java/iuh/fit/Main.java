package iuh.fit;

import iuh.fit.models.Quiz;
import iuh.fit.utils.JsonUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Quiz> res = JsonUtils.listQuizzes("C001","json/quizzes.json");
        res.forEach(System.out::println);
        JsonUtils.writeQuizzesToJson(res,"json/quizzes2.json");
    }
}

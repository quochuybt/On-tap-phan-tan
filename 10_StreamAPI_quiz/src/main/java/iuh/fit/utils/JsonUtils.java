package iuh.fit.utils;

import iuh.fit.models.Category;
import iuh.fit.models.Question;
import iuh.fit.models.Quiz;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<Quiz> listQuizzes(String fileName) {
        List<Quiz> res = new ArrayList<>();

        try(JsonParser parser = Json.createParser(new FileReader(fileName))) {

            Quiz quiz = null;
            List<Question> questions = null;
            Question question= null;
            List<String> options = null;
            Category category = null;

            String key = "";
            String currentObject ="";

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY -> {
                        if (key.equalsIgnoreCase("questions")) {
                            questions = new ArrayList<>();
                            currentObject = "questions";
                        }else if (key.equalsIgnoreCase("options")) {
                            options = new ArrayList<>();
                            currentObject = "options";
                        }
                    }
                    case START_OBJECT -> {
                        if (currentObject.equals("")) {
                            quiz = new Quiz();
                            currentObject = "quiz";

                        } else if (currentObject.equals("questions")) {
                            question = new Question();
                            currentObject = "question";

                        } else if (key.equals("category")) {
                            category = new Category();
                            currentObject = "category";
                        }
                    }
                    case END_OBJECT -> {
                        if (question != null) {
                            questions.add(question);
                            question.setOptions(options);
                            options = null;
                            question = null;
                            currentObject = "questions";
                        }else if (category != null) {
                            quiz.setCategory(category);
                            category = null;
                            currentObject = "quiz";
                        }else if (quiz != null) {
                            quiz.setQuestions(questions);
                            res.add(quiz);
                            quiz = null;
                            questions = null;
                            currentObject ="";
                        }
                    }
                    case KEY_NAME -> key = parser.getString();
                    case VALUE_STRING -> {
                        String value = parser.getString();
                        switch (key) {
                            case "quiz_id" -> quiz.setQuiz_id(value);
                            case "name" -> {
                                if (currentObject.equalsIgnoreCase("quiz")) {
                                    quiz.setName(value);
                                }else if (currentObject.equalsIgnoreCase("category")) category.setName(value);
                            }
                            case "question_id" -> question.setQuestion_id(value);
                            case "text" -> question.setText(value);
                            case "correct_answer" -> question.setCorrect_answer(value);
                            case "category_id" -> {
                                if (category != null)
                                    category.setCategory_id(value);
                            }
                            default -> {
                                if (currentObject.equalsIgnoreCase("options")) options.add(value);
                            }
                        }

                    }
                    case VALUE_NUMBER -> {
                        quiz.setScore(parser.getInt());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}

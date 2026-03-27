package iuh.fit.utils;

import iuh.fit.models.Category;
import iuh.fit.models.Question;
import iuh.fit.models.Quiz;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public static List<Quiz> listQuizzes(String categoryId,String fileName) {
        List<Quiz> res = new ArrayList<>();

        try(JsonParser parser = Json.createParser(new FileReader(fileName))) {

            Quiz quiz = null;
            List<Question> questions = null;
            Question question= null;
            List<String> options = null;
            Category category = null;

            String key = "";
            String currentObject ="";
            boolean isMatch = false;

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
//
                        if ("category".equalsIgnoreCase(key)) {
                            category = new Category();
                            currentObject = "category";
                        }
                        else if (questions!=null && question ==null) {
                            question = new Question();
                            currentObject = "question";
                        }
                        else if(quiz==null){
                            quiz = new Quiz();
                            currentObject = "quiz";
                        }
                    }
                    case END_OBJECT -> {
                        if (question != null) {
                            question.setOptions(options);
                            questions.add(question);
                            options = null;
                            question = null;
                            currentObject = "questions";
                        }else if (category != null) {
                            quiz.setCategory(category);
                            category = null;
                            currentObject = "quiz";
                        }else if (quiz != null) {
                            quiz.setQuestions(questions);
                            if (isMatch) res.add(quiz);
                            quiz = null;
                            questions = null;
                            currentObject = "";
                            isMatch = false;
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
                                if (category != null) {
                                    if (categoryId.equalsIgnoreCase(value)) isMatch = true;
                                    category.setCategory_id(value);
                                }
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
    public static void writeQuizToJson (List<Quiz> quizzes,String fileName) {
        Map<String,Object> config = Map.of(JsonGenerator.PRETTY_PRINTING,true);
        JsonGeneratorFactory jsonGeneratorFactory = Json.createGeneratorFactory(config);

        try (JsonGenerator jsonGenerator = jsonGeneratorFactory.createGenerator(new FileWriter(fileName))) {

            jsonGenerator.writeStartArray();
            quizzes.forEach(quiz -> {

                jsonGenerator.writeStartObject();

                jsonGenerator.write("quiz_id",quiz.getQuiz_id());
                jsonGenerator.write("name",quiz.getName());
                jsonGenerator.write("score",quiz.getScore());

                List<Question> questions = quiz.getQuestions();
                jsonGenerator.writeStartArray("questions");

                questions.forEach(question -> {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.write("question_id",question.getQuestion_id());
                    jsonGenerator.write("text",question.getText());
                    List<String> options = question.getOptions();
                    jsonGenerator.writeStartArray("options");
                    options.forEach(option -> {
                        jsonGenerator.write(option);
                    });
                    jsonGenerator.writeEnd();
                    jsonGenerator.write("correct_answer",question.getCorrect_answer());
                    jsonGenerator.writeEnd();
                });
                jsonGenerator.writeEnd();
                Category category = quiz.getCategory();
                jsonGenerator.writeStartObject("category");

                jsonGenerator.write("category_id",category.getCategory_id());
                jsonGenerator.write("name",category.getName());

                jsonGenerator.writeEnd();

                jsonGenerator.writeEnd();

            });

            jsonGenerator.writeEnd();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

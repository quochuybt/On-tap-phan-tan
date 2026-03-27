package fit.iuh.utils;

import fit.iuh.models.Category;
import fit.iuh.models.Question;
import fit.iuh.models.Quiz;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static javax.json.Json.createArrayBuilder;

public class JsonUtils {
    public static List<Quiz> listQuizzes(String categoryId, String fileName) {
        List<Quiz> res = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray quizzesJsonArray = reader.readArray();
            quizzesJsonArray.forEach(quizValue -> {
                JsonObject quizJsonObject = quizValue.asJsonObject();

                JsonObject categoryJsonObject = quizJsonObject.getJsonObject("category");
                String category_id = categoryJsonObject.getString("category_id");
                if (category_id.equalsIgnoreCase(categoryId)) {
                    String quiz_id = quizJsonObject.getString("quiz_id");
                    String name = quizJsonObject.getString("name");
                    int score = quizJsonObject.getInt("score");

                    List<Question> questions = new ArrayList<>();
                    JsonArray questionsJsonArray = quizJsonObject.getJsonArray("questions");
                    questionsJsonArray.forEach(questionValue -> {
                        JsonObject questionJsonObject = questionValue.asJsonObject();
                        String question_id = questionJsonObject.getString("question_id");
                        String text = questionJsonObject.getString("text");
                        List<String> options = new ArrayList<>();
                        JsonArray optionsJsonArray = questionJsonObject.getJsonArray("options");
                        IntStream.range(0,optionsJsonArray.size())
                                .forEach(x->options.add(optionsJsonArray.getString(x)));
                        String correct_answer = questionJsonObject.getString("correct_answer");
                        Question question = new Question(question_id,text,options,correct_answer);
                        questions.add(question);
                    });
                    String category_name = categoryJsonObject.getString("name");
                    Category category = new Category(category_id,category_name);
                    Quiz quiz = new Quiz(quiz_id,name,score,questions,category);
                    res.add(quiz);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void writeQuizzesToJson (List<Quiz> quizzes, String fileName) {
        Map<String,Object> config =Map.of(JsonGenerator.PRETTY_PRINTING,true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);
        try(JsonWriter jsonWriter = jsonWriterFactory.createWriter(new FileWriter(fileName))) {

            JsonArrayBuilder quizzesJsonArray = createArrayBuilder();
            quizzes.forEach(quiz -> {
                JsonObjectBuilder quizJsonObject = Json
                        .createObjectBuilder()
                        .add("quiz_id",quiz.getQuiz_id())
                        .add("name",quiz.getName())
                        .add("score",quiz.getScore());
                List<Question> questions = quiz.getQuestions();
                JsonArrayBuilder questionsJsonArray = Json.createArrayBuilder();
                questions.forEach(question -> {
                    JsonObjectBuilder questionJsonObject = Json
                            .createObjectBuilder()
                            .add("question_id",question.getQuestion_id())
                            .add("text",question.getText());
                    List<String> options = question.getOptions();
                    JsonArrayBuilder optionsJsonArray = createArrayBuilder();
                    options.forEach(optionsJsonArray::add);
                    questionJsonObject.add("options",optionsJsonArray);
                    questionJsonObject.add("correct_answer",question.getCorrect_answer());
                    questionsJsonArray.add(questionJsonObject);
                });
                quizJsonObject.add("questions",questionsJsonArray);
                Category category = quiz.getCategory();
                JsonObjectBuilder categoryJsonObject = Json
                        .createObjectBuilder()
                        .add("category_id",category.getCategory_id())
                        .add("name",category.getName());
                quizJsonObject.add("category",categoryJsonObject);
                quizzesJsonArray.add(quizJsonObject);
            });
            jsonWriter.write(quizzesJsonArray.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

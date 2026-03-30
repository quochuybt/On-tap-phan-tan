package iuh.fit.util;

import iuh.fit.model.Course;
import iuh.fit.model.Enrollment;
import iuh.fit.model.Student;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class JsonUltils {
    public static Map<String, Object> getStudentCourseData(String fileName){
        Map<String, Object> res = new HashMap<>();

        try(JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonObject classInfo = reader.readObject();
            List<Student> students = new ArrayList<>();
            JsonArray studentsJsonArray = classInfo.getJsonArray("students");
            studentsJsonArray.forEach(studentValue -> {
                JsonObject studentJsonObject = studentValue.asJsonObject();
                String studentId = studentJsonObject.getString("studentId");
                String name = studentJsonObject.getString("name");
                Student student = new Student(studentId,name);
                students.add(student);
            });
            res.put("students",students);
            List<Course> courses = new ArrayList<>();
            JsonArray coursesJsonArray = classInfo.getJsonArray("courses");
            coursesJsonArray.forEach(courseValue -> {
                JsonObject studentJsonObject = courseValue.asJsonObject();
                String courseId = studentJsonObject.getString("courseId");
                String courseName = studentJsonObject.getString("courseName");
                Course course = new Course(courseId,courseName);
                courses.add(course);
            });
            res.put("courses",courses);
            List<Enrollment> enrollments = new ArrayList<>();
            JsonArray enrollmentsJsonArray = classInfo.getJsonArray("enrollments");
            enrollmentsJsonArray.forEach(enrollmentValue -> {
                JsonObject enrollmentJsonObject = enrollmentValue.asJsonObject();
                String studentId = enrollmentJsonObject.getString("studentId");
                String courseId = enrollmentJsonObject.getString("courseId");
                String semester = enrollmentJsonObject.getString("semester");
                double grade = enrollmentJsonObject.getJsonNumber("grade").doubleValue();
                Enrollment enrollment = new Enrollment(studentId,courseId,semester,grade);
                enrollments.add(enrollment);
            });
            res.put("enrollments",enrollments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void writeToFile(Map<String,Object> data,String key,String fileName) {
        Map<String,Object> config = Map.of(JsonGenerator.PRETTY_PRINTING,true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try(JsonWriter jsonWriter = jsonWriterFactory.createWriter(new FileWriter(fileName))) {
            JsonObjectBuilder elementJsonObject = Json.createObjectBuilder();
            switch (key) {
                case "students" -> {
                    List<Student> students = (List<Student>) data.get("students");
                    JsonArrayBuilder studentJsonArray = Json.createArrayBuilder();
                    students.forEach(student -> {
                        studentJsonArray.add(Json.createObjectBuilder()
                                .add("studentId",student.getStudentId())
                                .add("name",student.getName()));
                    });
                    elementJsonObject.add("students",studentJsonArray);
                }
                case "courses" -> {
                    List<Course> courses = (List<Course>) data.get("courses");
                    JsonArrayBuilder coursesJsonArray = Json.createArrayBuilder();
                    courses.forEach(course -> {
                        coursesJsonArray.add(Json.createObjectBuilder()
                                .add("courseId",course.getCourseId())
                                .add("courseName",course.getCourseName()));
                    });
                    elementJsonObject.add("courses",coursesJsonArray);
                }
                case "enrollments" -> {
                    List<Enrollment> enrollments = (List<Enrollment>) data.get("enrollments");
                    JsonArrayBuilder enrollmentJsonArray = Json.createArrayBuilder();
                    enrollments.forEach(enrollment -> {
                        enrollmentJsonArray.add(Json.createObjectBuilder()
                                .add("studentId",enrollment.getStudentId())
                                .add("courseId",enrollment.getCourseId())
                                .add("semester",enrollment.getSemester())
                                .add("grade",enrollment.getGrade()));
                    });
                    elementJsonObject.add("enrollments",enrollmentJsonArray);
                }
            }
            jsonWriter.write(elementJsonObject.build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

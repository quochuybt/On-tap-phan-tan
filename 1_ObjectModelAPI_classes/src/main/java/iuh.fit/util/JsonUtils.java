package iuh.fit.util;

import iuh.fit.model.Address;
import iuh.fit.model.ClassInfo;
import iuh.fit.model.Student;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class JsonUtils {
    public static List<ClassInfo> fromJson(String fileName) {
        List<ClassInfo> res = new ArrayList<>();
        try(JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray classInfoJsonArray = reader.readArray();
            classInfoJsonArray.forEach((classInfoValue) -> {
                JsonObject classInfoJsonObject = classInfoValue.asJsonObject();
                String name = classInfoJsonObject.getString("name");
                String teacher = classInfoJsonObject.getString("teacher");
                int room = classInfoJsonObject.getInt("room");
                String start_time = classInfoJsonObject.getString("start_time");
                String end_time = classInfoJsonObject.getString("end_time");
                JsonArray studentJsonArray = classInfoJsonObject.getJsonArray("students");
                List<Student> students = new ArrayList<>();
                studentJsonArray.forEach((studentValue) -> {
                    JsonObject studentJsonObject = studentValue.asJsonObject();
                    String studentName = studentJsonObject.getString("name");
                    int age = studentJsonObject.getInt("age");
                    double gpa = studentJsonObject.getJsonNumber("gpa").doubleValue();
                    JsonObject addressJsonObject = studentJsonObject.getJsonObject("address");
                    Address address = new Address(
                            addressJsonObject.getString("street"),
                            addressJsonObject.getString("city"),
                            addressJsonObject.getString("state"),
                            addressJsonObject.getString("zip")
                    );
                    Student student = new Student(
                            studentName,age,gpa,address
                    );
                    students.add(student);
                });
                ClassInfo classInfo = new ClassInfo(
                        name,teacher,room,start_time,end_time,students
                );
                res.add(classInfo);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void writeToFile(List<ClassInfo> classInfos, String fileName) {
        Map<String,Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING,true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try (JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))) {
            JsonArrayBuilder classInfosJsonArray = Json.createArrayBuilder();
            classInfos.forEach(classInfo -> {
                JsonObjectBuilder classInfoJsonObject = Json
                        .createObjectBuilder()
                        .add("name",classInfo.getName())
                        .add("teacher",classInfo.getTeacher())
                        .add("room",classInfo.getRoom())
                        .add("start_time",classInfo.getStart_time())
                        .add("end_time",classInfo.getEnd_time());
                JsonArrayBuilder studentsJsonArray = Json.createArrayBuilder();
                List<Student> students = classInfo.getStudent();
                students.forEach(student -> {
                   JsonObjectBuilder studentJsonObject = Json
                            .createObjectBuilder()
                            .add("name",student.getName())
                           .add("age",student.getAge())
                           .add("gpa",student.getGpa());
                   Address address = student.getAddress();
                   JsonObjectBuilder addressJsonObject = Json
                           .createObjectBuilder()
                           .add("street",address.getStreet())
                           .add("city",address.getCity())
                           .add("state",address.getState())
                           .add("zip",address.getZip());
                   studentJsonObject.add("address",addressJsonObject);
                   studentsJsonArray.add(studentJsonObject);
                });
                classInfoJsonObject.add("students",studentsJsonArray);
                classInfosJsonArray.add(classInfoJsonObject);
            });
            writer.write(classInfosJsonArray.build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

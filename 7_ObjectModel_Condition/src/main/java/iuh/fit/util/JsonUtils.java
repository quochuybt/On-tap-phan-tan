package iuh.fit.util;

import iuh.fit.model.Address;
import iuh.fit.model.ClassInfo;
import iuh.fit.model.Student;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Admin 4/15/2025
 *  
**/
public class JsonUtils {
    public static List<Student> listStudentsByClassName(String className, String fileName){
        List<Student> res = new ArrayList<>();

        try(JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray classInfos = reader.readArray();
            classInfos.forEach(classInfoValue -> {
                JsonObject classInforObject = classInfoValue.asJsonObject();
                String name =  classInforObject.getString("name");
                if (name.equalsIgnoreCase(className)) {
                    JsonArray students = classInforObject.getJsonArray("students");
                    students.forEach(studentValue -> {
                        JsonObject studentObject = studentValue.asJsonObject();
                        String studentName = studentObject.getString("name");
                        int age = studentObject.getInt("age");
                        double gpa = studentObject.getJsonNumber("gpa").doubleValue();
                        JsonObject addressJsonObject = studentObject.getJsonObject("address");
                        Address address = new Address(
                                addressJsonObject.getString("street"),
                                addressJsonObject.getString("city"),
                                addressJsonObject.getString("state"),
                                addressJsonObject.getString("zip")
                        );
                        Student student = new Student(
                                studentName,age,gpa,address
                        );
                        res.add(student);
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void writeStudentsToJson(List<Student> students, String fileName){
        Map<String,Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try(JsonWriter jsonWriter = jsonWriterFactory.createWriter(new  FileWriter(fileName))) {
            JsonArrayBuilder studentsJsonArray = Json.createArrayBuilder();
            students.forEach(student -> {
                JsonObjectBuilder studentJsonObject = Json
                        .createObjectBuilder()
                        .add("name",student.getName())
                        .add("age",student.getAge())
                        .add("gpa",student.getGpa());
                JsonObjectBuilder addressJsonObject = Json
                        .createObjectBuilder()
                        .add("street",student.getAddress().getStreet())
                        .add("city",student.getAddress().getCity())
                        .add("state",student.getAddress().getState())
                        .add("zip",student.getAddress().getZip());
                studentJsonObject.add("address",addressJsonObject);
                studentsJsonArray.add(studentJsonObject);
            });
            jsonWriter.write(studentsJsonArray.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

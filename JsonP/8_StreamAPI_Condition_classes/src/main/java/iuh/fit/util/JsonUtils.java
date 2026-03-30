package iuh.fit.util;

import iuh.fit.model.Address;
import iuh.fit.model.ClassInfo;
import iuh.fit.model.Student;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonGeneratorFactory;
import jakarta.json.stream.JsonParser;

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
    public static List<Student> listStudentsByClassName(String className, String fileName) {
        List<Student> res = new ArrayList<>();

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            Student student = null;
            Address address = null;

            String key = "";
            boolean isMatch = false;
            boolean isInStudentArray = false;
            while(parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY -> {
                        if (key.equalsIgnoreCase("students") && isMatch) isInStudentArray = true;
                    }
                    case END_ARRAY -> {
                        if (isInStudentArray) isInStudentArray = false;
                    }
                    case START_OBJECT -> {
                        if (key.equalsIgnoreCase("address")&&isInStudentArray) {
                            address = new Address();
                        }else if (student == null && isInStudentArray ) {
                            student = new Student();
                        }
                    }
                    case END_OBJECT -> {
                        if (address!=null && isInStudentArray) {
                            student.setAddress(address);
                            address = null;
                        }else if (student != null && isInStudentArray) {
                            res.add(student);
                            student = null;
                        }else if (!isInStudentArray) {
                            isMatch = false;
                        }
                    }
                    case KEY_NAME -> key = parser.getString();
                    case VALUE_STRING -> {
                        String value = parser.getString();
                        if (key.equalsIgnoreCase("name")
                                && !isInStudentArray
                                && value.equalsIgnoreCase(className)
                        ) isMatch = true;
                        else if (isInStudentArray) {
                            switch (key) {
                                case "name" -> student.setName(value);
                                case "street" -> address.setStreet(value);
                                case "city" -> address.setCity(value);
                                case "state" -> address.setState(value);
                                case "zip" -> address.setZip(value);
                            }
                        }
                    }
                    case VALUE_NUMBER -> {
                        if (isInStudentArray) {
                            switch (key) {
                                case "age" -> student.setAge(parser.getInt());
                                case "gpa" -> student.setGpa(parser.getBigDecimal().doubleValue());
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
    public static void writeStudentsToJson(List<Student> students, String fileName) {
        Map<String,Object> config = Map.of(JsonGenerator.PRETTY_PRINTING,true);
        JsonGeneratorFactory jsonGeneratorFactory = Json.createGeneratorFactory(config);

        try(JsonGenerator jsonGenerator= jsonGeneratorFactory.createGenerator(new FileWriter(fileName)) ) {

            jsonGenerator.writeStartArray();
            students.forEach(student -> {
                jsonGenerator.writeStartObject();
                jsonGenerator.write("name",student.getName());
                jsonGenerator.write("age",student.getAge());
                jsonGenerator.write("gpa",student.getGpa());
                Address address = student.getAddress();
                jsonGenerator.writeStartObject("address");
                jsonGenerator.write("street",address.getStreet());
                jsonGenerator.write("city",address.getCity());
                jsonGenerator.write("state",address.getState());
                jsonGenerator.write("zip",address.getZip());
                jsonGenerator.writeEnd();
                jsonGenerator.writeEnd();
            });
            jsonGenerator.writeEnd();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

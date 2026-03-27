package iuh.fit.util;

import iuh.fit.model.Address;
import iuh.fit.model.ClassInfo;
import iuh.fit.model.Student;
import jakarta.json.Json;
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

        try(JsonParser parser = Json.createParser(new FileReader(fileName))) {

            Student student = null;
            Address address = null;

            boolean isMatch = false;
            boolean isStudentArray = false;
            String key = "";

            while(parser.hasNext()) {
                JsonParser.Event event = parser.next();

                switch (event) {
                    case START_ARRAY -> {
                        if (key.equalsIgnoreCase("students")&& isMatch)
                            isStudentArray = true;
                    }
                    case END_ARRAY -> {
                        if (isStudentArray) isStudentArray = false;
                    }
                    case START_OBJECT -> {
                        if (student == null && isStudentArray) {
                            student = new Student();
                        }else if (address == null && isStudentArray) {
                            address = new Address();
                        }
                    }
                    case END_OBJECT -> {
                        if (isStudentArray && address!= null) {
                            student.setAddress(address);
                            address = null;
                        }else if (isStudentArray && student!= null) {
                            res.add(student);
                            student = null;
                        }else if (!isStudentArray) isMatch = false;

                    }
                    case KEY_NAME -> key = parser.getString();
                    case VALUE_STRING -> {
                        String value = parser.getString();
                        if (
                                value.equalsIgnoreCase(className)
                                && key.equalsIgnoreCase("name")
                                && !isStudentArray
                        ) isMatch = true;
                        if (isStudentArray) {
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
                        if (isStudentArray) {
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
}

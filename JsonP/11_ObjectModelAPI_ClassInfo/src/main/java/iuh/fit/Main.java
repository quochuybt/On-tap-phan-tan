package iuh.fit;

import iuh.fit.model.Course;
import iuh.fit.util.JsonUltils;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,Object> map = JsonUltils.getStudentCourseData("json/classInfo.json");
        List<Course> courses = (List<Course>) map.get("courses");
        courses.forEach(System.out::println);
        JsonUltils.writeToFile(map,"courses","json/classInfo2.json");
    }
}

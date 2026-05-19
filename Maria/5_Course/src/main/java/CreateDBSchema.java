import dto.EnrollmentDTO;
import entity.EnrollStatus;
import jakarta.persistence.Persistence;
import service.CourseService;
import service.EnrollmentService;

import java.time.LocalDate;

public class CreateDBSchema {
    public static void main(String[] args) {
//        Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();

        EnrollmentService enrollmentService = new EnrollmentService();
        CourseService courseService = new CourseService();

//        EnrollmentDTO enrollmentDTO = EnrollmentDTO.builder()
//                .studentId("S007")
//                .courseId("C010")
//                .enrollDate(LocalDate.of(2026,05,19))
//                .score(10)
//                .build();

//        boolean res = enrollmentService.addEnrollment(enrollmentDTO);
//
//        System.out.println(res?"add success":"add error");

//        boolean res = enrollmentService.updateScore(enrollmentDTO);
//        System.out.println(res?"update success":"update error");

        courseService.getOpenCoursesByGenre("Pop").forEach(System.out::println);


    }
}

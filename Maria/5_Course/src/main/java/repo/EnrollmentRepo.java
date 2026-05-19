package repo;

import entity.Course;
import entity.Enrollment;
import entity.Student;

import java.time.LocalDate;

public class EnrollmentRepo extends AbstractGenericRepo<Enrollment, Enrollment.EnrollmentId>{

    public EnrollmentRepo() {
        super(Enrollment.class);
    }

    public boolean addEnrollment(Enrollment enrollment) {
        return doInTransaction(em -> {
            Course course = em.find(Course.class,enrollment.getId().getCourse_id());
            Student student = em.find(Student.class,enrollment.getId().getStudent_id());

            if (course == null || student==null) return false;

            enrollment.setCourse(course);
            enrollment.setStudent(student);
            em.persist(enrollment);
            return true;
        });

    }

    public  boolean updateScore(String studentId, String courseId, LocalDate enrollDate,double newScore){
        return doInTransaction(em -> {
            Enrollment.EnrollmentId id = Enrollment.EnrollmentId.builder()
                    .student_id(studentId)
                    .course_id(courseId)
                    .enrollDate(enrollDate)
                    .build();

            Enrollment enrollment = em.find(Enrollment.class,id);
            enrollment.setScore(newScore);

            em.merge(enrollment);
            return true;
        });
    }
}

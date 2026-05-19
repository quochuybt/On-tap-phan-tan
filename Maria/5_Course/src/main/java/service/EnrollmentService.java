package service;

import dto.EnrollmentDTO;
import entity.Enrollment;
import repo.EnrollmentRepo;

public class EnrollmentService {
    private EnrollmentRepo enrollmentRepo;

    public EnrollmentService() {
        this.enrollmentRepo = new EnrollmentRepo();
    }

    public boolean addEnrollment(EnrollmentDTO enrollmentDTO) {

        Enrollment.EnrollmentId id = Enrollment.EnrollmentId.builder()
                .course_id(enrollmentDTO.getCourseId())
                .student_id(enrollmentDTO.getStudentId())
                .enrollDate(enrollmentDTO.getEnrollDate())
                .build();

        Enrollment enrollment = Enrollment.builder()
                .id(id)
                .score(enrollmentDTO.getScore())
                .status(enrollmentDTO.getStatus())
                .build();

        return enrollmentRepo.addEnrollment(enrollment);
    }

    public boolean updateScore(EnrollmentDTO enrollmentDTO) {
        return enrollmentRepo.updateScore(enrollmentDTO.getStudentId(),enrollmentDTO.getCourseId(),enrollmentDTO.getEnrollDate(),enrollmentDTO.getScore());
    }
}

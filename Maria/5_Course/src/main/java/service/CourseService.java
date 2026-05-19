package service;

import dto.CourseStatDTO;
import repo.CourseRepo;

import java.util.List;

public class CourseService {
    private CourseRepo courseRepo;

    public CourseService() {
        this.courseRepo = new CourseRepo();
    }

    public List<CourseStatDTO> getOpenCoursesByGenre(String genreName) {

        return courseRepo.getOpenCoursesByGenre(genreName).stream().map(
                obj -> CourseStatDTO.builder()
                        .courseName((String)obj[0])
                        .instructorName((String)obj[1])
                        .genreName((String)obj[2])
                        .tuitionFee((double)obj[3])
                        .countStudent((long)obj[4])
                        .build()
        ).toList();
    }

}

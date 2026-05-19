package repo;

import entity.Course;

import java.util.List;

public class CourseRepo extends AbstractGenericRepo<Course, String>{
    public CourseRepo() {
        super(Course.class);
    }

    public List<Object[]> getOpenCoursesByGenre(String genreName) {
        return doInTransaction(em -> {
            String jpql = """
                    select c.name, c.instructor.fullName, c.genre.name, c.tuitionFee, count(e.student)
                    from Course c
                    join c.enrollments e
                    where c.status in ("OPEN") and c.genre.name like :genreName and e.status in ("REGISTERED" ,"COMPLETED") 
                    group by c.name, c.instructor.fullName, c.genre.name, c.tuitionFee
                    """;
            return em.createQuery(jpql,Object[].class).setParameter("genreName",genreName).getResultList();
        });
    }
}

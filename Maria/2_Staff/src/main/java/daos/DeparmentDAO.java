package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import model.Project;
import util.EntityManagerUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeparmentDAO {
    public List<Department> findDepartmentHasMoreThan3Staffs() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT d
                    from Department d
                    where size(d.staffs)>3
                    """;

            TypedQuery<Department> query = em.createQuery(jpql, Department.class);

            return query.getResultList();
        }
    }

    public Map<Department, Long> countStaffByDepartment()
    {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT d,count(s)
                    from Department d
                    join d.staffs s
                    group by d.name
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            Map<Department,Long> res = query.getResultList()
                    .stream()
                    .collect(Collectors.toMap(
                        obj -> (Department) obj[0],
                        obj -> (Long) obj[1]
                    ));

            return res;
//            return query.getResultList();
        }
    }
    public List<Department> findDepartmentHasMoreStaffThanDepartment(String deptId)
    {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT d
                    from Department d
                    where size(d.staffs) > (
                        select size(d2.staffs)
                        from Department d2
                        where d2.id = :deptId
                    )
                    """;

            TypedQuery<Department> query = em.createQuery(jpql, Department.class);
            query.setParameter("deptId",deptId);

            return query.getResultList();
        }
    }
    public List<Department> findDepartmentWithAvgAgeGreaterThan(int age) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT d
                    from Department d
                    join d.staffs s
                    group by d
                    having avg(s.age)>:age
                    """;

            TypedQuery<Department> query = em.createQuery(jpql, Department.class);
            query.setParameter("age",age);
            return query.getResultList();
        }
    }
}

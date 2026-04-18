package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Project;
import util.EntityManagerUtil;

import java.util.List;

public class ProjectDAO {
    public List<Project> findProjectByMinBudget(double minBudget) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT p 
                    from Project p
                    where budget >= :minBudget
                    """;

            TypedQuery<Project> query = em.createQuery(jpql, Project.class);
            query.setParameter("minBudget",minBudget);

            return query.getResultList();
        }
    }
    public List<Project> findProjectHasStaffs() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT p 
                    from Project p
                    where size(p.staffs)>0 
                    """;

            TypedQuery<Project> query = em.createQuery(jpql, Project.class);

            return query.getResultList();
        }
    }
    public List<Project> findProjectsHasMoreStaffThanAverage() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){

            String jpql = """
                    SELECT p 
                    from Project p
                    where size(p.staffs)> (
                        select avg(size(p2.staffs))
                        from Project p2
                        
                    )
                    """;

            TypedQuery<Project> query = em.createQuery(jpql, Project.class);

            return query.getResultList();
        }
    }

}

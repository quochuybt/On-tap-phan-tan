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

}

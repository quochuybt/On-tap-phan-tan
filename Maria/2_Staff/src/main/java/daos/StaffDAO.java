package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Staff;
import util.EntityManagerUtil;

import java.util.List;

public class StaffDAO {
    public List<Staff> findStaffByNameKeyword(String keyword) {
        try(EntityManager em = EntityManagerUtil.getEntityManager()) {

            String jpql = """
                    SELECT s from Staff s where s.name LIKE CONCAT("%",:keyword,"%") 
                    """;

            TypedQuery<Staff> query = em.createQuery(jpql,Staff.class);
            query.setParameter("keyword",keyword);

            return query.getResultList();
        }
    }
    public List<Staff> findStaffByAgeBetween(int minAge, int maxAge) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql = """
                    select s
                    from Staff s
                    where s.age between :minAge and :maxAge
                    """;

            TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);
            query.setParameter("minAge",minAge);
            query.setParameter("maxAge",maxAge);

            return query.getResultList();
        }
    }
}

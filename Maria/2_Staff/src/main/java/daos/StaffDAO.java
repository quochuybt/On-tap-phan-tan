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
    public List<Staff> findStaffWithoutProject() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql = """
                    select s
                    from Staff s
                    where size(s.projects)=0 
                    """;

            TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);

            return query.getResultList();
        }
    }
    public List<Staff> findStaffByPhone(String phone) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql = """
                    select s
                    from Staff s
                    join s.phoneNumbers phoneNumber 
                    where phoneNumber = :phone
                    """;

            TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);
            query.setParameter("phone",phone);

            return query.getResultList();
        }
    }
    public List<Staff> findStaffInProjectWithMaxBudget() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql = """
                    select s
                    from Staff s
                    join s.projects p 
                    where p.budget>= (
                    select max(p2.budget)
                    from Project p2
                    )
                    """;

            TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);

            return query.getResultList();
        }
    }
    public List<Staff> findStaffNotJoinLowBudgetProject(double budget) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql = """
                    select s
                    from Staff s
                    join s.projects p 
                    where not exists (
                        select p2
                        from Project p2
                        where p2.budget<:budget and p.id = p2.id
                    )
                    """;

            TypedQuery<Staff> query = em.createQuery(jpql, Staff.class);
            query.setParameter("budget",budget);

            return query.getResultList();
        }
    }

}

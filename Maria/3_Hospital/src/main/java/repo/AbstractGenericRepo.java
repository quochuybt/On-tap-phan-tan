package repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.function.Function;

public class AbstractGenericRepo {
    public <R> R doInTransaction(Function<EntityManager, R> function) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            R res = function.apply(em);
            tx.commit();
            return res;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new RuntimeException(e);
        }finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

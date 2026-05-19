package repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractGenericRepo<T, ID> implements GenericRepo<T, ID>{

    protected Class<T> entiyClass;

    public AbstractGenericRepo(Class<T> entiyClass) {
        this.entiyClass = entiyClass;
    }

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
            if (tx!= null && tx.isActive()) tx.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public T create(T t) {
        return doInTransaction(em -> {
            em.persist(t);
            return t;
        });
    }

    @Override
    public T update(T t) {
        return doInTransaction(em -> {
            em.merge(t);
            return t;
        });
    }

    @Override
    public boolean delete(ID id) {
        return doInTransaction(em -> {
            T t = em.find(entiyClass,id);
            em.remove(t);
            return true;
        });
    }

    @Override
    public T findById(ID id) {
        return doInTransaction(em -> {
            T t = em.find(entiyClass,id);

            return t;
        });
    }

    @Override
    public List<T> loadAll() {
        String query = "FROM" + entiyClass.getSimpleName();
        return doInTransaction(em -> {
            List<T> res = em.createQuery(query,entiyClass).getResultList();
            return res;
        });
    }
}

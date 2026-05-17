package dao;

import db.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractGenericDao<T, ID> implements GenericDao<T, ID> {
    protected Class<T> entityClass;

    public AbstractGenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected <R> R doInTransaction(Function<EntityManager, R> function) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try{
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            R result = function.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx!=null && tx.isActive()) tx.rollback();
            throw new RuntimeException(e);
        }finally {
            em.close();
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
            T t = em.find(entityClass, id);
            em.remove(t);
            return true;
        });
    }

    @Override
    public T findById(ID id) {
        return doInTransaction(em -> {
            T t = em.find(entityClass, id);
            return t;
        });
    }

    @Override
    public List<T> loadAll() {
        String query = "FROM" + entityClass.getSimpleName();
        return doInTransaction(em -> {
            List<T> list = em.createQuery(query,entityClass).getResultList();
            return list;
        });
    }
}

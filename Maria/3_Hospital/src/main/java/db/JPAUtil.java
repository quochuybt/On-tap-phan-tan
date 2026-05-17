package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil implements AutoCloseable{
    private static final String PERSISTENCE_UNIT_NAME = "mariadb-pu";
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager() {return  emf.createEntityManager();}


    @Override
    public void close() throws Exception {
        if (emf != null) {
            emf.close();
        }
    }
}

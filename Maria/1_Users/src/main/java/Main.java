import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Group;
import model.User;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();

        User user = new User();
        user.setUsername("LeNguyenQuocHuy");
        user.setPassword("huyle123");
        user.setEmail("quochuybt2005@gmail.com");

        Group group = new Group();
        group.setName("Software Engineer");

        user.getGroups().add(group);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}

import daos.ProjectDAO;
import daos.StaffDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import util.EntityManagerUtil;

public class Main {
    public static void main(String[] args) {
        ProjectDAO projectDAO = new ProjectDAO();
        StaffDAO staffDAO = new StaffDAO();

//        staffDAO.findStaffByNameKeyword("a").forEach(System.out::println);

//        projectDAO.findProjectByMinBudget(30000).forEach(System.out::println);

        staffDAO.findStaffByAgeBetween(20,30).forEach(System.out::println);

        EntityManagerUtil.close();
    }
}

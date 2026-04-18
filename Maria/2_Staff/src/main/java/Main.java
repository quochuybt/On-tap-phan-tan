import daos.DeparmentDAO;
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
        DeparmentDAO deparmentDAO = new DeparmentDAO();

//        staffDAO.findStaffByNameKeyword("a").forEach(System.out::println);
//        projectDAO.findProjectByMinBudget(30000).forEach(System.out::println);
//        staffDAO.findStaffByAgeBetween(20,30).forEach(System.out::println);
//        projectDAO.findProjectHasStaffs().forEach(System.out::println);
//        deparmentDAO.findDepartmentHasMoreThan3Staffs().forEach(System.out::println);
//        deparmentDAO.countStaffByDepartment().forEach((k,v)-> System.out.println(k+" "+v));
//        staffDAO.findStaffWithoutProject().forEach(System.out::println);
//        staffDAO.findStaffByPhone("(274) 759-2655").forEach(System.out::println);
//        projectDAO.findProjectsHasMoreStaffThanAverage().forEach(System.out::println);
//        staffDAO.findStaffInProjectWithMaxBudget().forEach(System.out::println);
//        deparmentDAO.findDepartmentHasMoreStaffThanDepartment("D2").forEach(System.out::println);
//        staffDAO.findStaffNotJoinLowBudgetProject(50000).forEach(System.out::println);
        deparmentDAO.findDepartmentWithAvgAgeGreaterThan(30).forEach(System.out::println);
        EntityManagerUtil.close();
    }
}

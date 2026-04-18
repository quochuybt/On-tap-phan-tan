import service.DepartmentService;
import service.StaffService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry("pinkPanther",4121);

        StaffService staffService = (StaffService) registry.lookup("staffService");
        DepartmentService departmentService = (DepartmentService) registry.lookup("departmentService");

        departmentService.findDepartmentWithAvgAgeGreaterThan(30).forEach(System.out::println);
    }
}

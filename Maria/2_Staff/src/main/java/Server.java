import service.DepartmentService;
import service.StaffService;
import service.impl.DepartmentServiceImpl;
import service.impl.StaffServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();

        LocateRegistry.createRegistry(4121);

        StaffService staffService = new StaffServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();

        context.bind("rmi://pinkPanther:4121/staffService",staffService);
        context.bind("rmi://pinkPanther:4121/departmentService",departmentService);

        System.out.println("Server stated");
    }
}

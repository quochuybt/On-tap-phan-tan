package service.impl;

import daos.DeparmentDAO;
import model.Department;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DepartmentServiceImpl extends UnicastRemoteObject implements service.DepartmentService {
    private final DeparmentDAO deparmentDAO;
    public DepartmentServiceImpl() throws RemoteException {
        deparmentDAO = new DeparmentDAO();
    }
    @Override
    public List<Department> findDepartmentWithAvgAgeGreaterThan(int age) throws RemoteException {
        return deparmentDAO.findDepartmentWithAvgAgeGreaterThan(age);
    }
    @Override
    public List<Department> findDepartmentHasMoreStaffThanDepartment(String deptId) throws RemoteException {
        return deparmentDAO.findDepartmentHasMoreStaffThanDepartment(deptId);
    }
}

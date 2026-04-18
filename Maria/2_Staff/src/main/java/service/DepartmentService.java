package service;

import model.Department;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DepartmentService extends Remote {
    List<Department> findDepartmentWithAvgAgeGreaterThan(int age) throws RemoteException;

    List<Department> findDepartmentHasMoreStaffThanDepartment(String deptId) throws RemoteException;
}

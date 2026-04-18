package service;

import model.Staff;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StaffService extends Remote {
    List<Staff> findStaffByAgeBetween(int minAge, int maxAge) throws RemoteException;

    List<Staff> findStaffByPhone(String phone) throws RemoteException;
}

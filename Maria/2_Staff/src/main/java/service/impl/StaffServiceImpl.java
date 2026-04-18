package service.impl;

import daos.StaffDAO;
import model.Staff;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class StaffServiceImpl extends UnicastRemoteObject implements service.StaffService {
    private final StaffDAO staffDAO;


    public StaffServiceImpl() throws RemoteException {
        staffDAO=new StaffDAO();
    }
    @Override
    public List<Staff> findStaffByAgeBetween(int minAge, int maxAge) throws RemoteException {
        return staffDAO.findStaffByAgeBetween(minAge,maxAge);
    }
    @Override
    public List<Staff> findStaffByPhone(String phone) throws RemoteException{
        return staffDAO.findStaffByPhone(phone);
    }
}

package dao;

import entity.Patient;

public class PatientDao extends AbstractGenericDao<Patient , String>{
    public PatientDao() {
        super(Patient.class);
    }
}

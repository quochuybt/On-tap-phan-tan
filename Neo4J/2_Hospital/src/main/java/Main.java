import dao.DepartmentDAO;
import dao.DoctorDAO;
import model.Doctor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DoctorDAO doctorDAO = new DoctorDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        Doctor doctor = new Doctor("DR.101","Nguyen","0123456789","Nhan Khoa");
        System.out.println(doctorDAO.addDoctor(doctor));
        List<Doctor> doctors = DoctorDAO.listDoctorsBySpeciality("Internal");
        doctors.forEach(System.out::println);
        System.out.println(DoctorDAO.updateDiagnosis("PT005","DR.011", "Huy"));
        departmentDAO.getNoOfDOctorsBySpeciality("Internal Medicine").forEach(
                (k,v) -> System.out.println(k + " " + v)
        );
    }
}

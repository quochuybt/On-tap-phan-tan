package dao;

import entity.Appointment;
import entity.AppointmentId;
import entity.Doctor;
import entity.Patient;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDao extends AbstractGenericDao<Appointment, AppointmentId> {

    public AppointmentDao() {
        super(Appointment.class);
    }
    public boolean addAppointment(Appointment appointment) {
        return doInTransaction(em -> {
            Doctor doctor = em.find(Doctor.class, appointment.getId().getDoctorId());
            Patient patient = em.find(Patient.class, appointment.getId().getPatientId());
            if (doctor == null || patient == null) {
                return false;
            }
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            em.persist(appointment);
            return true;
        });
    }

    public List<Object[]> getAppointmentDetails() {
        return doInTransaction(em -> {
            String jpql = """
               select a.id.appointmentTime, a.status, a.doctor.id, a.doctor.fullName, a.patient.id, a.patient.fullName
               FROM Appointment a
                """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        });

    }

}

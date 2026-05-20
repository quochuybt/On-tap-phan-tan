package repo;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;

import java.util.List;

public class AppointmentRepo extends AbstractGenericRepo{
    public boolean addAppointment(Appointment appointment) {
        return doInTransaction(em -> {
            Doctor doctor = em.find(Doctor.class,appointment.getId().getDoctorId());
            Patient patient = em.find(Patient.class,appointment.getId().getPatientId());

            if(doctor==null||patient==null) return false;

            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            em.persist(appointment);
            return true;
        });
    }

    public List<Object[]> getAppointmentDetails() {
        return doInTransaction(em -> {
            String jpql = """
                    select a.doctor.id, a.doctor.fullName,a.patient.id,a.patient.fullName,a.id.appointmentTime,a.status
                    from Appointment a
                    """;
            return em.createQuery(jpql, Object[].class).getResultList();
        });
    }
}

package service;

import dto.AppointmentDTO;
import entity.Appointment;
import entity.Status;
import repo.AppointmentRepo;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {
    private AppointmentRepo appointmentRepo;

    public AppointmentService() {
        this.appointmentRepo = new AppointmentRepo();
    }

    public boolean addAppointment(AppointmentDTO appointmentDTO) {
        Appointment.AppointmentId id = Appointment.AppointmentId.builder()
                .doctorId(appointmentDTO.getDoctorId())
                .patientId(appointmentDTO.getPatientId())
                .appointmentTime(appointmentDTO.getAppointmentTime())
                .build();

        Appointment appointment = Appointment.builder()
                .id(id)
                .status(appointmentDTO.getStatus())
                .build();

        return appointmentRepo.addAppointment(appointment);
    }

    public List<AppointmentDTO> getAppointmentDetails() {
        return appointmentRepo.getAppointmentDetails().stream().map(
                row -> AppointmentDTO.builder()
                        .doctorId((String) row[0])
                        .doctorName((String) row[1])
                        .patientId((String) row[2])
                        .patientName((String) row[3])
                        .appointmentTime((LocalDateTime) row[4])
                        .status((Status) row[5])
                        .build()
        ).toList();
    }
}

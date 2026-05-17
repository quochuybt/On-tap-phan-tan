package service;

import dao.AppointmentDao;
import dao.DoctorDao;
import dao.PatientDao;
import dto.AppointmentDto;
import entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private AppointmentDao appointmentDao;

    public AppointmentService() {
        this.appointmentDao = new AppointmentDao();
    }

    public boolean addAppointment(AppointmentDto appointmentDto) {
        AppointmentId appointmentId = AppointmentId
                .builder()
                .doctorId(appointmentDto.getDoctorId())
                .patientId(appointmentDto.getPatientId())
                .appointmentTime(appointmentDto.getAppointmentTime())
                .build();

        Appointment appointment = Appointment.builder()
                .status(appointmentDto.getStatus())
                .id(appointmentId)
                .build();

        appointmentDao.addAppointment(appointment);
        return true;
    }

    public List<AppointmentDto> getAppointmentDetails() {
        return appointmentDao.getAppointmentDetails().stream().map(
                obj -> new AppointmentDto(
                        (LocalDateTime) obj[0],
                        (Status) obj[1],
                        (String)obj[2],(String)obj[3],(String)obj[4],(String)obj[5]
                )
        ).toList();
    }
}

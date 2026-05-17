import dto.AppointmentDto;
import dto.DoctorWorkloadDTO;
import entity.Status;
import jakarta.persistence.Persistence;
import service.AppointmentService;
import service.DoctorService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateDBSchema {
    public static void main(String[] args) {
//        Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();

        AppointmentService appointmentService = new AppointmentService();
        DoctorService doctorService = new DoctorService();
//
//        AppointmentDto appointmentDto = AppointmentDto.builder()
//                .appointmentTime(LocalDateTime.now())
//                .status(Status.CANCELLED)
//                .doctorId("D01")
//                .patientId("P02")
//                .build();
//
//        if (appointmentService.addAppointment(appointmentDto)) {
//            System.out.println("Appointment added");
//        }

//        List<AppointmentDto> list = appointmentService.getAppointmentDetails();
//
//                list.forEach(System.out::println);

        doctorService.getDoctorWorkload().forEach(System.out::println);
    }
}

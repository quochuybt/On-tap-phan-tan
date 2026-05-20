package service;

import dto.DoctorWorkloadDTO;
import repo.DoctorRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DoctorService {
    private DoctorRepo doctorRepo;

    public DoctorService() {
        this.doctorRepo = new DoctorRepo();
    }

    public List<DoctorWorkloadDTO> getDoctorWorkload() {
        return doctorRepo.getDoctorWorkload().stream().map(
                row -> DoctorWorkloadDTO.builder()
                        .doctorId((String) row[0])
                        .doctorName((String) row[1])
                        .appointmentTime((Date) row[2])
                        .soCa((long) row[3])
                        .build()
        ).toList();
    }
}

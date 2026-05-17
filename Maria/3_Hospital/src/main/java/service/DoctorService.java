package service;

import dao.DoctorDao;
import dto.DoctorWorkloadDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DoctorService {
    private DoctorDao doctorDao;

    public DoctorService() {
        this.doctorDao = new  DoctorDao();
    }

    public List<DoctorWorkloadDTO> getDoctorWorkload() {
        return doctorDao.getDoctorWorkload().stream().map(
                obj -> new DoctorWorkloadDTO(
                        (String)obj[0],(String)obj[1],(Date) obj[2],(long)obj[3]
                )
        ).toList();
    }
}

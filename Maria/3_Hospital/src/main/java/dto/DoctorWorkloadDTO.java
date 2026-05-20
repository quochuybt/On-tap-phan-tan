package dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DoctorWorkloadDTO implements Serializable {
    private String doctorId;
    private String doctorName;
    private Date appointmentTime;
    private long soCa;
}

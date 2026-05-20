package dto;

import entity.Status;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AppointmentDTO implements Serializable {
    private String doctorId;
    private String doctorName;
    private String patientId;
    private String patientName;
    private LocalDateTime appointmentTime;
    private Status status;
}

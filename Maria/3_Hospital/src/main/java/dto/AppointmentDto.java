package dto;

import entity.Status;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AppointmentDto implements Serializable {
    private LocalDateTime appointmentTime;
    private Status status;
    private String doctorId;
    private String doctorName;
    private String patientId;
    private String patientName;
}

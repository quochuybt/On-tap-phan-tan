package entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Builder
public class AppointmentId implements Serializable {
    private String doctorId;
    private String patientId;
    private LocalDateTime appointmentTime;
}

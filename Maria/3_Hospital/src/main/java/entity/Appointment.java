package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointments")
@Getter
@Setter
@ToString(exclude = {"doctor", "patient"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Appointment {

    @EmbeddedId
    private AppointmentId id;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    @EqualsAndHashCode.Include
    @MapsId("doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @EqualsAndHashCode.Include
    @MapsId("patientId")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private Status status;
}

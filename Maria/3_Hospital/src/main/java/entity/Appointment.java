package entity;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "Appointments")
@Entity
public class Appointment {

    @EmbeddedId
    private AppointmentId id;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patientId")
    private Patient patient;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    @Embeddable
    public static class AppointmentId {
        private String doctorId;
        private String patientId;

        @Column(columnDefinition = "DATETIME")
        private LocalDateTime appointmentTime;
    }
}

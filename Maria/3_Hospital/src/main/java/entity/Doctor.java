package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Table(name = "Doctors")
@Entity
@PrimaryKeyJoinColumn(name = "doctorId")
public class Doctor extends Person{

    private String specialty;

    private String hospital;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments = new HashSet<>();
}

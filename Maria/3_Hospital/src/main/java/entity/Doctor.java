package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Doctors")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "doctorId")
public class Doctor extends Person{

    private String specialty;

    private String hospital;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments = new HashSet<>();
}

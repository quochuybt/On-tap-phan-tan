package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patients")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@PrimaryKeyJoinColumn(name = "patientId")
public class Patient extends Person {

    @ElementCollection
    @CollectionTable(
            name = "Phones",
            joinColumns = @JoinColumn(name = "patientId")
    )
    @Column(name = "phoneNumber")
    private Set<String> phones = new HashSet<>();

    private String address;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<>();
}

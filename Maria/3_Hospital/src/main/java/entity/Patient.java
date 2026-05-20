package entity;

import jakarta.persistence.*;
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
@Table(name = "Patients")
@Entity
@PrimaryKeyJoinColumn(name = "patientId")
public class Patient extends Person{

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

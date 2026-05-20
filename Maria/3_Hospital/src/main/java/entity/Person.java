package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Table(name = "People")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @Column(name = "personId")
    protected String id;

    protected String fullName;

    protected String email;
}

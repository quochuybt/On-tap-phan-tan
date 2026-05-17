package entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "People")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Person {

    @Id
    @Column(name = "personId")
    @EqualsAndHashCode.Include
    protected String id;

    protected String fullName;

    protected String email;
}

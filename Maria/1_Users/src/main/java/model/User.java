package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String username;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String  password;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @ToString.Exclude
    private Set<Group> groups = new HashSet<>();

}

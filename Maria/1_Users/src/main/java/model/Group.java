package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();
}

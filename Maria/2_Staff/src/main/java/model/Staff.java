package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staffs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false)
    private int age;

    @Column(name = "staff_name",columnDefinition = "NVARCHAR(100)",nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "phones",joinColumns = @JoinColumn(name = "staff_id"),uniqueConstraints = @UniqueConstraint(columnNames = {"staff_id","number"}))
    @Column(name = "number",nullable = false)
    private Set<String> phoneNumbers;

    @Column(name = "refers")
    private String references;

    @OneToOne(mappedBy = "staff")
    @ToString.Exclude
    private Profile profile;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToMany(mappedBy = "staffs")
    @ToString.Exclude
    private Set<Project> projects = new HashSet<>();
}

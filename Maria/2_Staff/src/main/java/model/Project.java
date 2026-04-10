package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {

    @Id
    @Column(name = "project_id",columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    @Column(columnDefinition = "FLOAT",nullable = false)
    private double budget;

    @Column(name = "project_name")
    private String name;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "staff_project",joinColumns = @JoinColumn(name = "project_id"),inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private Set<Staff> staffs = new HashSet<>();
}

package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "project_id",columnDefinition = "VARCHAR(50)")
    private String id;

    @Column(columnDefinition = "FLOAT",nullable = false)
    private double budget;

    @Column(name = "project_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "staff projects",
        joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private Set<Staff> staffs = new HashSet<>();
}

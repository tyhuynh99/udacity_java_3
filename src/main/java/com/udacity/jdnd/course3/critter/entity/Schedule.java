package com.udacity.jdnd.course3.critter.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employee;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;

}

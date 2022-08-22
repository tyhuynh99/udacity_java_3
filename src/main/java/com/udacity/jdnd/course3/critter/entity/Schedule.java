package com.udacity.jdnd.course3.critter.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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

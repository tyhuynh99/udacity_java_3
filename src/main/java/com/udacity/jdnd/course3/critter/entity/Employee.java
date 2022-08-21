package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "Employee")
public class Employee {
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    // private Set<EmployeeSkill> skills;
    // private Set<DayOfWeek> daysAvailable;
}

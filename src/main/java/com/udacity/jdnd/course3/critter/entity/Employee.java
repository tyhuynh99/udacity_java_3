package com.udacity.jdnd.course3.critter.entity;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
}

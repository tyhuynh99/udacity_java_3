package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByDaysAvailableEquals(DayOfWeek dayAvailable);
}

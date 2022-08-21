package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

public interface EmployeeService {

    EmployeeDTO save(EmployeeDTO dto);

    EmployeeDTO findById(Long id);

    List<EmployeeDTO> findBySkills(LocalDate date, Set<EmployeeSkill> skills);

    void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId);

}

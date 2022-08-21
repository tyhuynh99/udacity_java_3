package com.udacity.jdnd.course3.critter.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.util.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        Employee employee = EmployeeMapper.employeeDTOToEntity(dto);
        Employee savedEmployee = repository.save(employee);
        return EmployeeMapper.employeeToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO findById(Long id) {
        Optional<Employee> employeeOpt = repository.findById(id);
        if (!employeeOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        Employee employee = employeeOpt.get();
        return EmployeeMapper.employeeToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findBySkills(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> availableEmployee = repository.findAllByDaysAvailableEquals(date.getDayOfWeek());
        if (availableEmployee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }

        List<Employee> employees = availableEmployee
                .stream()
                .filter(x -> x.getSkills().containsAll(skills))
                .collect(Collectors.toList());

        if (employees.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }

        List<EmployeeDTO> employeeDTO = employees
                .stream()
                .map(x -> EmployeeMapper.employeeToDTO(x))
                .collect(Collectors.toList());

        return employeeDTO;
    }

    @Override
    public void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId) {
        Optional<Employee> employeeOpt = repository.findById(employeeId);
        if (!employeeOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        Employee employee = employeeOpt.get();
        employee.setDaysAvailable(days);
        repository.save(employee);
    }

}

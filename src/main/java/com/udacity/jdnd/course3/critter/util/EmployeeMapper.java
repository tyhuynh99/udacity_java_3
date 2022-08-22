package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;

public class EmployeeMapper {
    public static Employee employeeDTOToEntity(EmployeeDTO dto) {
        if (dto.getId() == null) {
            return Employee.builder()
                .name(dto.getName())
                .skills(dto.getSkills())
                .daysAvailable(dto.getDaysAvailable())
                .build();
        }
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .skills(dto.getSkills())
                .daysAvailable(dto.getDaysAvailable())
                .build();
    }

    public static EmployeeDTO employeeToDTO(Employee entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .skills(entity.getSkills())
                .daysAvailable(entity.getDaysAvailable())
                .build();
    }
}

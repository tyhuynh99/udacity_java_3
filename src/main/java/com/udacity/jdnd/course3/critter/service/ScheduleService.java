package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

public interface ScheduleService {

    List<ScheduleDTO> findAll();

    List<ScheduleDTO> findByEmployeeId(Long employeeId);

    List<ScheduleDTO> findByPetId(Long petId);

    List<ScheduleDTO> findByCustomerId(Long customerId);

    ScheduleDTO save(ScheduleDTO dto, List<Long> employeeIds, List<Long> petIds);

}

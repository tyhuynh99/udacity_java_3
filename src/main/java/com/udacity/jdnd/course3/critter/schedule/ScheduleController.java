package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.service.ScheduleService;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return service.save(scheduleDTO, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return service.findAll();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return service.findByPetId(petId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return service.findByEmployeeId(employeeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return service.findByCustomerId(customerId);
    }
}

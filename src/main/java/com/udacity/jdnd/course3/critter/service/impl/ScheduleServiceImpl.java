package com.udacity.jdnd.course3.critter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.util.ScheduleMapper;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public ScheduleServiceImpl(ScheduleRepository repository, EmployeeRepository employeeRepository,
            PetRepository petRepository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> ScheduleMapper.ScheduleToDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> findByEmployeeId(Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (!employeeOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        Employee employee = employeeOpt.get();
        List<Schedule> schedules = repository.findAllByEmployee(employee);

        return schedules.stream().map(x -> ScheduleMapper.ScheduleToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> findByPetId(Long petId) {
        Optional<Pet> petOpt = petRepository.findById(petId);
        if (!petOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        Pet pet = petOpt.get();
        List<Schedule> schedules = repository.findAllByPets(pet);

        return schedules.stream().map(x -> ScheduleMapper.ScheduleToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> findByCustomerId(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        Customer customer = customerOpt.get();
        List<Pet> pets = petRepository.findAllByOwnerId(customer.getId());
        if (pets.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        List<Schedule> schedules = new ArrayList<>();
        for (Pet pet : pets) {
            List<Schedule> s = repository.findAllByPets(pet);
            schedules.addAll(s);
        }

        return schedules.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Schedule::getId))),
                        ArrayList::new))
                .stream()
                .map(x -> ScheduleMapper.ScheduleToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public ScheduleDTO save(ScheduleDTO dto, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        if (employees.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        List<Pet> pets = petRepository.findAllById(petIds);
        if (pets.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        Schedule schedule = ScheduleMapper.ScheduleDTOToEntity(dto);
        schedule.setEmployee(employees);
        schedule.setPets(pets);
        Schedule savedSchedule = repository.save(schedule);
        return ScheduleMapper.ScheduleToDTO(savedSchedule);
    }

}

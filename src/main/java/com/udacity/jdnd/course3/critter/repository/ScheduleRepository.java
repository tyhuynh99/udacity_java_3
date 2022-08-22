package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByEmployee(Employee employee);

    List<Schedule> findAllByPets(Pet pet);
}

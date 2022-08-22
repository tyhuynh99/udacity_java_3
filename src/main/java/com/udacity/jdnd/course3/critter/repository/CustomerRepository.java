package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByPets(Pet pet);
}

package com.udacity.jdnd.course3.critter.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.util.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    @Override
    public CustomerDTO create(CustomerDTO dto, List<Long> petIds) {
        Customer customer = CustomerMapper.customerDTOToEntity(dto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.customerToDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(x -> CustomerMapper.customerToDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findByPetId(Long petId) {
        Optional<Pet> petOpt = petRepository.findById(petId);
        if (!petOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        Pet pet = petOpt.get();
        Customer customer = customerRepository.findByPets(pet);

        return CustomerMapper.customerToDTO(customer);
    }

}

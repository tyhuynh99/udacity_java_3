package com.udacity.jdnd.course3.critter.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.util.PetMapper;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository repository;
    private final CustomerRepository customerRepository;

    public PetServiceImpl(PetRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<PetDTO> findByCustomerId(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        Customer customer = customerOpt.get();
        List<Pet> pet = repository.findAllByOwnerId(customer.getId());
        if (pet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        return pet.stream()
                .map(x -> PetMapper.PetToDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> PetMapper.PetToDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO save(PetDTO dto, Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        Customer customer = customerOpt.get();
        Pet pet = PetMapper.PetDTOToEntity(dto);
        pet.setOwnerId(customer.getId());
        Pet savedPet = repository.save(pet);

        return PetMapper.PetToDTO(savedPet);
    }

    @Override
    public PetDTO findById(Long id) {
        Optional<Pet> petOpt = repository.findById(id);
        if (!petOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
        Pet pet = petOpt.get();
        return PetMapper.PetToDTO(pet);
    }

}

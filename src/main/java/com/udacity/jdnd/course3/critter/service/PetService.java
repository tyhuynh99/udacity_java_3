package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import com.udacity.jdnd.course3.critter.pet.PetDTO;

public interface PetService {

    List<PetDTO> findByCustomerId(Long customerId);

    List<PetDTO> findAll();

    PetDTO save(PetDTO dto, Long customerId);

    PetDTO findById(Long id);

}

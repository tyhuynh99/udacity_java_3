package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import com.udacity.jdnd.course3.critter.user.CustomerDTO;

public interface CustomerService {

    CustomerDTO create(CustomerDTO dto, List<Long> petIds);

    List<CustomerDTO> findAll();

    CustomerDTO findByPetId(Long petId);

}

package com.udacity.jdnd.course3.critter.util;

import java.util.stream.Collectors;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;

public class CustomerMapper {
    public static Customer customerDTOToEntity(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .notes(dto.getNotes())
                .build();
    }

    public static CustomerDTO customerToDTO(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .notes(entity.getNotes())
                .petIds(entity.getPets().stream().map(x -> x.getId()).collect(Collectors.toList()))
                .build();
    }
}

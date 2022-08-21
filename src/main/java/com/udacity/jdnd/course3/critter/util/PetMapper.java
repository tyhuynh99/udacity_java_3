package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;

public class PetMapper {
    public static Pet PetDTOToEntity(PetDTO dto) {
        return Pet.builder()
                .id(dto.getId())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .notes(dto.getNotes())
                .type(dto.getType())
                .build();
    }

    public static PetDTO PetToDTO(Pet entity) {
        return PetDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthDate(entity.getBirthDate())
                .notes(entity.getNotes())
                .type(entity.getType())
                .ownerId(entity.getOwner().getId())
                .build();
    }
}

package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.service.PetService;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return service.save(petDTO, petDTO.getOwnerId());
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return service.findById(petId);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return service.findAll();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return service.findByCustomerId(ownerId);
    }
}

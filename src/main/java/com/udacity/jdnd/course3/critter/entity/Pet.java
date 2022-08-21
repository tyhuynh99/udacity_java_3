package com.udacity.jdnd.course3.critter.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.udacity.jdnd.course3.critter.pet.PetType;

import lombok.Data;

@Data
@Entity(name = "Pet")
public class Pet {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onwerId", referencedColumnName = "id")
    private Customer owner;

}

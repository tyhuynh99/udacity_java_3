package com.udacity.jdnd.course3.critter.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.udacity.jdnd.course3.critter.pet.PetType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue
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

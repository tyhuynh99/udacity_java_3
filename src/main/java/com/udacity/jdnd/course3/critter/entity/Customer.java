package com.udacity.jdnd.course3.critter.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets;

}

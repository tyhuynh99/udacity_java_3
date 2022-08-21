package com.udacity.jdnd.course3.critter.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "Customer")
public class Customer {
    @Id
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

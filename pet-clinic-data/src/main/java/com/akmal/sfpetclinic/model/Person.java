package com.akmal.sfpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@MappedSuperclass // Because it inherits and we wont be working with Person Pojo
public class Person extends BaseEntity {


    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;


}

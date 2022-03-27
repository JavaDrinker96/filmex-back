package com.example.filmex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Getter
@Setter
@MappedSuperclass
public abstract class Person extends BaseEntity {

    protected String fullName;

    @OneToOne
    @JoinColumn(name = "photo_id")
    protected Photo photo;

    protected String shortBiography;
}

package com.example.filmex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Profession extends BaseEntity {

    private String name;
}
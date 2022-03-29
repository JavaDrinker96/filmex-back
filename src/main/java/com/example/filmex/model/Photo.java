package com.example.filmex.model;

import com.example.filmex.repository.PhotoType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Photo extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private PhotoType type;

    private String location;
}

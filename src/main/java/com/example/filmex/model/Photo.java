package com.example.filmex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Getter
@Setter
@Entity
public class Photo extends BaseEntity{

    private String name;

    @Lob
    private byte[] content;

    private String path;
}

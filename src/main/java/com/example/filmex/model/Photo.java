package com.example.filmex.model;

import com.example.filmex.repository.PhotoType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Photo extends BaseEntity {

    private String name;

    @Transient
    private byte[] content;

    @Enumerated(EnumType.STRING)
    private PhotoType type;

    private String location;
}

package com.example.filmex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Genre extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    private String name;
    private String description;
}

package com.example.filmex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ViewLink extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    private String siteName;
    private String link;
    private String description;
}

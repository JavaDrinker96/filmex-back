package com.example.filmex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ViewLink extends BaseEntity{

    private String siteName;
    private String link;
    private String description;
}

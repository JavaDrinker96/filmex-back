package com.example.filmex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Quote extends BaseEntity {

    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

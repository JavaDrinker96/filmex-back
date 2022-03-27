package com.example.filmex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Post extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private LocalDate year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    private String slogan;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;

    private Integer durationMinutes;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo logo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> shots;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "viewlink_id"))
    private List<ViewLink> viewLinks;

    @Column(nullable = false)
    private Boolean favourite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ViewStatus status;

    private LocalDate viewDate;

    @Column(nullable = false)
    private Integer score;

    private String comment;

    @OneToMany(mappedBy = "post")
    private List<Quote> quote;
}

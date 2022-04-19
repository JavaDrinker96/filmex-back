package com.example.filmex.dto.post;

import com.example.filmex.dto.actor.ActorDto;
import com.example.filmex.dto.country.CountryDto;
import com.example.filmex.dto.director.DirectorDto;
import com.example.filmex.dto.link.ViewLinkCreateDto;
import com.example.filmex.dto.photo.PhotoCreateDto;
import com.example.filmex.dto.quote.QuoteCreateDto;
import com.example.filmex.model.ViewStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PostCreateDto {

    private String name;
    private LocalDate year;
    private Set<CountryDto> countries;
    private String slogan;
    private Set<DirectorDto> directors;
    private Set<ActorDto> actors;
    private Integer durationMinutes;
    private PhotoCreateDto logo;
    private List<PhotoCreateDto> shots;
    private Set<ViewLinkCreateDto> viewLinks;
    private Boolean favourite;
    private ViewStatus status;
    private Integer score;
    private String comment;
    private List<QuoteCreateDto> quote;
}

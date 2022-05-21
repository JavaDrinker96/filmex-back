package com.example.filmex.converter;

import com.example.filmex.dto.genre.GenreCreateDto;
import com.example.filmex.dto.genre.GenreDto;
import com.example.filmex.model.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter extends AbstractConverter<Genre, GenreDto, GenreCreateDto>{

    protected GenreConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }
}

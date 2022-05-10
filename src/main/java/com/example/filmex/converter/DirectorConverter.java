package com.example.filmex.converter;

import com.example.filmex.dto.director.DirectorCreateDto;
import com.example.filmex.dto.director.DirectorDto;
import com.example.filmex.model.Director;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DirectorConverter extends AbstractConverter<Director, DirectorDto, DirectorCreateDto> {

    protected DirectorConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }
}

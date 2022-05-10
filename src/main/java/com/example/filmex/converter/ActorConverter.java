package com.example.filmex.converter;

import com.example.filmex.dto.actor.ActorCreateDto;
import com.example.filmex.dto.actor.ActorDto;
import com.example.filmex.model.Actor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter extends AbstractConverter<Actor, ActorDto, ActorCreateDto> {

    protected ActorConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }
}


package com.example.filmex.converter;

import com.example.filmex.dto.link.ViewLinkCreateDto;
import com.example.filmex.dto.link.ViewLinkDto;
import com.example.filmex.model.ViewLink;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ViewLinkConverter extends AbstractConverter<ViewLink, ViewLinkDto, ViewLinkCreateDto> {

    protected ViewLinkConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }
}

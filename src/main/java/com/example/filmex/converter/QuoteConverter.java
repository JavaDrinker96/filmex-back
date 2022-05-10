package com.example.filmex.converter;

import com.example.filmex.dto.quote.QuoteCreateDto;
import com.example.filmex.dto.quote.QuoteDto;
import com.example.filmex.model.Quote;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuoteConverter extends AbstractConverter<Quote, QuoteDto, QuoteCreateDto> {

    protected QuoteConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }
}
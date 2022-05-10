package com.example.filmex.dto.quote;

import com.example.filmex.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDto extends BaseDto {

    private Long id;
    protected String content;
    protected String author;
}
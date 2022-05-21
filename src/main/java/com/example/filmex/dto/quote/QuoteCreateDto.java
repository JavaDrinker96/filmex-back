package com.example.filmex.dto.quote;

import com.example.filmex.dto.BaseCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteCreateDto extends BaseCreateDto {

    protected String content;
    protected String author;
}

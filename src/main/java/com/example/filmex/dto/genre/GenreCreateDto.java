package com.example.filmex.dto.genre;

import com.example.filmex.dto.BaseCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreCreateDto extends BaseCreateDto {

    protected String name;
    protected String description;
}

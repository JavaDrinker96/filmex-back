package com.example.filmex.dto.genre;

import com.example.filmex.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDto extends BaseDto {

    private Long id;
    protected String name;
    protected String description;
}

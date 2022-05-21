package com.example.filmex.dto.director;

import com.example.filmex.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorDto extends BaseDto {

    private Long id;
    protected String fullName;
    protected String shortBiography;
}

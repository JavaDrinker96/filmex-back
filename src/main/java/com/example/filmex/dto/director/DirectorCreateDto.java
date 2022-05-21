package com.example.filmex.dto.director;

import com.example.filmex.dto.BaseCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorCreateDto extends BaseCreateDto {

    protected String fullName;
    protected String shortBiography;
}

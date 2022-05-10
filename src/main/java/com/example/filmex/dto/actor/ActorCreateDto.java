package com.example.filmex.dto.actor;

import com.example.filmex.dto.BaseCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorCreateDto extends BaseCreateDto {

    protected String fullName;
    protected String shortBiography;
}

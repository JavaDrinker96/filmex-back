package com.example.filmex.dto.actor;

import com.example.filmex.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorDto extends BaseDto {

    private Long id;
    protected String fullName;
    protected String shortBiography;
}

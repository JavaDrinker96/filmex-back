package com.example.filmex.dto.photo;

import com.example.filmex.repository.PhotoType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoCreateDto {

    protected String name;
    protected byte[] content;
    protected String type;
}
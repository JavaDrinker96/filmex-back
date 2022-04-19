package com.example.filmex.dto.photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDto extends PhotoCreateDto{

    private Long id;
    private String location;
}

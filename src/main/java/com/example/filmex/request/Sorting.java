package com.example.filmex.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class Sorting {
    private Direction direction;
    private String property;
}

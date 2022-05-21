package com.example.filmex.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pagination {

    private Integer page;
    private Integer size;
    private Sorting sorting;
}

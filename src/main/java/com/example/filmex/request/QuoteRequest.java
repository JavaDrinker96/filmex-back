package com.example.filmex.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteRequest {

    private Pagination pagination;
    private String contains;
}

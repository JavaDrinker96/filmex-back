package com.example.filmex.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Pagination pagination;
    private PostFilter filter;
    private String contains;
}

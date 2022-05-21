package com.example.filmex.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityRequest {

    private String login;
    private String password;
}

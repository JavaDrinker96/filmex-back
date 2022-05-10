package com.example.filmex.dto.link;

import com.example.filmex.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewLinkDto extends BaseDto {

    private Long id;
    protected String siteName;
    protected String link;
    protected String description;
}

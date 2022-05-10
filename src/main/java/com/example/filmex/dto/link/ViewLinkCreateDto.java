package com.example.filmex.dto.link;

import com.example.filmex.dto.BaseCreateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewLinkCreateDto extends BaseCreateDto {

    protected String siteName;
    protected String link;
    protected String description;
}

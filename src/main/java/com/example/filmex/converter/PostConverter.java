package com.example.filmex.converter;

import com.example.filmex.dto.post.PostCreateDto;
import com.example.filmex.dto.post.PostDto;
import com.example.filmex.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostConverter extends AbstractConverter<Post, PostDto, PostCreateDto> {

    protected PostConverter(final ModelMapper modelMapper) {
        super(modelMapper);
    }
}

package com.example.filmex.converter;

import com.example.filmex.dto.post.PostCreateDto;
import com.example.filmex.dto.post.PostDto;
import com.example.filmex.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    private final ModelMapper modelMapper;

    public PostConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Post convertPostCreateDtoToPost(final PostCreateDto dto) {
        return modelMapper.map(dto, Post.class);
    }

    public PostDto convertPostToPostDto(final Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    public Post converterPostDtoToPost(final PostDto dto){
        return modelMapper.map(dto, Post.class);
    }
}

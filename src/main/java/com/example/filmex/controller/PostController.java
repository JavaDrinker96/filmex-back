package com.example.filmex.controller;

import com.example.filmex.converter.PostConverter;
import com.example.filmex.dto.post.PostCreateDto;
import com.example.filmex.dto.post.PostDto;
import com.example.filmex.manager.PostManager;
import com.example.filmex.model.Post;
import com.example.filmex.request.PostRequest;
import com.example.filmex.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    private final UserUtil userUtil;
    private final PostManager manager;
    private final PostConverter converter;

    public PostController(final UserUtil userUtil, final PostManager manager, final PostConverter converter) {
        this.userUtil = userUtil;
        this.manager = manager;
        this.converter = converter;
    }

    @PostMapping(value = "create")
    public ResponseEntity<PostDto> create(@RequestBody final PostCreateDto createDto) {
        final Post post = converter.convertCreateDtoToEntity(createDto, Post.class);
        post.setUserId(userUtil.getCurrentUserId());
        final PostDto dto = converter.convertEntityToDto(manager.create(post), PostDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{postId}")
    public ResponseEntity<PostDto> read(@PathVariable final Long postId) {
        final PostDto dto = converter.convertEntityToDto(manager.read(postId), PostDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update/{userId}")
    public ResponseEntity<PostDto> update(@RequestBody final PostDto updateDto) {
        final Post post = converter.convertDtoToEntity(updateDto, Post.class);
        final PostDto dto = converter.convertEntityToDto(manager.update(post), PostDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{postId}")
    public ResponseEntity<?> delete(@PathVariable final Long postId) {
        manager.delete(postId, userUtil.getCurrentUserId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all")
    public ResponseEntity<List<PostDto>> readAll(@RequestBody final PostRequest request) {
        final List<Post> postList = manager.readAll(request, userUtil.getCurrentUserId());
        final List<PostDto> dtoList = postList.stream()
                .map(x -> converter.convertEntityToDto(x, PostDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}

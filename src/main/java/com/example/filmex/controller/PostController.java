package com.example.filmex.controller;

import com.example.filmex.converter.PostConverter;
import com.example.filmex.dto.post.PostCreateDto;
import com.example.filmex.dto.post.PostDto;
import com.example.filmex.manager.PostManager;
import com.example.filmex.model.Post;
import com.example.filmex.request.PostRequest;
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

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostManager manager;
    private final PostConverter converter;

    public PostController(final PostManager manager, final PostConverter converter) {
        this.manager = manager;
        this.converter = converter;
    }

    @PostMapping(value = "crate/{userId}")
    public ResponseEntity<PostDto> create(@RequestBody final PostCreateDto createDto, @PathVariable final Long userId) {
        final Post post = converter.convertPostCreateDtoToPost(createDto);
        final PostDto dto = converter.convertPostToPostDto(manager.create(post, userId));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{postId}")
    public ResponseEntity<PostDto> read(@PathVariable final Long postId) {
        final PostDto dto = converter.convertPostToPostDto(manager.read(postId));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update/{userId}")
    public ResponseEntity<PostDto> update(@RequestBody final PostDto updateDto, @PathVariable final Long userId) {
        final Post post = converter.converterPostDtoToPost(updateDto);
        final PostDto dto = converter.convertPostToPostDto(manager.update(post, userId));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{postId}/{userId}")
    public ResponseEntity<?> delete(@PathVariable final Long postId,@PathVariable final Long userId) {
        manager.delete(postId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all/{userId}")
    public ResponseEntity<List<PostDto>> readAll(@RequestBody final PostRequest request){
        final List<Post> postList = manager.
    }
}

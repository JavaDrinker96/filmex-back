package com.example.filmex.manager;

import com.example.filmex.model.Post;
import com.example.filmex.request.PostRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostManager {

    @Transactional
    Post create(Post post);

    Post read(Long id);

    Post update(Post newPost);

    void delete(Long id, Long userId);

    List<Post> readAll(PostRequest request, Long userId);
}

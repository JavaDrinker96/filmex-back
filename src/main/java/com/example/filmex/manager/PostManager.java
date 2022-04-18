package com.example.filmex.manager;

import com.example.filmex.model.Post;
import com.example.filmex.service.PostService;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

public interface PostManager {

    @Transactional
    Post create(Post post, Long userId);

    @SneakyThrows
    Post read(Long id);

    Post update(Post newPost, Long userId);

    void delete(Long id, Long userId);
}

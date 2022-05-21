package com.example.filmex.service;

import com.example.filmex.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService extends BaseService<Post> {
    List<Post> findPostsByName(Long userId, String name, Pageable pageable);
}

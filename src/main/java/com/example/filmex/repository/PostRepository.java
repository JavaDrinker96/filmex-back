package com.example.filmex.repository;

import com.example.filmex.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends BaseRepository<Post> {

    Page<Post> findAllByUserIdAndNameLike(Long userId, String name, Pageable pageable);
}

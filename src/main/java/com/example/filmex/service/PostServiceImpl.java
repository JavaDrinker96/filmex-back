package com.example.filmex.service;

import com.example.filmex.model.Post;
import com.example.filmex.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends AbstractService<Post, PostRepository> implements PostService {

    public PostServiceImpl(final PostRepository repository) {
        super(repository, Post.class);
    }

    @Override
    public List<Post> findPostsByName(final Long userId, final String name, final Pageable pageable) {
        return repository.findAllByUserIdAndNameLike(userId, name, pageable).getContent();
    }
}

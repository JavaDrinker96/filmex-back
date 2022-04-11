package com.example.filmex.service;

import com.example.filmex.model.Post;
import com.example.filmex.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends AbstractService<Post, PostRepository> implements PostService {

    public PostServiceImpl(final PostRepository repository, final Class<Post> entityClass) {
        super(repository, entityClass);
    }
}

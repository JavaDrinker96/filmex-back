package com.example.filmex.service;

import com.example.filmex.model.Genre;
import com.example.filmex.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends AbstractService<Genre, GenreRepository> implements GenreService {

    public GenreServiceImpl(final GenreRepository repository) {
        super(repository, Genre.class);
    }
}

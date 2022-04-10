package com.example.filmex.service;

import com.example.filmex.model.Director;
import com.example.filmex.repository.DirectorRepository;

public class DirectorServiceImpl extends AbstractService<Director, DirectorRepository> implements DirectorService {

    public DirectorServiceImpl(final DirectorRepository repository, final Class<Director> entityClass) {
        super(repository, entityClass);
    }
}

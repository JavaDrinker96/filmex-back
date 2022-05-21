package com.example.filmex.service;

import com.example.filmex.model.Director;
import com.example.filmex.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl extends AbstractService<Director, DirectorRepository> implements DirectorService {

    public DirectorServiceImpl(final DirectorRepository repository) {
        super(repository, Director.class);
    }
}

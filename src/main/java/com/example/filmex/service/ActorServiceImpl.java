package com.example.filmex.service;

import com.example.filmex.model.Actor;
import com.example.filmex.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends AbstractService<Actor, ActorRepository> implements ActorService {

    public ActorServiceImpl(final ActorRepository repository, final Class<Actor> entityClass) {
        super(repository, entityClass);
    }
}

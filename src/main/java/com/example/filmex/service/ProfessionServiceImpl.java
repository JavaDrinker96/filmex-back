package com.example.filmex.service;

import com.example.filmex.model.Profession;
import com.example.filmex.repository.ProfessionRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessionServiceImpl extends AbstractService<Profession, ProfessionRepository> implements ProfessionService {

    public ProfessionServiceImpl(final ProfessionRepository repository, final Class<Profession> entityClass) {
        super(repository, entityClass);
    }
}

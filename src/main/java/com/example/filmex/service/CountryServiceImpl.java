package com.example.filmex.service;

import com.example.filmex.model.Country;
import com.example.filmex.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends AbstractService<Country, CountryRepository> implements CountryService {

    public CountryServiceImpl(final CountryRepository repository, final Class<Country> entityClass) {
        super(repository, entityClass);
    }
}

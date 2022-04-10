package com.example.filmex.service;

import com.example.filmex.model.Quote;
import com.example.filmex.repository.QuoteRepository;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl extends AbstractService<Quote, QuoteRepository> implements QuotService {

    public QuoteServiceImpl(final QuoteRepository repository, final Class<Quote> entityClass) {
        super(repository, entityClass);
    }
}

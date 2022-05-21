package com.example.filmex.service;

import com.example.filmex.model.Quote;
import com.example.filmex.repository.QuoteRepository;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl extends AbstractService<Quote, QuoteRepository> implements QuoteService {

    public QuoteServiceImpl(final QuoteRepository repository) {
        super(repository, Quote.class);
    }
}

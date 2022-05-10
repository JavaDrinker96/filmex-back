package com.example.filmex.service;

import com.example.filmex.model.ViewLink;
import com.example.filmex.repository.ViewLinkRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewLinkServiceImpl extends AbstractService<ViewLink, ViewLinkRepository> implements ViewLinkService {

    public ViewLinkServiceImpl(final ViewLinkRepository repository) {
        super(repository, ViewLink.class);
    }
}
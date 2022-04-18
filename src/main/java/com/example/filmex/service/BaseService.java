package com.example.filmex.service;

import com.example.filmex.model.BaseEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<E extends BaseEntity> {

    E create(E entity);

    E read(Long id);

    E update(E newEntity);

    void delete(Long id);

    List<E> readAll(Pageable pageable);
}

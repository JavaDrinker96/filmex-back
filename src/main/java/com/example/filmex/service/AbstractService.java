package com.example.filmex.service;

import com.example.filmex.exception.NotFoundException;
import com.example.filmex.exception.NullParameterException;
import com.example.filmex.exception.UpdateException;
import com.example.filmex.model.BaseEntity;
import com.example.filmex.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

public abstract class AbstractService<E extends BaseEntity, R extends BaseRepository<E>> implements BaseService<E> {

    protected final R repository;
    protected final Class<E> entityClass;

    public AbstractService(final R repository, Class<E> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

    @Override
    public E create(final E entity) {
        if (Objects.isNull(entity)) {
            throw new NullParameterException(
                    String.format("Error creating an entity of the  %s. An entity cannot have a value of null",
                            entityClass.getTypeName()));
        }
        return repository.save(entity);
    }

    @Override
    public E update(final E newEntity) {
        if (Objects.isNull(newEntity)) {
            throw new NullParameterException(
                    String.format("Update error an entity of the  %s. An entity cannot have a value of null",
                            entityClass.getTypeName()));
        }
        if (newEntity.getId() == null) {
            throw new UpdateException(
                    String.format("Error updating an entity of the %s type. It is not possible to update " +
                            "an entity with a null id", entityClass.getTypeName()));
        }
        return repository.save(newEntity);
    }

    @Override
    public E read(final Long id) {
        if (Objects.isNull(id)) {
            throw new NullParameterException(
                    String.format("Read error an entity of the type %s. An entity cannot have a value of null",
                            entityClass.getTypeName()));
        }

        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("An entity of type %s with Id = %d is missing in the database",
                        entityClass.getTypeName(), id)));
    }

    @Override
    public void delete(final Long id) {
        if (Objects.isNull(id)) {
            throw new NullParameterException(
                    String.format("Delete error an entity of the type %s and id = %d. " +
                            "An entity cannot have a value of null", entityClass.getTypeName(), id));
        }

        repository.deleteById(id);
    }

    @Override
    public List<E> readAll(final Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new NullParameterException(
                    "Reading error. The pagination object cannot be null");
        }
        final List<E> entityList = repository.findAll(pageable).getContent();
        if (entityList.isEmpty()) {
            throw new NotFoundException(
                    String.format("Could not find entities of the %s type corresponding to the query in the database",
                            entityClass.getTypeName()));
        }
        return entityList;
    }
}
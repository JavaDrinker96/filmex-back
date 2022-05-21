package com.example.filmex.converter;

import com.example.filmex.dto.BaseCreateDto;
import com.example.filmex.dto.BaseDto;
import com.example.filmex.model.BaseEntity;
import org.modelmapper.ModelMapper;

public abstract class AbstractConverter<E extends BaseEntity, D extends BaseDto, CD extends BaseCreateDto> {

    private final ModelMapper modelMapper;

    protected AbstractConverter(final ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    public E convertCreateDtoToEntity(final CD dto, final Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public D convertEntityToDto(final E entity, final Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public E convertDtoToEntity(final D dto, final Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
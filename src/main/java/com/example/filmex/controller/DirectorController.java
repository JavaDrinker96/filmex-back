package com.example.filmex.controller;

import com.example.filmex.converter.DirectorConverter;
import com.example.filmex.dto.director.DirectorCreateDto;
import com.example.filmex.dto.director.DirectorDto;
import com.example.filmex.dto.genre.GenreDto;
import com.example.filmex.model.Director;
import com.example.filmex.model.Genre;
import com.example.filmex.request.Pagination;
import com.example.filmex.service.DirectorService;
import com.example.filmex.util.UserUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/director")
public class DirectorController {

    private final UserUtil userUtil;
    private final DirectorService service;
    private final DirectorConverter converter;

    public DirectorController(final UserUtil userUtil, final DirectorService service, final DirectorConverter converter) {
        this.userUtil = userUtil;
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "create")
    public ResponseEntity<DirectorDto> create(@RequestBody final DirectorCreateDto createDto) {
        final Director director = converter.convertCreateDtoToEntity(createDto, Director.class);
        director.setUserId(userUtil.getCurrentUserId());
        final DirectorDto dto = converter.convertEntityToDto(service.create(director), DirectorDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{directorId}")
    public ResponseEntity<DirectorDto> read(@PathVariable final Long directorId) {
        final DirectorDto dto = converter.convertEntityToDto(service.read(directorId), DirectorDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update")
    public ResponseEntity<DirectorDto> update(@RequestBody final DirectorDto updateDto) {
        final Director director = converter.convertDtoToEntity(updateDto, Director.class);
        director.setUserId(userUtil.getCurrentUserId());
        final DirectorDto dto = converter.convertEntityToDto(service.update(director), DirectorDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{directorId}")
    public ResponseEntity<?> delete(@PathVariable final Long directorId) {
        service.delete(directorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all")
    public ResponseEntity<List<DirectorDto>> readAll(@RequestBody final Pagination request) {
        final PageRequest pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(
                        request.getSorting().getDirection(),
                        request.getSorting().getProperty()
                )
        );

        final List<Director> genreList = service.readAll(pageable, userUtil.getCurrentUserId());
        final List<DirectorDto> dtoList = genreList.stream()
                .map(x -> converter.convertEntityToDto(x, DirectorDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}

package com.example.filmex.controller;

import com.example.filmex.converter.GenreConverter;
import com.example.filmex.dto.genre.GenreCreateDto;
import com.example.filmex.dto.genre.GenreDto;
import com.example.filmex.dto.quote.QuoteDto;
import com.example.filmex.model.Genre;
import com.example.filmex.model.Quote;
import com.example.filmex.request.Pagination;
import com.example.filmex.service.GenreService;
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
@RequestMapping("/genre")
public class GenreController {

    private final UserUtil userUtil;
    private final GenreService service;
    private final GenreConverter converter;

    public GenreController(final UserUtil userUtil, final GenreService service, final GenreConverter converter) {
        this.userUtil = userUtil;
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "create")
    public ResponseEntity<GenreDto> create(@RequestBody final GenreCreateDto createDto) {
        final Genre genre = converter.convertCreateDtoToEntity(createDto, Genre.class);
        genre.setUserId(userUtil.getCurrentUserId());
        final GenreDto dto = converter.convertEntityToDto(service.create(genre), GenreDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{genreId}")
    public ResponseEntity<GenreDto> read(@PathVariable final Long genreId) {
        final GenreDto dto = converter.convertEntityToDto(service.read(genreId), GenreDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update")
    public ResponseEntity<GenreDto> update(@RequestBody final GenreDto updateDto) {
        final Genre genre = converter.convertDtoToEntity(updateDto, Genre.class);
        genre.setUserId(userUtil.getCurrentUserId());
        final GenreDto dto = converter.convertEntityToDto(service.update(genre), GenreDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{genreId}")
    public ResponseEntity<?> delete(@PathVariable final Long genreId) {
        service.delete(genreId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all")
    public ResponseEntity<List<GenreDto>> readAll(@RequestBody final Pagination request) {
        final PageRequest pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(
                        request.getSorting().getDirection(),
                        request.getSorting().getProperty()
                )
        );

        final List<Genre> genreList = service.readAll(pageable, userUtil.getCurrentUserId());
        final List<GenreDto> dtoList = genreList.stream()
                .map(x -> converter.convertEntityToDto(x, GenreDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}

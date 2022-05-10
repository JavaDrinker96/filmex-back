package com.example.filmex.controller;

import com.example.filmex.converter.ActorConverter;
import com.example.filmex.dto.actor.ActorCreateDto;
import com.example.filmex.dto.actor.ActorDto;
import com.example.filmex.model.Actor;
import com.example.filmex.request.Pagination;
import com.example.filmex.service.ActorService;
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
@RequestMapping("/actor")
public class ActorController {

    private final UserUtil userUtil;
    private final ActorService service;
    private final ActorConverter converter;

    public ActorController(final UserUtil userUtil, final ActorService service, final ActorConverter converter) {
        this.userUtil = userUtil;
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "create")
    public ResponseEntity<ActorDto> create(@RequestBody final ActorCreateDto createDto) {
        final Actor actor = converter.convertCreateDtoToEntity(createDto, Actor.class);
        actor.setUserId(userUtil.getCurrentUserId());
        final ActorDto dto = converter.convertEntityToDto(service.create(actor), ActorDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{actorId}")
    public ResponseEntity<ActorDto> read(@PathVariable final Long actorId) {
        final ActorDto dto = converter.convertEntityToDto(service.read(actorId), ActorDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update")
    public ResponseEntity<ActorDto> update(@RequestBody final ActorDto updateDto) {
        final Actor actor = converter.convertDtoToEntity(updateDto, Actor.class);
        actor.setUserId(userUtil.getCurrentUserId());
        final ActorDto dto = converter.convertEntityToDto(service.update(actor), ActorDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{actorId}")
    public ResponseEntity<?> delete(@PathVariable final Long actorId) {
        service.delete(actorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all")
    public ResponseEntity<List<ActorDto>> readAll(@RequestBody final Pagination request) {
        final PageRequest pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(
                        request.getSorting().getDirection(),
                        request.getSorting().getProperty()
                )
        );

        final List<Actor> genreList = service.readAll(pageable, userUtil.getCurrentUserId());
        final List<ActorDto> dtoList = genreList.stream()
                .map(x -> converter.convertEntityToDto(x, ActorDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}

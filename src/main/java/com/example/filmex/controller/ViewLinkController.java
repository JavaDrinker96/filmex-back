package com.example.filmex.controller;

import com.example.filmex.converter.ViewLinkConverter;
import com.example.filmex.dto.link.ViewLinkCreateDto;
import com.example.filmex.dto.link.ViewLinkDto;
import com.example.filmex.model.ViewLink;
import com.example.filmex.request.Pagination;
import com.example.filmex.service.ViewLinkService;
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
@RequestMapping("/link")
public class ViewLinkController {

    private final UserUtil userUtil;
    private final ViewLinkService service;
    private final ViewLinkConverter converter;

    public ViewLinkController(final UserUtil userUtil, final ViewLinkService service, final ViewLinkConverter converter) {
        this.userUtil = userUtil;
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "create")
    public ResponseEntity<ViewLinkDto> create(@RequestBody final ViewLinkCreateDto createDto) {
        final ViewLink link = converter.convertCreateDtoToEntity(createDto, ViewLink.class);
        link.setUserId(userUtil.getCurrentUserId());
        final ViewLinkDto dto = converter.convertEntityToDto(service.create(link), ViewLinkDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{linkId}")
    public ResponseEntity<ViewLinkDto> read(@PathVariable final Long linkId) {
        final ViewLinkDto dto = converter.convertEntityToDto(service.read(linkId), ViewLinkDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update")
    public ResponseEntity<ViewLinkDto> update(@RequestBody final ViewLinkDto updateDto) {
        final ViewLink link = converter.convertDtoToEntity(updateDto, ViewLink.class);
        link.setUserId(userUtil.getCurrentUserId());
        final ViewLinkDto dto = converter.convertEntityToDto(service.update(link), ViewLinkDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{linkId}")
    public ResponseEntity<?> delete(@PathVariable final Long linkId) {
        service.delete(linkId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all")
    public ResponseEntity<List<ViewLinkDto>> readAll(@RequestBody final Pagination request) {
        final PageRequest pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(
                        request.getSorting().getDirection(),
                        request.getSorting().getProperty()
                )
        );

        final List<ViewLink> linkList = service.readAll(pageable, userUtil.getCurrentUserId());
        final List<ViewLinkDto> dtoList = linkList.stream()
                .map(x -> converter.convertEntityToDto(x, ViewLinkDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}

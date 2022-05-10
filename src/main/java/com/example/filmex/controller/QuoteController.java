package com.example.filmex.controller;

import com.example.filmex.converter.QuoteConverter;
import com.example.filmex.dto.quote.QuoteCreateDto;
import com.example.filmex.dto.quote.QuoteDto;
import com.example.filmex.model.Quote;
import com.example.filmex.request.Pagination;
import com.example.filmex.service.QuoteService;
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
@RequestMapping("/quote")
public class QuoteController {

    private final UserUtil userUtil;
    private final QuoteService service;
    private final QuoteConverter converter;

    public QuoteController(final UserUtil userUtil, final QuoteService service, final QuoteConverter converter) {
        this.userUtil = userUtil;
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "create")
    public ResponseEntity<QuoteDto> create(@RequestBody final QuoteCreateDto createDto) {
        final Quote quote = converter.convertCreateDtoToEntity(createDto, Quote.class);
        quote.setUserId(userUtil.getCurrentUserId());
        final QuoteDto dto = converter.convertEntityToDto(service.create(quote), QuoteDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping(value = "read/{quoteId}")
    public ResponseEntity<QuoteDto> read(@PathVariable final Long quoteId) {
        final QuoteDto dto = converter.convertEntityToDto(service.read(quoteId), QuoteDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping(value = "update")
    public ResponseEntity<QuoteDto> update(@RequestBody final QuoteDto updateDto) {
        final Quote quote = converter.convertDtoToEntity(updateDto, Quote.class);
        quote.setUserId(userUtil.getCurrentUserId());
        final QuoteDto dto = converter.convertEntityToDto(service.update(quote), QuoteDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(value = "delete/{quoteId}")
    public ResponseEntity<?> delete(@PathVariable final Long quoteId) {
        service.delete(quoteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "read-all")
    public ResponseEntity<List<QuoteDto>> readAll(@RequestBody final Pagination request) {
        final PageRequest pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by(
                        request.getSorting().getDirection(),
                        request.getSorting().getProperty()
                )
        );

        final List<Quote> quoteList = service.readAll(pageable, userUtil.getCurrentUserId());
        final List<QuoteDto> dtoList = quoteList.stream()
                .map(x -> converter.convertEntityToDto(x, QuoteDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}
package com.example.filmex.service;

import com.example.filmex.model.Photo;
import org.springframework.core.io.FileSystemResource;

public interface PhotoService {

    Long save(byte[] bytes, Photo photo, Long userId, Long filmId);

    FileSystemResource find(Long photoId);
}

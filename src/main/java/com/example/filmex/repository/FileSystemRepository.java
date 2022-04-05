package com.example.filmex.repository;

import com.example.filmex.model.Photo;

public interface FileSystemRepository {

    String saveImage(Photo photo, Long userId, Long filmId);
}

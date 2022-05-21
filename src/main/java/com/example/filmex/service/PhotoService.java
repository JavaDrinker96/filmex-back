package com.example.filmex.service;

import com.example.filmex.model.Photo;

public interface PhotoService {

    Photo create(Photo photo, Long userId, Long filmId);

    Photo find(Long photoId);

    void delete(Long id);

    void deletePostFolder(Long postId, Long userId);
}

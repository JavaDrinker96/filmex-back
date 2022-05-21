package com.example.filmex.repository;

import com.example.filmex.model.Photo;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;

public interface FileSystemRepository {

    String saveImage(Photo photo, Long userId, Long filmId);

    FileSystemResource findInFileSystem(String location);

    void delete(String location);

    void deletePostFolder(Long postId, Long userId);
}

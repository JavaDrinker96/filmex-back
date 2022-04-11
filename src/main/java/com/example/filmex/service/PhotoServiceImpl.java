package com.example.filmex.service;

import com.example.filmex.exception.NotFoundException;
import com.example.filmex.model.Photo;
import com.example.filmex.repository.FileSystemRepository;
import com.example.filmex.repository.PhotoRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    final FileSystemRepository fileSystemRepository;
    final PhotoRepository photoRepository;

    public PhotoServiceImpl(final FileSystemRepository fileSystemRepository, final PhotoRepository photoRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public Long save(byte[] bytes, final Photo photo, final Long userId, final Long filmId) {
        String location = fileSystemRepository.saveImage(photo, userId, filmId);

        return photoRepository.save(
                Photo.builder()
                        .name(photo.getName())
                        .location(location)
                        .build()
        ).getId();
    }

    @Override
    public FileSystemResource find(final Long photoId) {
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Photo entity with id = %d not found in the database", photoId))
                );

        return fileSystemRepository.findInFileSystem(photo.getLocation());
    }
}

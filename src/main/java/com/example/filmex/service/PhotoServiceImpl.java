package com.example.filmex.service;

import com.example.filmex.exception.NotFoundException;
import com.example.filmex.model.Photo;
import com.example.filmex.repository.FileSystemRepository;
import com.example.filmex.repository.PhotoRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PhotoServiceImpl implements PhotoService {

    final FileSystemRepository fileSystemRepository;
    final PhotoRepository photoRepository;

    public PhotoServiceImpl(final FileSystemRepository fileSystemRepository, final PhotoRepository photoRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo create(final Photo photo, final Long userId, final Long filmId) {
        final String location = fileSystemRepository.saveImage(photo, userId, filmId);
        photo.setLocation(location);
        return photoRepository.save(photo);
    }

    @Override
    public Photo find(final Long photoId) {
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Photo entity with id = %d not found in the database.", photoId))
                );
        final FileSystemResource fileResource = fileSystemRepository.findInFileSystem(photo.getLocation());

        final InputStream inputStream;
        try {
            inputStream = new FileInputStream(fileResource.getFile());
            photo.setContent(FileCopyUtils.copyToByteArray(inputStream));
        } catch (IOException e) {
            throw new NotFoundException(e.getMessage());
        }

        return photo;
    }

    @Override
    public void delete(final Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Photo entity with id = %d not found in the database. Can`t delete.", id))
                );

        fileSystemRepository.delete(photo.getLocation());
    }

    @Override
    public void deletePostFolder(final Long postId, final Long userId) {
        fileSystemRepository.deletePostFolder(postId, userId);
    }
}
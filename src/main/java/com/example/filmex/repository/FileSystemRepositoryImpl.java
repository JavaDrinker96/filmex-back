package com.example.filmex.repository;

import com.example.filmex.exception.NotFoundException;
import com.example.filmex.model.Photo;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Repository
public class FileSystemRepositoryImpl implements FileSystemRepository {

    final String IMAGE_RESOURCES = FileSystemRepositoryImpl.class.getResource("/").getPath();

    @SneakyThrows
    @Override
    public String saveImage(final Photo photo, final Long userId, final Long filmId) {
        final Path newFile = switch (photo.getType()) {
            case FILM -> buildAbsolutePathFilmImage(userId, filmId, photo.getName());
            case PERSONAL -> buildAbsolutePathUserPhoto(userId, photo.getName());
        };

        Files.createDirectories(newFile.getParent());
        Files.write(newFile, photo.getContent());
        return newFile.toAbsolutePath().toString();
    }

    @Override
    public FileSystemResource findInFileSystem(final String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            throw new NotFoundException(String.format("File with path %s not found in the file system", location));
        }
    }

    @SneakyThrows
    @Override
    public void delete(final String location){
        Path deletedImage = Paths.get(location);
        Files.delete(deletedImage);
    }

    @SneakyThrows
    @Override
    public void deletePostFolder(final Long postId, final Long userId){
        final StringBuilder pathBuilder = new StringBuilder(IMAGE_RESOURCES);
        pathBuilder.append(String.format("images/%d/films/%d", userId, postId));
        Path deletedFolder = Paths.get(pathBuilder.toString());
        Files.delete(deletedFolder);
    }

    private Path buildAbsolutePathUserPhoto(final Long userId, final String imageName) {
        final StringBuilder pathBuilder = new StringBuilder(IMAGE_RESOURCES);
        pathBuilder.append(String.format("images/%d/personal/", userId));
        pathBuilder.append(getFormattedNowTime());
        pathBuilder.append(String.format("_%s", imageName));
        return Paths.get(pathBuilder.toString());
    }

    private Path buildAbsolutePathFilmImage(final Long userId, final Long filmId, final String imageName) {
        final StringBuilder pathBuilder = new StringBuilder(IMAGE_RESOURCES);
        pathBuilder.append(String.format("images/%d/films/%d/", userId, filmId));
        pathBuilder.append(getFormattedNowTime());
        pathBuilder.append(String.format("_%s", imageName));
        return Paths.get(pathBuilder.toString());
    }

    private String getFormattedNowTime() {
        return LocalDateTime.now().toString().replaceAll("[-:]", "_");
    }
}

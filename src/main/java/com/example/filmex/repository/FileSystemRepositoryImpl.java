package com.example.filmex.repository;

import com.example.filmex.exception.FileException;
import com.example.filmex.exception.NotFoundException;
import com.example.filmex.model.Photo;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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

    @Override
    public void delete(final String location) {
        Path deletedImage = Paths.get(location);
        try {
            Files.delete(deletedImage);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }

    @Override
    public void deletePostFolder(final Long postId, final Long userId) {
        final StringBuilder pathBuilder = new StringBuilder(IMAGE_RESOURCES);
        pathBuilder.append(String.format("images/%d/films/%d", userId, postId));
        Path deletedFolder = Paths.get(pathBuilder.toString());
        try {
            deleteFolder(deletedFolder);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
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

    private void deleteFolder(final Path path) throws IOException {
        Files.walkFileTree(path,
                new SimpleFileVisitor<>() {
                    // delete directories or folders
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        System.out.printf("Directory is deleted : %s%n", dir);
                        return FileVisitResult.CONTINUE;
                    }

                    // delete files
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        System.out.printf("File is deleted : %s%n", file);
                        return FileVisitResult.CONTINUE;
                    }
                }
        );
    }

    private String getFormattedNowTime() {
        return LocalDateTime.now().toString().replaceAll("[-:]", "_");
    }
}

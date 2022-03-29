package com.example.filmex.repository;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Repository
public class FileSystemRepositoryImpl implements FileSystemRepository {

    final String RESOURCES_DIR = FileSystemRepositoryImpl.class.getResource("/photo").getPath();

    @SneakyThrows
    @Override
    public String saveImage(final byte[] content, final String imageName, final Long id, final PhotoType photoType) {

        final Path newFile = buildAbsolutePathImage(imageName, id, photoType);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath().toString();
    }

    //you can save photos only after saving a post about the movie
    //(a crutch, but otherwise there will be a bad structure for storing pictures)
    private Path buildAbsolutePathImage(final String imageName, final Long id, final PhotoType photoType) {
        final StringBuilder pathBuilder = new StringBuilder(RESOURCES_DIR);
        pathBuilder.append(String.format("/%s/%d", photoType.toString().toLowerCase(), id));
        pathBuilder.append(LocalDateTime.now().toString().replaceAll("[-:]", "_"));
        pathBuilder.append(String.format("_%s", imageName));
        return Paths.get(pathBuilder.toString());
    }
}

package com.example.filmex.repository;

public interface FileSystemRepository {

    String saveImage(byte[] content, String imageName, Long id, PhotoType photoType);
}

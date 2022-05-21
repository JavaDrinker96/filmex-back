package com.example.filmex.manager;

import com.example.filmex.model.Photo;
import com.example.filmex.model.Post;
import com.example.filmex.request.PostFilter;
import com.example.filmex.request.PostRequest;
import com.example.filmex.service.PhotoService;
import com.example.filmex.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostManagerImpl implements PostManager {

    private final PostService postService;
    private final PhotoService photoService;

    public PostManagerImpl(final PostService postService, final PhotoService photoService) {
        this.postService = postService;
        this.photoService = photoService;
    }

    @Transactional
    @Override
    public Post create(final Post post) {
        final Post savedPost = postService.create(post);
        savePhotos(savedPost);
        return savedPost;
    }

    @Override
    public Post read(final Long id) {
        final Post post = postService.read(id);
        mergePhotosToPost(post);
        return post;
    }

    @Override
    public Post update(final Post newPost) {
        photoService.deletePostFolder(newPost.getId(), newPost.getUserId());
        savePhotos(newPost);
        return newPost;
    }

    @Override
    public void delete(final Long id, final Long userId) {
        postService.delete(id);
        photoService.deletePostFolder(id, userId);
    }

    @Override
    @Transactional
    public List<Post> readAll(final PostRequest request, final Long userId) {
        final PostFilter filter = request.getFilter();
        final String contains = request.getContains();
        final PageRequest pageable = PageRequest.of(
                request.getPagination().getPage(),
                request.getPagination().getSize(),
                Sort.by(
                        request.getPagination().getSorting().getDirection(),
                        request.getPagination().getSorting().getProperty()
                )
        );

        final List<Post> postList = new ArrayList<>();

        if (filter.equals(PostFilter.TITLE)) {
            postList.addAll(postService.findPostsByName(userId, contains, pageable));
        } else {
            postList.addAll(postService.readAll(pageable, userId));
        }

        for (int i = 0; i < postList.size(); i++) {
            mergePhotosToPost(postList.get(i));
        }

        return postList;
    }

    private void savePhotos(final Post post) {
        final Photo savedLogo = photoService.create(post.getLogo(), post.getUserId(), post.getId());
        final List<Photo> savedShots = post.getShots().stream()
                .map(shot -> photoService.create(shot, post.getUserId(), post.getId()))
                .collect(Collectors.toList());
        post.setLogo(savedLogo);
        post.setShots(savedShots);
    }

    private void mergePhotosToPost(final Post post) {
        final List<Photo> shots = post.getShots().stream()
                .map(shot -> photoService.find(shot.getId())).collect(Collectors.toList());

        post.setLogo(photoService.find(post.getLogo().getId()));
        post.setShots(shots);
    }
}

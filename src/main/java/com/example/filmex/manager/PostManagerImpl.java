package com.example.filmex.manager;

import com.example.filmex.model.Photo;
import com.example.filmex.model.Post;
import com.example.filmex.request.PostFilter;
import com.example.filmex.request.PostRequest;
import com.example.filmex.service.PhotoService;
import com.example.filmex.service.PostService;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostManagerImpl implements PostManager {

    final PostService postService;
    final PhotoService photoService;

    public PostManagerImpl(final PostService postService, final PhotoService photoService) {
        this.postService = postService;
        this.photoService = photoService;
    }

    @Transactional
    @Override
    public Post create(final Post post, final Long userId) {
        final Post savedPost = postService.create(post);
        mergePhotoToPost(savedPost, userId);
        return savedPost;
    }

    @SneakyThrows
    @Override
    public Post read(final Long id) {
        final Post post = postService.read(id);
        final List<Photo> shots = post.getShots().stream()
                .map(shot -> photoService.find(shot.getId())).collect(Collectors.toList());

        post.setLogo(photoService.find(post.getLogo().getId()));
        post.setShots(shots);
        return post;
    }

    @Override
    public Post update(final Post newPost, final Long userId) {
        photoService.deletePostFolder(newPost.getId(), userId);
        mergePhotoToPost(newPost, userId);
        return newPost;
    }

    @Override
    public void delete(final Long id, final Long userId) {
        postService.delete(id);
        photoService.deletePostFolder(id, userId);
    }

//    public List<Post> readAll(final PostRequest request) {
//        final PostFilter filter = request.getFilter();
//        final String contains = request.getContains();
//        final PageRequest pageable = PageRequest.of(
//                request.getPagination().getPage(),
//                request.getPagination().getSize(),
//                Sort.by(
//                        request.getPagination().getSorting().getDirection(),
//                        request.getPagination().getSorting().getProperty()
//                )
//        );
//
//        final List<Post> postList = switch (filter){
//            case TITLE ->
//        }
//    }

    private void mergePhotoToPost(final Post post, final Long userId) {
        final Photo savedLogo = photoService.create(post.getLogo(), userId, post.getId());
        final List<Photo> savedShots = post.getShots().stream()
                .map(shot -> photoService.create(shot, userId, post.getId()))
                .collect(Collectors.toList());
        post.setLogo(savedLogo);
        post.setShots(savedShots);
    }
}

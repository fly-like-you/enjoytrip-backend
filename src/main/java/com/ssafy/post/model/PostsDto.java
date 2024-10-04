package com.ssafy.post.model;

import java.util.ArrayList;
import java.util.List;

public class PostsDto {
    private List<PostDto> posts = new ArrayList<>();

    public PostsDto(List<PostDto> posts) {
        this.posts = posts;
    }

    public PostsDto() {
    }

    public void addPost(PostDto postDto) {
        posts.add(postDto);
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
}

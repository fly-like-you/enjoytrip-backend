package com.ssafy.post.service;

import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostSummariesDto;
import com.ssafy.post.model.PostSummaryDto;
import com.ssafy.post.model.PostsDto;

public interface PostService {
	
	Integer createPost(PostDto postDto) throws Exception;
    PostsDto searchPostsAll() throws Exception;
    PostsDto findByMemberId(Integer memberId) throws Exception;
    PostDto findById(Integer postId) throws Exception;
    void modifyPost(PostDto postDto) throws Exception;
    void deletePost(Integer postId) throws Exception;
    PostSummariesDto searchPostSummariesAll() throws Exception;
    PostSummaryDto findPostSummaryById(Integer postId) throws Exception;
    PostSummariesDto findPostSummariesByMemberId(Integer memberId) throws Exception;
}

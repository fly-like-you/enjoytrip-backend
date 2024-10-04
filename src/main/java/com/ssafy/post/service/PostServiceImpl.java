package com.ssafy.post.service;

import com.ssafy.post.dao.PostDao;
import com.ssafy.post.dao.PostDaoImpl;
import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostSummariesDto;
import com.ssafy.post.model.PostSummaryDto;
import com.ssafy.post.model.PostsDto;

public class PostServiceImpl implements PostService {
    
    private static final PostServiceImpl instance = new PostServiceImpl();
    private PostDao postDao;

    private PostServiceImpl() {
        postDao = PostDaoImpl.getPostDao();
    }

    public static PostServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Integer createPost(PostDto postDto) throws Exception {
        try {
            return postDao.createPost(postDto);
        } catch (Exception e) {
            throw new Exception("게시글 작성 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public PostsDto searchPostsAll() throws Exception {
        try {
            return postDao.searchPostsAll();
        } catch (Exception e) {
            throw new Exception("모든 게시글 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public PostsDto findByMemberId(Integer memberId) throws Exception {
        try {
            return postDao.findByMemberId(memberId);
        } catch (Exception e) {
            throw new Exception("회원의 게시글 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public PostDto findById(Integer postId) throws Exception {
        try {
            return postDao.findById(postId);
        } catch (Exception e) {
            throw new Exception("게시글 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void modifyPost(PostDto postDto) throws Exception {
        try {
            postDao.modifyPost(postDto);
        } catch (Exception e) {
            throw new Exception("게시글 수정 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void deletePost(Integer postId) throws Exception {
        try {
            postDao.deletePost(postId);
        } catch (Exception e) {
            throw new Exception("게시글 삭제 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public PostSummariesDto searchPostSummariesAll() throws Exception {
        try {
            return postDao.searchPostSummariesAll();
        } catch (Exception e) {
            throw new Exception("모든 게시글 요약 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public PostSummaryDto findPostSummaryById(Integer postId) throws Exception {
        try {
            return postDao.findPostSummaryById(postId);
        } catch (Exception e) {
            throw new Exception("게시글 요약 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public PostSummariesDto findPostSummariesByMemberId(Integer memberId) throws Exception {
        try {
            return postDao.findPostSummariesByMemberId(memberId);
        } catch (Exception e) {
            throw new Exception("회원의 게시글 요약 조회 중 오류가 발생했습니다.", e);
        }
    }
}
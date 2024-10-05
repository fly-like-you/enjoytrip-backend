package com.ssafy.post.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.ParseException;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostsDto;
import com.ssafy.post.service.PostService;
import com.ssafy.post.service.PostServiceImpl;

@WebServlet("/post")
public class PostController extends HttpServlet {
    
    private PostService postService;
    private ObjectMapper objectMapper;
    
    @Override
    public void init() throws ServletException {
        super.init();
        postService = PostServiceImpl.getInstance();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        try {
            switch (action) {
                case "list":
                    list(request, response);
                    break;
                case "view":
                    viewPost(request, response);
                    break;
                case "mvwrite":
                    moveToWritePage(request, response);
                    break;
                case "mvmodify":
                    moveToModifyPage(request, response);
                    break;
                default:
                    sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        try {
            switch (action) {
                case "write":
                    writePost(request, response);
                    break;
                case "modify":
                    modifyPost(request, response);
                    break;
                case "delete":
                    deletePost(request, response);
                    break;
                default:
                    sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (Exception e) {
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostsDto posts = postService.searchPostsAll();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/post/postList.jsp").forward(request, response);
    }

    private void viewPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
            return;
        }
        int postId = Integer.parseInt(postIdStr);
        PostDto post = postService.findById(postId);
        if (post != null) {
            request.setAttribute("post", post);
            request.getRequestDispatcher("post/postContent.jsp").forward(request, response);
        } else {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Post not found");
        }
    }

    private void moveToWritePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/post/write.jsp").forward(request, response);
    }

    private void moveToModifyPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
            return;
        }
        int postId = Integer.parseInt(postIdStr);
        PostDto post = postService.findById(postId);
        if (post != null) {
            request.setAttribute("post", post);
            request.getRequestDispatcher("/WEB-INF/views/post/modify.jsp").forward(request, response);
        } else {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Post not found");
        }
    }

    private void writePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String createdAtString = request.getParameter("createdAt");
        Integer memberId = (Integer) request.getSession().getAttribute("memberId");
        
        if (memberId == null) {
            request.setAttribute("errorMessage", "로그인이 필요합니다.");
            request.getRequestDispatcher("/member/login.jsp").forward(request, response);
            return;
        }

        Timestamp createdAt;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            java.util.Date parsedDate = formatter.parse(createdAtString);
            createdAt = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            // 파싱에 실패한 경우 현재 시간을 사용
            createdAt = new Timestamp(System.currentTimeMillis());
        }

        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setCreatedAt(createdAt);
        postDto.setMemberId(memberId);

        try {
            Integer newPostId = postService.createPost(postDto);
            if (newPostId != null) {
                response.sendRedirect(request.getContextPath() + "/post?action=list");
            } else {
                request.setAttribute("errorMessage", "게시글 작성에 실패했습니다.");
                request.getRequestDispatcher("/enjoy_trip/post?action=list").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "게시글 작성 중 오류가 발생했습니다.");
            request.getRequestDispatcher("/enjoy_trip/post?action=list").forward(request, response);
        }
    }
    private void modifyPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postIdStr = request.getParameter("postId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (postIdStr == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
            return;
        }

        int postId = Integer.parseInt(postIdStr);
        PostDto postDto = new PostDto();
        postDto.setId(postId);
        postDto.setTitle(title);
        postDto.setContent(content);

        postService.modifyPost(postDto);
        // 수정 후 목록 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/post?action=list");
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
            return;
        }
        int postId = Integer.parseInt(postIdStr);
        postService.deletePost(postId);
        response.sendRedirect(request.getContextPath() + "/post?action=list");
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.sendError(status, message);
    }
}
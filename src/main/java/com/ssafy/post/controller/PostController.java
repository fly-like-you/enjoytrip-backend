package com.ssafy.post.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        System.out.println("posts : "+posts);
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("post/post.jsp").forward(request, response);
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
            request.getRequestDispatcher("/WEB-INF/views/post/view.jsp").forward(request, response);
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

    private void writePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        // Assume memberId is stored in session after login
        Integer memberId = (Integer) request.getSession().getAttribute("memberId");
        
        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setMemberId(memberId);

        Integer newPostId = postService.createPost(postDto);
        response.sendRedirect(request.getContextPath() + "/post?action=view&postId=" + newPostId);
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
        response.sendRedirect(request.getContextPath() + "/post?action=view&postId=" + postId);
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
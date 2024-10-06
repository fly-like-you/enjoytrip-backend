package com.ssafy.post.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import java.text.ParseException;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.model.MemberDto;
import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostsDto;
import com.ssafy.post.service.PostService;
import com.ssafy.post.service.PostServiceImpl;


@WebServlet("/post")
public class PostController extends HttpServlet {
	private int pgno;
	private String key;
	private String word;
	private String queryStrig;
	
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
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        
        if (memberDto == null) {
            response.sendRedirect(request.getContextPath() + "/member/login.jsp");
            return;
        }

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
                case "mvWrite":
                    moveToWritePage(request, response);
                    break;
                case "mvModify":
                    moveToModifyPage(request, response);
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        
        if (memberDto == null) {
            response.sendRedirect(request.getContextPath() + "/member/login.jsp");
            return;
        }

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
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("pgno", pgno + "");
            map.put("key", key);
            map.put("word", word);
            
            PostsDto posts = postService.searchPostsAll();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/post/postList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
        }
    }

    private void viewPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
            return;
        }
        try {
            int postId = Integer.parseInt(postIdStr);
            PostDto post = postService.findById(postId);
            if (post != null) {
                request.setAttribute("post", post);
                request.getRequestDispatcher("post/postContent.jsp").forward(request, response);
            } else {
                sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Post not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "글 조회 중 문제 발생!!!");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
        }
    }

    private void moveToWritePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/post/postWrite.jsp").forward(request, response);
    }

    private void moveToModifyPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String postIdStr = request.getParameter("postId");
        if (postIdStr == null) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Post ID is missing");
            return;
        }
        try {
            int postId = Integer.parseInt(postIdStr);
            PostDto post = postService.findById(postId);
            if (post != null) {
                request.setAttribute("post", post);
                request.getRequestDispatcher("/WEB-INF/views/post/modify.jsp").forward(request, response);
            } else {
                sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Post not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "글 수정 페이지 이동 중 문제 발생!!!");
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
        }
    }


    private void writePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        
        if (memberDto == null) {
            response.sendRedirect(request.getContextPath() + "/member/login.jsp");
            return;
        }
        
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String createdAtString = request.getParameter("createdAt");
        
        Timestamp createdAt;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            java.util.Date parsedDate = formatter.parse(createdAtString);
            createdAt = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }

        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setCreatedAt(createdAt);
        postDto.setMemberId(memberDto.getId());  // Assuming MemberDto has getId() method

        try {
            Integer newPostId = postService.createPost(postDto);
            if (newPostId != null) {
                response.sendRedirect(request.getContextPath() + "/post?action=list");
            } else {
                request.setAttribute("errorMessage", "게시글 작성에 실패했습니다.");
                request.getRequestDispatcher("/post?action=list").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "게시글 작성 중 오류가 발생했습니다: " + e.getMessage());
            request.getRequestDispatcher("/post?action=list").forward(request, response);
        }
    }
    private void modifyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        
        String postIdStr = request.getParameter("postId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (postIdStr == null || memberDto == null) {
            response.sendRedirect(request.getContextPath() + "/post?action=list");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdStr);
            PostDto existingPost = postService.findById(postId);
            
            if (existingPost == null) {
                response.sendRedirect(request.getContextPath() + "/post?action=list");
                return;
            }

            // 권한 체크를 일시적으로 제거
            PostDto postDto = new PostDto();
            postDto.setId(postId);
            postDto.setTitle(title);
            postDto.setContent(content);
            postDto.setMemberId(memberDto.getId());

            postService.modifyPost(postDto);
            response.sendRedirect(request.getContextPath() + "/post?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/post?action=list");
        }
    }
    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        
        String postIdStr = request.getParameter("postId");

        if (postIdStr == null || memberDto == null) {
            response.sendRedirect(request.getContextPath() + "/post?action=list");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdStr);
            PostDto existingPost = postService.findById(postId);
            
            if (existingPost == null) {
                request.setAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
                request.getRequestDispatcher("/post?action=list").forward(request, response);
                return;
            }

            // 권한 체크
            if (!existingPost.getMemberId().equals(memberDto.getId())) {
                request.setAttribute("errorMessage", "게시글을 삭제할 권한이 없습니다.");
                request.getRequestDispatcher("/post?action=list").forward(request, response);
                return;
            }

            // 게시글 삭제 수행
            postService.deletePost(postId);
            
            // 삭제 성공 시 목록 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/post?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "게시글 삭제 중 오류가 발생했습니다: " + e.getMessage());
            request.getRequestDispatcher("/post?action=list").forward(request, response);
        }
    }
    

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.sendError(status, message);
    }
}
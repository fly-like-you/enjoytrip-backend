<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세/수정</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 80%; margin: 0 auto; }
        input[type="text"], textarea { width: 100%; padding: 10px; margin: 10px 0; }
        button { padding: 10px 20px; margin-right: 10px; }
    </style>
</head>
<body>


    <div class="container">
        <h1>게시글 상세/수정</h1>
        <form id="postForm" action="${pageContext.request.contextPath}/post" method="post">
            <input type="hidden" name="action" value="modify">
            <input type="hidden" name="postId" value="${post.id}">

            <label for="title">제목:</label>
            <input type="text" id="title" name="title" value="${post.title}" required>

            <label for="content">내용:</label>
            <textarea id="content" name="content" rows="10" required>${post.content}</textarea>

            <label for="author">작성자:</label>
            <input type="text" id="author" name="author" value="${post.author}" readonly>

            <label for="createdAt">작성일:</label>
            <input type="text" id="createdAt" name="createdAt" value="<fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly>

            <button type="submit">수정하기</button>
            <button type="button" onclick="deletePost()">삭제하기</button>
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/post?action=list'">목록으로</button>
        </form>
    </div>

    <script>
        function deletePost() {
            if (confirm('정말로 이 게시글을 삭제하시겠습니까?')) {
                location.href = '${pageContext.request.contextPath}/post?action=delete&postId=${post.id}';
            }
        }
    </script>
</body>
</html>
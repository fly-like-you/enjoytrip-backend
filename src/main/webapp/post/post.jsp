<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.post.model.PostsDto" %>
<%@ page import="com.ssafy.post.model.PostDto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행 경로 게시글</title>
    <style>
        .container { width: 80%; margin: 0 auto; }
        .search-container { display: flex; justify-content: space-between; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .pagination { text-align: center; margin-top: 20px; }
        .pagination a { padding: 8px 16px; text-decoration: none; }
        .pagination a.active { background-color: #4CAF50; color: white; }
    </style>
</head>
<body>
    <div class="container">
        <h1>여행 경로 게시글</h1>

        <div class="search-container">
            <a href="<%=request.getContextPath()%>/post?action=mvwrite">게시글 작성하기</a>
            <form action="<%=request.getContextPath()%>/post" method="get">
                <input type="hidden" name="action" value="list">
                <select name="searchType">
                    <option value="title">제목</option>
                    <option value="author">작성자</option>
                </select>
                <input type="text" name="searchKeyword" placeholder="검색어를 입력하세요">
                <button type="submit">검색</button>
            </form>
        </div>

        <table>
            <thead>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <%
                PostsDto posts = (PostsDto)request.getAttribute("posts");
                if (posts != null && posts.getPosts() != null) {
                    for (PostDto post : posts.getPosts()) {
                %>
                    <tr>
                        <td><%=post.getId()%></td>
                        <td><a href="<%=request.getContextPath()%>/post?action=view&postId=<%=post.getId()%>"><%=post.getTitle()%></a></td>
                        <td><%=post.getAuthor()%></td>
                        <td><%=post.getCreatedAt()%></td>
                    </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>

        <!-- 페이지네이션 로직은 서버 측에서 제공해야 합니다 -->
        <div class="pagination">
            <%
            // 페이지네이션 로직은 서버에서 계산하여 request 속성으로 전달해야 합니다
            Integer currentPage = (Integer)request.getAttribute("currentPage");
            Integer totalPages = (Integer)request.getAttribute("totalPages");
            if (currentPage != null && totalPages != null) {
                if (currentPage > 1) {
            %>
                <a href="<%=request.getContextPath()%>/post?action=list&page=<%=currentPage - 1%>">이전</a>
            <%
                }
                for (int i = 1; i <= totalPages; i++) {
                    if (i == currentPage) {
            %>
                <a href="#" class="active"><%=i%></a>
            <%
                    } else {
            %>
                <a href="<%=request.getContextPath()%>/post?action=list&page=<%=i%>"><%=i%></a>
            <%
                    }
                }
                if (currentPage < totalPages) {
            %>
                <a href="<%=request.getContextPath()%>/post?action=list&page=<%=currentPage + 1%>">다음</a>
            <%
                }
            }
            %>
        </div>
    </div>
</body>
</html>
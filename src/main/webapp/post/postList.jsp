<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>여행정보공유</title>
    <style>
        /* CSS 스타일은 그대로 유지 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .search-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .write-btn {
            padding: 10px 15px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .write-btn:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
            text-align: left;
        }

        th, td {
            padding: 12px;
        }

        th {
            background-color: #f2f2f2;
        }

        .pagination {
            text-align: center;
        }

        .pagination a {
            margin: 0 5px;
            padding: 8px 12px;
            border: 1px solid #ddd;
            text-decoration: none;
            color: #007bff;
        }

        .pagination a.active {
            background-color: #007bff;
            color: white;
        }
        
        
    </style>
</head>
<body>
<div class="container">
    <h1>여행 경로 게시글</h1>

    <div class="search-container">
        <button class="write-btn" onclick="location.href='${root}/post?action=mvWrite'">게시글 작성하기</button>
        <div>
            <select>
                <option value="title">제목조건</option>
                <option value="author">닉네임</option>
                <option value="content">내용</option>
            </select>
            <input type="text" placeholder="검색어..." />
            <button>검색</button>
        </div>
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
        <c:choose>
            <c:when test="${not empty posts.posts}">
                <c:forEach var="post" items="${posts.posts}">
                    <tr>
                        <td>${post.id}</td>
                        <td><a href="${pageContext.request.contextPath}/post?action=view&postId=${post.id}" class="title-link">${post.title}</a></td>
                        <td>${post.author}</td>
                        <td>${post.createdAt}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">게시글이 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <div class="pagination">
        <a href="#">이전</a>
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">다음</a>
    </div>
</div>
</body>
</html>
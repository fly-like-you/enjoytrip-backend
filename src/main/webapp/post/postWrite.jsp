<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>게시글 작성</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
        color: #333;
    }
    
    .container {
        max-width: 600px;
        margin: 0 auto;
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
        color: #4a90e2;
    }

    input[type="text"],
    textarea {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 16px;
    }

    input[type="text"]:focus,
    textarea:focus {
        border-color: #4a90e2;
        outline: none;
        box-shadow: 0 0 5px rgba(74, 144, 226, 0.5);
    }

    .btn-container {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    .btn {
        display: inline-block;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        background-color: #4a90e2;
        color: white;
        text-decoration: none;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn:hover {
        background-color: #357ABD;
    }

    .btn-danger {
        background-color: #e74c3c;
    }

    .btn-danger:hover {
        background-color: #c0392b;
    }
</style>
</head>
<body>
<div class="container">
    <h1>게시글 작성</h1>
    
    <form action="${pageContext.request.contextPath}/post" method="post" onsubmit="setCreatedAt()">
    <input type="hidden" name="action" value="write">
    <input type="hidden" name="author" value="${sessionScope.username}">
    <input type="hidden" name="createdAt" id="createdAt">
    <input type="text" name="title" placeholder="제목" required>
    <textarea name="content" placeholder="내용" rows="10" required></textarea>
    
    <div class="btn-container">
        <div>
            <button type="submit" class="btn">작성하기</button>
            <a href="${pageContext.request.contextPath}/post?action=list" class="btn">목록으로</a>
        </div>
    </div>
</form>
</div>

<script>
function setCreatedAt() {
    var now = new Date();
    document.getElementById('createdAt').value = now.toISOString();
}
</script>
</body>
</html>
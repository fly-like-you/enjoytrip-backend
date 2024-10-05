<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>${post.title}-여행이야기</title>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #e6f3ff;
	margin: 0;
	padding: 0;
	color: #333;
}

.container {
	max-width: 800px;
	margin: 40px auto;
	background: white;
	border-radius: 12px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

.post-header {
	background-image: url('https://source.unsplash.com/1600x900/?travel');
	background-size: cover;
	background-position: center;
	height: 300px;
	position: relative;
}

.post-header::after {
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.4);
}

.post-title {
	position: absolute;
	bottom: 20px;
	left: 20px;
	right: 20px;
	color: white;
	font-size: 2.5em;
	font-weight: 700;
	z-index: 1;
}

.post-meta {
	background-color: #4a89dc;
	color: #ffffff;
	padding: 15px 20px;
	font-size: 0.9em;
	display: flex;
	justify-content: space-between;
}

.post-content {
	padding: 0; /* 패딩 제거 */
	line-height: 1.6;
	min-height: 300px;
	background-color: #f9f9f9;
	border: 1px solid #e0e0e0;
	margin: 20px;
	border-radius: 8px;
	overflow: hidden; /* 내부 요소가 넘치지 않도록 */
}

.post-content textarea {
	width: 100%;
	min-height: 300px;
	padding: 30px; /* div에 있던 패딩을 여기로 이동 */
	border: none;
	outline: none;
	background: transparent;
	font-size: 1.6em;
	line-height: 1.6;
	resize: vertical; /* 수직 리사이즈만 허용 */
	font-family: inherit; /* 부모 요소의 폰트 상속 */
}

.post-title-display {
	font-size: 1.5em;
	font-weight: bold;
	margin: 20px; /* 여백 추가 */
	color: #333;
}

.post-content img {
	max-width: 100%;
	height: auto;
	border-radius: 8px;
	margin: 20px 0;
}

.btn-container {
	display: flex;
	justify-content: space-between;
	padding: 20px;
	background-color: #f0f4f8;
}

.btn {
	padding: 12px 24px;
	border: none;
	border-radius: 25px;
	cursor: pointer;
	text-decoration: none;
	font-weight: 600;
	transition: all 0.3s ease;
	text-transform: uppercase;
	letter-spacing: 1px;
}

.btn-list {
	background-color: #4a89dc; /* 하늘색 */
	color: white;
}

.btn-list:hover {
	background-color: #5d9cec;
}

.btn-edit {
	background-color: #48cfad; /* 바다색 */
	color: white;
}

.btn-edit:hover {
	background-color: #5ddebd;
}

.btn-reset {
	background-color: #f6bb42; /* 모래색 */
	color: white;
}

.btn-reset:hover {
	background-color: #ffce54;
}
</style>
</head>
<body>
	<div class="container">
		<header class="post-header">
			<h1 class="post-title">
				<input type="text" name="title" value="${post.title}"
					style="border: none; outline: none; background: transparent; color: white; font-size: 2.5em; font-weight: 700;"
					required>
			</h1>
		</header>
		<div class="post-meta">
			<span>작성자: ${post.author}</span> <span>작성일: <fmt:formatDate
					value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm" /></span>

		</div>
		<div class="post-title-display">
			제목: ${post.title}
			<!-- 작성한 글 제목 표시 -->
		</div>
		<div class="post-content">
			<textarea name="content"
				style="border: none; outline: none; background: transparent; width: 100%; height: auto; font-size: 1.6em;">${post.content}</textarea>
		</div>
		<div class="btn-container">
			<a href="/enjoy_trip/post?action=list" class="btn btn-list">목록으로</a>
			<div>
				<a href="/enjoy_trip/post?action=modify&postId=${post.id}"
					class="btn btn-edit">수정하기</a>
				<button type="button" class="btn btn-reset" onclick="resetForm()">초기화</button>
			</div>
		</div>
	</div>
	<script>
		function resetForm() {
			document.querySelector('input[name="title"]').value = '';
			document.querySelector('textarea[name="content"]').value = '';
		}
	</script>
</body>
</html>

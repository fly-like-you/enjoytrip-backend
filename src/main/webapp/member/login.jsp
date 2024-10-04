<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>로그인</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- 통합된 CSS -->
    <link href="./assets/css/login_join.css" rel="stylesheet" />
    <link rel="shotcut icon" href="./assets/img/favicon-16x16.png" />
  </head>
  <body>
    <div class="container login-container">
      <div class="row justify-content-center align-items-center min-vh-100">
        <div class="col-lg-4 col-md-6">
          <form class="login-form">
            <div class="logo-img mb-4">
              <a href="index.html"><img src="./assets/img/EnjoyTrip.png" alt="" /></a>
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">이메일</label>
              <input type="email" class="form-control" id="email" required />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">비밀번호</label>
              <input type="password" class="form-control" id="password" required />
            </div>
            <button type="submit" class="btn btn-info w-100 text-white"">로그인</button>
            <div class="mt-3 text-center join">
              <small>아직 회원이 아니신가요? <a href="join.html" class="text-info">회원가입</a></small>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

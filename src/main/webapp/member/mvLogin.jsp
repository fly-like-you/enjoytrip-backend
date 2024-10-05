<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

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
          <form class="login-form" method="POST" action="${root}/member">
            <input type="hidden" name="action" value="login">
            <div class="logo-img mb-4">
              <a href="${root}/main"><img src="./assets/img/EnjoyTrip.png" alt="" /></a>
            </div>
            <div class="mb-3">
              <label for="member-id" class="form-label">아이디</label>
              <input type="text" class="form-control" id="member-id" name="member-id" required />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">비밀번호</label>
              <input type="password" class="form-control" id="password" name="password" required />
            </div>
            <button type="submit" class="btn btn-info w-100 text-white">로그인</button>
            <div class="mt-3 text-center join">
              <small>아직 회원이 아니신가요? <a href="${root}/member?action=mvJoin" class="text-info">회원가입</a></small>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

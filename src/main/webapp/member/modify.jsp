<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>정보수정</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- 통합된 CSS -->
    <link href="./assets/css/login_join.css" rel="stylesheet" />
    <link rel="shotcut icon" href="./assets/img/favicon-16x16.png" />
  </head>
  <body>
    <div class="container join-container">
      <div class="row justify-content-center align-items-center min-vh-100">
        <div class="col-md-6">
          <div class="card signup-card">
            <div class="card-body">
              <h2 class="mb-4 ps-1">정보수정</h2>
              <form>
                <div class="mb-3">
                  <label for="nickname" class="form-label">닉네임</label>
                  <div class="input-group">
                    <input type="text" class="form-control" id="nickname" placeholder="변경하려면 입력하세요"/>
                    <button class="btn btn-info text-white" type="button">중복 확인</button>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">현재 비밀번호</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    placeholder="영문,숫자,특수문자 조합 8자리 이상" />
                </div>
                <hr>
                <div class="mb-3">
                  <label for="password" class="form-label">변경할 비밀번호</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    placeholder="영문,숫자,특수문자 조합 8자리 이상" />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">비밀번호 확인</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    placeholder="영문,숫자,특수문자 조합 8자리 이상" />
                </div>
                <div class="d-grid">
                  <button type="submit" class="btn btn-info btn-next text-white">확인</button>
                </div>
                <div class="mt-3 text-center join">
                  <a href="login.html" class="text-info"><small></small>로그인 화면 가기</small></a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

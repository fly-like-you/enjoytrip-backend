<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원가입</title>
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
              <h2 class="mb-4 ps-1">회원가입</h2>
              <form method="POST" action="${root}/member">
                <input type="hidden" name="action" value="join"/>
                <div class="mb-3">
                  <label for="member-id" class="form-label">아이디</label>
                  <div class="input-group">
                    <input type="text" class="form-control" id="member-id" name="member-id" placeholder="아이디" minlength="4" maxlength="20" required/>
                  </div>
                  <small class="form-text text-muted">아이디는 4자 이상 20자리 이하로 입력해주세요.</small>
                </div>
                <div class="mb-3">
                  <label for="nickname" class="form-label">닉네임</label>

                  <div class="input-group">
                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임" minlength="4" maxlength="20" required/>
                    <button class="btn btn-info text-white" type="button">확인</button>
                  </div>
                </div>

                <div class="mb-3">
                  <label for="password" class="form-label">비밀번호</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    minlength="4"
                    maxlength="20"
                    placeholder="4자 이상 20자리 이하" />
                </div>
                <div class="mb-3">
                  <label for="name" class="form-label">이름</label>
                  <div class="input-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="이름" minlength="2" maxlength="10" required/>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="phone" class="form-label">전화번호</label>
                  <div class="input-group">
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="전화번호" minlength="2" maxlength="12" required/>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">이메일</label>
                  <div class="input-group">
                    <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required/>
                  </div>
                </div>
                <div class="d-grid">
                  <button type="submit" class="btn btn-info btn-next text-white">회원가입</button>
                </div>
                <div class="mt-3 text-center join">
                  <a href="${root}/member?action=mvLogin" class="text-info"><small>로그인 화면 가기</small></a>
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

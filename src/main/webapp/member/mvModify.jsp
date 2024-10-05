<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

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
              <form action="${root}/member" method="POST">
                <input type="hidden" name="action" value="modify"/>
                <input type="hidden" name="id" value="${member.id}"/>

                <div class="mb-3">
                  <label for="nickname" class="form-label">닉네임</label>
                  <div class="input-group">
                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="변경하려면 입력하세요" value="${member.nickname} "/>
                  </div>

                  <label for="user-id" class="form-label">아이디</label>
                  <div class="input-group">
                    <input type="text" class="form-control" name="user-id" id="user-id" value="${member.userId}" readonly/>
                  </div>
                  <label for="name" class="form-label">이름</label>
                  <div class="input-group">
                    <input type="text" class="form-control" name="name" id="name" value="${member.name}" readonly/>
                  </div>
                  <label for="email" class="form-label">이메일</label>
                  <div class="input-group">
                    <input type="text" class="form-control" name="email" id="email" value="${member.email}" readonly/>
                  </div>
                  <label for="phone" class="form-label">휴대폰 번호</label>
                  <div class="input-group">
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="변경하려면 입력하세요" value="${member.phone} "/>
                  </div>
                </div>


                <hr>
                <div class="mb-3">
                  <label for="password" class="form-label">변경할 비밀번호</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    placeholder="4자리 이상 20자리 이하로 작성"
                    minlength="4"
                    maxlength="20"
                  />
                </div>
                <div class="mb-3">
                  <label for="password-confirm" class="form-label">비밀번호 확인</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password-confirm"
                    name="password-confirm"
                    placeholder="4자리 이상 20자리 이하로 작성"
                    minlength="4"
                    maxlength="20"
                  />
                </div>
                <div class="d-grid">
                  <button type="submit" class="btn btn-info btn-next text-white">확인</button>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg bg-light">

  <div class="container">
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div
      class="collapse navbar-collapse justify-content-between"
      id="navbarNav"
    >
      <a class="navbar-brand" href="${root}/main"
        ><img src="./assets/img/EnjoyTrip.png" alt="로고" width="100px"
      /></a>
      <div class="navbar-nav">
        <a class="nav-link" href="${root}/spot"> 관광지 탐방</a>

        <a class="nav-link" href="${root}/review">관광지 리뷰</a>
      </div>
      <div class="user-menu before-login">
        <c:choose>
          <c:when test="${not empty sessionScope.member}">
            안녕하세요, ${sessionScope.member.nickname}님!
            <a class="login-button menu-item" type="button" href="${root}/member?action=logout"
            >로그아웃</a
            >
            <a class="join-button menu-item" type="button" href="${root}/member?action=mvModify&member-id=${sessionScope.member.id}"
            >정보수정</a
            >
          </c:when>
          <c:otherwise>
            <a class="login-button menu-item" type="button" href="${root}/member?action=mvLogin"
            >로그인</a
            >
            <a class="join-button menu-item" type="button" href="${root}/member?action=mvJoin"
            >회원가입</a
            >
          </c:otherwise>
        </c:choose>


      </div>
    </div>
  </div>
</nav>
<link rel="stylesheet" href="./assets/css/header.css" />

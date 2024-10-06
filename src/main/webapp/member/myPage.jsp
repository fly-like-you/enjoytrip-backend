<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<body class="bg-white">
<%@ include file="/common/header.jsp" %>
<main class="container-fluid">

    <div class="container mt-5">
        <!-- 여행 계획 섹션 -->
        <section class="mb-5">
            <h2 class="text-center">여행 계획 확인</h2>
            <hr />
            <div class="row">
                <c:forEach var="trip" items="${trips}">
                    <div class="col-md-4 mb-4">
                        <div class="card" style="width: 300px; height: 300px;">
                            <div class="card-body">
                                <h5 class="card-title">${trip.name}</h5>
                                <p class="card-text">시작일: ${trip.startDate}</p>
                                <p class="card-text">종료일: ${trip.endDate}</p>
                                <p class="card-text">생성 날짜: ${trip.createdAt}</p>
                                <a href="${root}/tripAttraction?action=read&tripId=${trip.id}" class="btn btn-primary">자세히 보기</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </section>

        <!-- 내가 쓴 게시글 섹션 -->
        <section class="mt-5">
            <h2 class="text-center">내가 쓴 게시글</h2>
            <hr />
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card" style="width: 300px; height: 300px;">
                        <div class="card-body">
                            <h5 class="card-title">제주도 여행 후기</h5>
                            <p class="card-text">제주도에서의 멋진 여행 경험을 공유합니다. 아름다운 경치와 맛있는 음식들이 인상적이었어요!</p>
                            <a href="#" class="btn btn-primary">자세히 보기</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card" style="width: 300px; height: 300px;">
                        <div class="card-body">
                            <h5 class="card-title">부산 여행 후기</h5>
                            <p class="card-text">부산에서의 해변과 맛집 탐방이 기억에 남는 즐거운 여행이었습니다.</p>
                            <a href="#" class="btn btn-primary">자세히 보기</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

</main>

<%@ include file="/common/footer.jsp" %>
<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>


<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enjoy Trip</title>
    <link rel="shortcut icon" href="./assets/img/favicon.ico" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <link rel="shotcut icon" href="./assets/img/favicon-16x16.png" />
    <link type="text/css" rel="stylesheet" href="./assets/css/main.css" />
    <link type="text/css" rel="stylesheet" href="./assets/css/global.css" />
    <link rel="stylesheet" href="./assets/css/review.css" />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
</head>

</html>
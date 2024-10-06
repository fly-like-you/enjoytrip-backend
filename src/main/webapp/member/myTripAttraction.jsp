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
            <h5 class="text-center mb-2">${trip.name}</h5>
            <p class="mb-1">시작일: ${trip.startDate}</p>
            <p class="mb-1">종료일: ${trip.endDate}</p>
            <p class="text-muted">생성 날짜: ${trip.createdAt}</p>

            <hr />
            <div class="row">

                <c:forEach var="tripAttraction" items="${tripAttractions}" varStatus="status">
                    <c:set var="attraction" value="${tripAttraction.attraction}" />

                    <div class="card mb-3">
                        <div class="card-header">
                                ${status.index + 1}번째 여행
                        </div>
                        <div class="card-body d-flex">
                            <img src="${attraction.imgUrl}" class="img-thumbnail me-3" style="width: 150px; height: 150px; object-fit: cover;" />
                            <div>
                                <h5 class="card-title">${attraction.title}</h5>
                                <p class="card-text">${attraction.addr}</p>
                                <div class="d-none" id="mapy-${attraction.id}">${attraction.lat}</div>
                                <div class="d-none" id="mapx-${attraction.id}">${attraction.lng}</div>
                                <div class="d-none content-id" id="content-id-${attraction.id}">${attraction.id}</div>
                                <small class="text-muted">${spotType[attraction.type]}</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>



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
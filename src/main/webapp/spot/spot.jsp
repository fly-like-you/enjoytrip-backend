<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="kr">
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
    <style>
      .table thead {
        display: table;
        width: 100%;
      }
      .table tbody {
        display: block;
        max-height: 75vh;
        overflow-y: scroll;
      }

      #map {
        width: 100%;
        height: 800px;
      }
    </style>
  </head>
  <body>
    <%@ include file="/common/header.jsp" %>
    <main class="container-fluid">
      <section class="row">
        <form class="d-flex my-3" onsubmit="return false;" role="search">
          <select id="search-area" class="form-select me-2">
            <option value="0" selected>검색 할 지역 선택</option>
          </select>
          <select id="search-content-id" class="form-select me-2">
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
          <input
            id="search-keyword"
            class="form-control me-2"
            type="search"
            placeholder="검색어"
            aria-label="검색어"
          />
          <button id="btn-search" class="btn btn-outline-success" type="button">
            검색
          </button>
        </form>
      </section>

      <section class="row">
        <div class="col-md-4">
          <table class="table">
            <thead>
              <tr>
                <th>대표이미지</th>
                <th>관광지명</th>
                <th>주소</th>
              </tr>
            </thead>
            <tbody id="trip-list"></tbody>
          </table>
        </div>
        <div class="col-md-8">
          <div id="map" class="container-fluid"></div>
        </div>
      </section>
    </main>

    <%@ include file="/common/footer.jsp" %>

    <script src="./assets/js/key.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbcd0f91614428569cebd399155f81df&libraries=services,clusterer,drawing"
    ></script>
    <script src="./assets/js/includeHTML.js"></script>
    <script src="./assets/js/api/search.js"></script>
  </body>
</html>

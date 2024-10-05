<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="kr">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enjoy Trip</title>
    <link type="text/css" rel="stylesheet" href="./assets/css/global.css" />
    <link type="text/css" rel="stylesheet" href="./assets/css/main.css" />
    <link rel="stylesheet" href="./assets/css/trip.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <link rel="shotcut icon" href="./assets/img/favicon-16x16.png" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css"
    />
    <style>
      #map {
        width: 100%;
        height: 100%;
      }
    </style>
  </head>
  <body>
    <main class="container-fluid">
      <section class="d-flex" style="height: 100vh">
        <!-- 왼쪽 사이드바: 계획 단계 -->
        <section class="side-bar row" style="width: 1000px">
          <div class="col-md-2 d-none d-lg-block">
            <div class="list-group">
              <a href="${root}/main"
                ><img
                  class="mb-5 mt-3"
                  src="./assets/img/EnjoyTrip.png"
                  alt="logo"
                  width="130px"
              /></a>
              <a href="#" class="list-group-item list-group-item-action active"
                >STEP 1<br />동선 짜기</a
              >
            </div>
          </div>

          <!-- 중간 사이드바: 관광지 검색 및 목록 -->
          <div class="col-md-5">
            <h1 id="trip-area-head"></h1>
            <form action="#" method="get" id="trip-area-form">
              <input type="text" class="d-none" name="id" id="trip-area-id" />

              <div class="mb-3">
                <input
                  type="text"
                  class="form-control"
                  name="keyword"
                  placeholder="장소명을 입력하세요"
                  id="trip-area-keyword-input"
                />
              </div>
              <div class="mb-3">
                <input
                  type="radio"
                  name="trip-area"
                  class="btn-check trip-area-btn btn-outline-secondary me-2"
                  id="hot-place-btn"
                  value="12"
                />
                <label class="btn btn-outline-secondary" for="hot-place-btn"
                  >명소</label
                >

                <input
                  type="radio"
                  name="trip-area"
                  class="btn-check trip-area-btn btn-outline-secondary me-2"
                  id="restaurant-btn"
                  value="39"
                />
                <label class="btn btn-outline-secondary" for="restaurant-btn"
                  >식당</label
                >

                <input
                  type="radio"
                  name="trip-area"
                  class="btn-check trip-area-btn btn-outline-secondary me-2"
                  id="festival-btn"
                  value="15"
                />
                <label class="btn btn-outline-secondary" for="festival-btn"
                  >축제</label
                >

                <input
                  type="radio"
                  name="trip-area"
                  class="btn-check trip-area-btn btn-outline-secondary me-2"
                  id="hotel-btn"
                  value="32"
                />
                <label class="btn btn-outline-secondary" for="hotel-btn"
                  >숙박</label
                >

                <input
                  type="radio"
                  name="trip-area"
                  class="btn-check trip-area-btn btn-outline-secondary me-2"
                  id="shopping-btn"
                  value="38"
                />
                <label class="btn btn-outline-secondary" for="shopping-btn"
                  >쇼핑</label
                >
              </div>
            </form>

            <div class="list-group">
              <div
                id="spot-list"
                class="list-group-item overflow-scroll"
                style="height: 83vh"
              ></div>
              <!-- 추가 목록 아이템들 -->
            </div>
          </div>

          <!-- 오른쪽 사이드바: 선택된 관광지 및 동선 계획 -->
          <div class="col-md-5" id="movement-line">
            <h4>선택된 관광지</h4>
            <div id="selected-places" class="list-group">
              <!-- 추가 선택된 장소들 -->
            </div>
          </div>
        </section>

        <div class="flex-grow-1 d-none d-md-block">
          <div id="map" class="container-fluid"></div>
        </div>
      </section>
    </main>

    <%@ include file="/common/footer.jsp" %>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>

    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbcd0f91614428569cebd399155f81df&libraries=services,clusterer,drawing"
    ></script>
    <script src="./assets/js/global.js"></script>
    <script src="./assets/js/key.js"></script>

    <script src="./assets/js/trip.js"></script>
    <script src="./assets/js/move_spot_item.js"></script>
    <script src="./assets/js/spot_drag.js"></script>
    <script src="./assets/js/spot_remove.js"></script>
    <script src="./assets/js/api/kakaoMap.js"></script>
  </body>
</html>

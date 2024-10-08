<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="kr">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enjoy Trip</title>
    <link type="text/css" rel="stylesheet" href="./assets/css/main.css" />
    <link type="text/css" rel="stylesheet" href="./assets/css/global.css" />
    <link type="text/css" rel="stylesheet" href="./assets/css/index.css" />
    <link rel="shotcut icon" href="./assets/img/favicon-16x16.png" />
  </head>

  <body>
    
    <%@ include file="/common/header.jsp" %>
    <main>
      <section class="py-5 text-center">
        <div class="container">
          <h1 class="mb-4">어디로 떠나시나요?</h1>
          <div class="row justify-content-center">
            <div class="col-md-6">
              <div class="input-group">
                <input
                  type="text"
                  id="search"
                  class="form-control search-input"
                  placeholder="검색어를 입력하세요"
                  oninput="showSuggestions()"
                />
                <button
                  class="btn btn-success search-button"
                  onclick="performSearch()"
                >
                  검색
                </button>
                <div id="autocomplete-list" class="autocomplete-items"></div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section class="py-5">
        <div class="container">
          <div
            class="row row-cols-1 row-cols-md-3 g-4"
            id="area-card-list"
          ></div>
        </div>
      </section>
    </main>
    <%@ include file="/common/footer.jsp" %>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
    <script src="./assets/js/key.js"></script>
    <script src="./assets/js/index.js"></script>
    <script src="./assets/js/api/areaCode.js"></script>
  </body>
</html>

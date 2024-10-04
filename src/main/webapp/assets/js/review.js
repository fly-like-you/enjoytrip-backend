let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=EnjoyTrip&_type=json";

fetch(areaUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOption(data));

function makeOption(data) {
  let areas = data.response.body.items.item;
  let sel = document.getElementById("search-area");
  areas.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));

    sel.appendChild(opt);
  });
}
function searchParam(urltemp, key) {
  return new URLSearchParams(urltemp).get(key);
}

// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", () => {
  let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;
  let queryString = `serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  let areaCode = document.getElementById("search-area").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;

  if (parseInt(areaCode)) queryString += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) queryString += `&contentTypeId=${contentTypeId}`;

  let service = ``;
  if (keyword) {
    service = `searchKeyword1`;
    queryString += `&keyword=${keyword}`;
  } else {
    service = `areaBasedList1`;
  }
  let searchUrl = baseUrl + service + "?" + queryString;

  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => makeList(data));
});

// 아이디를 등록 해준다
// 눌렀을 때 querySelector을 통해 id를 확인한다.
const reviewMarkerList = {};
function makeList(data) {
  let trips = data.response.body.items.item;
  let tripList = ``;

  trips.forEach((area) => {
    tripList += `
            <tr id="review-table-list-${area.contentid}" onclick="moveCenter(${area.contentid}); createModal(${area.contentid})" style="display: table; width: 100%;">
              <td><img src="${area.firstimage}" width="260px"></td>
              <td>${area.title}</td>
              <td>${area.addr1} ${area.addr2}</td>
            </tr>
          `;
    reviewMarkerList[area.contentid] = {
      area: area,
    };
  });
  document.getElementById("trip-list").innerHTML = tripList;
}

function moveCenter(contentid) {
  map.setCenter(
    new kakao.maps.LatLng(
      reviewMarkerList[contentid]["area"].mapy,
      reviewMarkerList[contentid]["area"].mapx
    )
  );
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

function createModal(contentId) {
  const data = reviewMarkerList[contentId];
  var marker = new kakao.maps.Marker({
    map: map, // 마커를 표시할 지도
    position: new kakao.maps.LatLng(
      reviewMarkerList[contentId]["area"].mapy,
      reviewMarkerList[contentId]["area"].mapx
    ), // 마커를 표시할 위치
  });
  marker.setMap(map);

  const content = `
  <div class="wrap">
    <div class="info">
      <div class="title">
        ${reviewMarkerList[contentId]["area"].title}
        <div class="close" onclick="closeOverlay(${contentId})" title="닫기"></div>
        </div>
        <div class="body">
        <div class="img"><img src="${reviewMarkerList[contentId]["area"].firstimage}" width="73px" height="70px"></div>
        <div class="desc">
          <div class="ellipsis">${reviewMarkerList[contentId]["area"].addr1}</div>
          <div class="jibun ellipsis">${reviewMarkerList[contentId]["area"].addr2}</div> 
          <div>
          <a href="#" onclick="openModal()" class="link">리뷰 쓰기</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  `;
  var overlay = new kakao.maps.CustomOverlay({
    content: content,
    map: map,
    position: marker.getPosition(),
  });

  kakao.maps.event.addListener(marker, "click", function () {
    overlay.setMap(map);
  });
  kakao.maps.event.addListener(marker, "rightclick", function () {
    marker.setMap(null);
  });
  reviewMarkerList[contentId]["overlay"] = overlay;
  reviewMarkerList[contentId]["marker"] = marker;
}

function closeOverlay(contentId) {
  reviewMarkerList[contentId]["overlay"].setMap(null);
}

function openModal() {
  document.getElementById("reviewModal").style.display = "flex";
}

function closeModal() {
  document.getElementById("reviewModal").style.display = "none";
}

const ratingStars = [...document.getElementsByClassName("rating__star")];

function executeRating(stars) {
  const starClassActive = "rating__star fas fa-star";
  const starClassInactive = "rating__star far fa-star";
  const starsLength = stars.length;
  let i;

  stars.map((star) => {
    star.onclick = () => {
      i = stars.indexOf(star);

      if (star.className === starClassInactive) {
        for (i; i >= 0; --i) stars[i].className = starClassActive;
      } else {
        for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
      }
    };
  });
}

executeRating(ratingStars);

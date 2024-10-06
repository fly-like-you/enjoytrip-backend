import { getSelectedPlaceIds } from './select_place.mjs';
import { postSelectedPlaces } from './trip_api.mjs';

document.querySelector("#trip-submit").addEventListener("click", function () {
  const placeIds = getSelectedPlaceIds();
  postSelectedPlaces(placeIds);
});

paintTable();

function searchParam(urltemp, key) {
  return new URLSearchParams(urltemp).get(key);
}

// 검색 버튼을 누르면..
// 위 데이터를 가지고 공공데이터에 요청.
function paintTable(contentTypeId, keyword) {
  let baseUrl = `http://localhost:8080/enjoy_trip/api/attraction`;
  let queryString = `numOfRows=10&pageNo=1`;
  let areaCode = searchParam(window.location.search, "id");
  document.getElementById("trip-area-id").value = areaCode;

  paintHeader(areaCode);
  if (parseInt(areaCode)) queryString += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) queryString += `&contentTypeId=${contentTypeId}`;

  let searchUrl = baseUrl + "?" + queryString;
  console.log("여행 검색 URL: " + searchUrl);
  fetch(searchUrl)
      .then((response) => response.json())
      .then((data) => paintSpotList(data.attractions));
}

function paintHeader(code) {
  // id를 받아서 h1에 그려주기
  document.querySelector(`#trip-area-head`).textContent = `${cityCode[code]} 여행`;
}

// data의 응답을 받고 화면에 그려주는 함수
function paintSpotList(data) {
  let trips = data;
  let tripList = ``;
  trips.forEach((area) => {
    tripList += `
    <div class="d-flex w-100 justify-content-between align-items-center my-3 spot-list-item">
      <div class="d-flex">
        <img src="${area.imgUrl}" width="100%" />
        <div>
          <h6 id="title-${area.id}" class="mb-1">${area.title}</h6>
          <p id="addr-${area.id}" class="mb-1">${area.addr}</p>
          <div class="d-none" id="mapy-${area.id}">${area.lat}</div>
          <div class="d-none" id="mapx-${area.id}">${area.lng}</div>
          <div class="d-none content-id" id="content-id-${area.id}">${area.id}</div>
          <small id="area-type-${area.id}">${spotType[area.type]}</small>
          
        </div>
      </div>
      <button class="btn btn-sm btn-outline-secondary py-3" onclick="dragMove(); handleButtonClick(event);">+</button>
    </div>
    `;
  });
  document.getElementById("spot-list").innerHTML = tripList;
}

// trip-area-btn 클릭으로 카테고리 별 검색
document.querySelectorAll(`.trip-area-btn`).forEach((elem) => {
  elem.addEventListener("click", () => {
    let code = elem.value;
    paintTable(code);
  });
});

document.getElementById("trip-area-keyword-input").addEventListener("input", (event) => {
  const currentValue = event.target.value;
  const items = document.querySelectorAll(".spot-list-item");

  items.forEach((item) => {
    const titles = item.querySelectorAll("h6");
    titles.forEach((title) => {
      if (title.textContent.includes(currentValue)) {
        item.classList.remove("d-none");
      } else {
        item.classList.add("d-none");
      }
    });
  });
});

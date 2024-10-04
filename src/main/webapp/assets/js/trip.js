// index page 로딩 후 전국의 시도 설정.
let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=EnjoyTrip&_type=json";

paintTable();

function searchParam(urltemp, key) {
  return new URLSearchParams(urltemp).get(key);
}

// 검색 버튼을 누르면..
// 위 데이터를 가지고 공공데이터에 요청.
function paintTable(contentTypeId, keyword) {
  let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;
  let queryString = `serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  let areaCode = searchParam(window.location.search, "id");
  document.getElementById("trip-area-id").value = areaCode;

  paintHeader(areaCode);
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
    .then((data) => paintSpotList(data.response.body.items.item));
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
        <img src="${area.firstimage}" width="100%" />
        <div>
          <h6 id="title-${area.contentid}" class="mb-1">${area.title}</h6>
          <p id="addr-${area.contentid}" class="mb-1">${area.addr1} ${area.addr2}</p>
          <div class="d-none" id="mapy-${area.contentid}">${area.mapy}</div>
          <div class="d-none" id="mapx-${area.contentid}">${area.mapx}</div>
          <div class="d-none content-id" id="content-id-${area.contentid}">${area.contentid}</div>
          <small id="area-type-${area.contentid}">${spotType[area.contenttypeid]}</small>
          
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

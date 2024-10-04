let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=EnjoyTrip&_type=json";

fetch(areaUrl, { method: "GET" })
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    makeOption(data);
  });

function makeOption(data) {
  let areas = data.response.body.items.item;
  createCard(areas);
}

function createCard(areaData) {
  let div = document.getElementById("area-card-list");
  // a 태그의 id는 배열의 code 내용은 name
  let inner = "";
  areaData.forEach((area) => {
    const content = `
    <div class="col">
      <div class="card h-100 border-0">
        <a href="trip.html?id=${area.code}">
          <img src="./assets/img/${area.code}.png" class="card-img-top" alt="${area.name}" />
        </a>
      </div>
    </div>
    `;
    inner += content;
  });
  div.innerHTML = inner;
}

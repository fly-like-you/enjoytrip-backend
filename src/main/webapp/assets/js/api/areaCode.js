let areaUrl =
  "http://localhost:8080/enjoy_trip/api/sido"

fetch(areaUrl, { method: "GET" })
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    makeOption(data);
  });

function makeOption(data) {
  let areas = data;
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
        <a href="trip?action=plan&id=${area.sidoCode}">
          <img src="./assets/img/${area.sidoCode}.png" class="card-img-top" alt="${area.sidoName}" />
        </a>
      </div>
    </div>
    `;
    inner += content;
  });
  div.innerHTML = inner;
}

document.getElementById("spot-list").addEventListener(
  "click",
  function (event) {
    let list = document.getElementById("selected-places");
    // 클릭된 요소가 버튼인지 확인합니다
    if (event.target.tagName === "BUTTON") {
      // 클릭된 버튼의 부모 노드를 가져옵니다
      const parentLi = event.target.parentNode;

      // 부모 노드를 복사합니다
      const newLi = mapping(parentLi.cloneNode(true));

      // 복사된 노드를 ul 요소에 추가합니다
      list.appendChild(newLi);
    }
  },
  true
);

function mapping(node) {
  const divElement = document.createElement("div"); // div 요소 생성
  divElement.classList.add("list-group-item", "me-4", "draggable"); // 클래스 추가
  divElement.setAttribute("draggable", "true"); // draggable 속성 추가

  let id = node.querySelector(".content-id").textContent;
  divElement.setAttribute("id", `selected-list-item-${id}`);

  let title = node.querySelector(`#title-${id}`).textContent;
  let img = node.querySelector("img").src; // img의 src 얻어오기
  let addr = node.querySelector(`#addr-${id}`).textContent;
  let mapx = node.querySelector(`#mapx-${id}`).textContent;
  let mapy = node.querySelector(`#mapy-${id}`).textContent;
  let contentid = node.querySelector(`#content-id-${id}`).textContent;
  let description = node.querySelector(`#area-type-${id}`).textContent;

  divElement.innerHTML = `
            <div class="d-flex w-100 justify-content-between">
                <img src="${img}" alt="" />
                <h6 class="mb-1" id="selected-title-${id}">${title}</h6>
                <small id="selected-area-type-${id}">${description}</small>
            </div>
            <div class="d-flex justify-content-between">
              <p class="mb-1" id="addr-${id}">${addr}</p>
              <button class="btn btn-outline-danger spot-remove" id="spot-remove-btn-${id}" onclick="removeAndPaint(event)"><i class="bi bi-trash" id="trash-icon-${id}"></i></button>
            </div>
            <div class="d-none" id="selected-mapy-${id}">${mapy}</div>
            <div class="d-none" id="selected-mapx-${id}">${mapx}</div>
            <div class="d-none selected-content-id" id="selected-content-id-${id}">${contentid}</div>
        `;
  return divElement;
}

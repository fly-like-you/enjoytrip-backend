function dragMove() {
  const $ = (select) => document.querySelectorAll(select);
  const draggables = $(".draggable");
  const containers = $("#selected-places")[0];
  draggables.forEach((el) => {
    el.addEventListener("dragstart", () => {
      el.classList.add("dragging");
    });

    el.addEventListener("dragend", () => {
      el.classList.remove("dragging");
    });
  });

  function getDragAfterElement(container, y) {
    const draggableElements = [...container.querySelectorAll(".draggable:not(.dragging)")];
    return draggableElements.reduce(
      (closest, child) => {
        const box = child.getBoundingClientRect();
        const offset = y - box.top - box.height / 2;
        if (offset < 0 && offset > closest.offset) {
          return { offset: offset, element: child };
        } else {
          return closest;
        }
      },
      { offset: Number.NEGATIVE_INFINITY }
    ).element;
  }

  containers.addEventListener("dragover", (e) => {
    e.preventDefault();
    const afterElement = getDragAfterElement(containers, e.clientY);
    const draggable = document.querySelector(".dragging");

    if (draggable != null) {
      if (afterElement == null) {
        containers.appendChild(draggable);
      } else {
        containers.insertBefore(draggable, afterElement);
      }
    }
  });
  containers.addEventListener("drop", (e) => {
    e.preventDefault();

    refreshPosition();
    paintLine();
  });
}

function refreshPosition() {
  // positions의 위치를 변경
  let dragElem = document.querySelectorAll(".draggable");

  if (dragElem == null) return;
  positions.length = 0;

  for (let i = 0; i < dragElem.length; i++) {
    let id = dragElem[i].querySelector(".selected-content-id").textContent;
    let lat = dragElem[i].querySelector(`#selected-mapy-${id}`).textContent;
    let lng = dragElem[i].querySelector(`#selected-mapx-${id}`).textContent;
    let title = dragElem[i].querySelector(`#selected-title-${id}`).textContent;
    let contentid = dragElem[i].querySelector(`#selected-content-id-${id}`).textContent;

    let position = {
      id: contentid,
      title: title,
      latlng: new kakao.maps.LatLng(lat, lng),
    };
    positions.push(position);
  }
}

function removeAndPaint(event) {
  // 버튼의 부모 노드 (div.spot-item)를 찾음
  const parts = event.target.id.split("-");
  const id = parts.pop(); // 마지막 요소 추출
  const parent = document.querySelector(`#selected-list-item-${id}`);
  // paint 해주기
  parent.remove();
  const spotList = document.querySelector(".draggable");

  console.log("removeAndPaint: ", parent);
  // 선택된 장소 배열에서 해당 ID 제거
  const index = selectedPlace.indexOf(id);
  if (index > -1) {
    selectedPlace.splice(index, 1);
  }

  refreshPosition();
  paintLine();
  removeMarker();
}

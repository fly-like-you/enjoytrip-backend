function removeAndPaint(event) {
  // 버튼의 부모 노드 (div.spot-item)를 찾음
  const parts = event.target.id.split("-");
  const id = parts.pop(); // 마지막 요소 추출
  const parent = document.querySelector(`#selected-list-item-${id}`);
  // paint 해주기
  parent.remove();
  const spotList = document.querySelector(".draggable");

  refreshPosition();
  paintLine();
  removeMarker();
}

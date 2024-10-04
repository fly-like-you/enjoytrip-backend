// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 8, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 키워드로 장소를 검색합니다
ps.keywordSearch("인천", placesSearchCB);

// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    var bounds = new kakao.maps.LatLngBounds();

    for (var i = 0; i < data.length; i++) {
      bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    }

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
  }
}
function panTo(lat, lng) {
  map.panTo(new kakao.maps.LatLng(lat, lng));

  // setLevel 시 애니메이션 효과의 지속시간을 500ms로 설정
  map.setLevel(6, {
    animate: {
      duration: 700,
    },
    anchor: new kakao.maps.LatLng(lat, lng),
  });
}

// + 버튼을 누르면 해당 관광지의 위도 경도 정보를 보내면서 객체에 담는다
function handleButtonClick(e) {
  // 클릭된 버튼의 부모 요소를 찾습니다.
  const parentDiv = e.target.parentNode; // 버튼의 부모 요소는 img와 div가 있는 div이며, 이 div의 부모 요소가 전체 div입니다.
  const id = parentDiv.querySelector(".content-id").textContent;
  const lat = parentDiv.querySelector(`#mapy-${id}`).textContent;
  const lng = parentDiv.querySelector(`#mapx-${id}`).textContent;
  // 부모 요소 내에서 mapy와 mapx 값을 포함하는 div를 찾습니다.
  let markerInfo = {
    id: parentDiv.querySelector(`#content-id-${id}`).textContent,
    title: parentDiv.querySelector(`#title-${id}`).textContent,
    latlng: new kakao.maps.LatLng(lat, lng),
  };
  positions.push(markerInfo);
  displayMarker(markerInfo);
  paintLine();
  panTo(lat, lng);
}
// 3번 사이드바로 이동한 관광지를 클릭하면 해당 관광지의 지도로 이동한다
// 3번 사이드바의 멤버가 2개 이상인 경우 관광지와 관광지의 사이를 연결하는 선을 그린다.
const markers = [];
function displayMarker(markerInfo) {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  // 마커 이미지의 이미지 크기 입니다
  var imageSize = new kakao.maps.Size(24, 35);

  // 마커 이미지를 생성합니다
  var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

  var marker = new kakao.maps.Marker({
    position: markerInfo.latlng, // 마커를 표시할 위치
    title: markerInfo.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    image: markerImage, // 마커 이미지
  });
  marker.setMap(map);
  // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
  var iwContent = `<div style="padding:5px;">${markerInfo.title}</div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

  // 인포윈도우를 생성합니다
  var infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
  });
  markers.push({ marker: marker, iw: infowindow });

  // 마커에 마우스오버 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseover", function () {
    // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);
  });
}
function removeMarker() {
  if (markers == null) return;
  for (let i = 0; i < markers.length; i++) {
    markers[i]["marker"].setMap(null);
    markers[i]["iw"].close();
  }

  for (let i = 0; i < positions.length; i++) {
    let markerInfo = {
      id: positions[i].id,
      title: positions[i].title,
      latlng: positions[i].latlng,
    };
    displayMarker(markerInfo);
  }
}
function paintLine() {
  const linePath = [];

  if (polylines.length != 0) {
    for (let i = 0; i < polylines.length; i++) {
      polylines[i].setMap(null);
    }
    polylines.length = 0;
  }
  for (let i = 0; i < positions.length; i++) {
    linePath.push(positions[i].latlng);
  }

  // 지도에 표시할 선을 생성합니다
  var polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FFAE00", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });

  // 지도에 선을 표시합니다
  polyline.setMap(map);
  polylines.push(polyline);
}

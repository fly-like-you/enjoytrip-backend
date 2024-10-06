// 선택된 장소들을 서버로 POST 요청 보내기
export function postSelectedPlaces(placeIds) {
    const data = JSON.stringify({
        places: placeIds,
    });

    fetch("http://localhost:8080/enjoy_trip/trip?action=create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: data,
    })
        .then()
        .catch((error) => {
            console.error("에러 발생:", error);
            alert("저장하는 도중 에러가 발생했습니다.");
        });
}

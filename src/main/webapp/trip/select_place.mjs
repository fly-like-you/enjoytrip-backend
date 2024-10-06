// 선택된 장소들을 배열로 변환
export function getSelectedPlaceIds() {
    const selectedPlaces = document.querySelectorAll("#selected-places .list-group-item");
    const placeIds = [];
    selectedPlaces.forEach((place) => {
        const id = place.id.split("-").pop();
        placeIds.push(id);
    });
    console.log("선택된 여행 장소들: " + placeIds);
    return placeIds;
}
const locations = [
  { name: "서울", id: 1 },
  { name: "인천", id: 2 },
  { name: "대전", id: 3 },
  { name: "대구", id: 4 },
  { name: "광주", id: 5 },
  { name: "부산", id: 6 },
  { name: "울산", id: 7 },
  { name: "세종특별자치시", id: 8 },
  { name: "경기도", id: 31 },
  { name: "강원특별자치도", id: 32 },
  { name: "충청북도", id: 33 },
  { name: "충청남도", id: 34 },
  { name: "경상북도", id: 35 },
  { name: "경상남도", id: 36 },
  { name: "전북특별자치도", id: 37 },
  { name: "전라남도", id: 38 },
  { name: "제주도", id: 39 },
];

function showSuggestions() {
  const input = document.getElementById("search").value.toLowerCase();
  const autocompleteList = document.getElementById("autocomplete-list");
  autocompleteList.innerHTML = "";

  if (input) {
    const filteredData = locations.filter((location) =>
      location.name.toLowerCase().includes(input)
    );
    filteredData.forEach((location) => {
      const div = document.createElement("div");
      div.className = "autocomplete-item";
      div.textContent = location.name;
      div.onclick = () => {
        document.getElementById("search").value = location.name;
        autocompleteList.innerHTML = "";
      };
      autocompleteList.appendChild(div);
    });
    autocompleteList.style.display = filteredData.length > 0 ? "block" : "none";
  } else {
    autocompleteList.style.display = "none";
  }
}

function performSearch() {
  const input = document.getElementById("search").value.toLowerCase();
  const matchedLocation = locations.find((location) => location.name.toLowerCase() === input);

  if (matchedLocation) {
    window.location.href = `trip?action=plan&id=${matchedLocation.id}`;
  } else {
    alert("검색어에 맞는 항목이 없습니다.");
  }
}

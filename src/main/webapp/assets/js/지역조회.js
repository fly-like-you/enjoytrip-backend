const key = "7/oUgCUQWoWg4UXKG6J3vl2KkElR6d7aQztc0mUW3BYP5jUG4zeRiw1jqVT0yAG3NqdFDJJQfOizIlfVmoQh6Q=="; // 세미콜론 추가
const os = "WIN"; // 세미콜론 추가
const app = "EnjoyTrip"; // 세미콜론 추가

async function getData(code) {
    const areaCode = code; // 세미콜론 추가
    const link = `https://apis.data.go.kr/B551011/KorService1/areaCode1?MobileOS=${os}&MobileApp=${app}&areaCode=${areaCode}&_type=json&serviceKey=${key}`; // 세미콜론 추가

    try {
        const res = await fetch(link); // 세미콜론 추가
        const json = await res.json(); // 세미콜론 추가
        
        console.log(json); // API 응답 확인
        const data = json.response.body.items.item; // 세미콜론 추가
    } catch (error) {
        console.error("Error fetching data:", error); // 세미콜론 추가
    }
}



const key =
  "7/oUgCUQWoWg4UXKG6J3vl2KkElR6d7aQztc0mUW3BYP5jUG4zeRiw1jqVT0yAG3NqdFDJJQfOizIlfVmoQh6Q==";
const os = "WIN";
const app = "EnjoyTrip";

async function getData(code) {
  const areaCode = code;
  const link = `https://apis.data.go.kr/B551011/KorService1/areaCode1?MobileOS=${os}&MobileApp=${app}&areaCode=${areaCode}&_type=json&serviceKey=${key}`;

  try {
    const res = await fetch(link);
    const json = await res.json();
    const data = json.response.body.items.item;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

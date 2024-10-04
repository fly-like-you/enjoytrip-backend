function mvLogin() {
  if (id != "ssafy@ssafy.com" || password != "1234") {
    alert("로그인 성공!!");
  }
}

function login() {
  document.querySelectorAll(".before-login,.after-login").forEach((e) => {
    e.classList.toggle("hidden");
  });
}

function logout() {
  document.querySelectorAll(".before-login,.after-login").forEach((e) => {
    e.classList.toggle("hidden");
  });
}

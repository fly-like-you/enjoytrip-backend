<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>여행 일정 타임라인</title>
    <style>
      body {
        font-family: "Arial", sans-serif;
        line-height: 1.6;
        margin: 0;
        padding: 20px;
        background-color: #f4f4f4;
      }
      .container {
        max-width: 1000px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }
      h1 {
        text-align: center;
        color: #333;
      }
      .timeline {
        position: relative;
        padding: 20px 0;
      }
      .timeline::after {
        content: "";
        position: absolute;
        width: 6px;
        background-color: #ff9f55;
        top: 0;
        bottom: 0;
        left: 50%;
        margin-left: -3px;
      }
      .timeline-item {
        padding: 10px 40px;
        position: relative;
        background-color: inherit;
        width: calc(50% - 50px); /* Increased space for the circle */
      }
      .timeline-item::after {
        content: "";
        position: absolute;
        width: 25px;
        height: 25px;
        background-color: white;
        border: 4px solid #ff9f55;
        top: 15px;
        border-radius: 50%;
        z-index: 1;
      }
      .left {
        left: 0;
      }
      .right {
        left: 50%;
      }
      .left::after {
        right: 13px; /* Adjusted to sit on the line */
      }
      .right::after {
        left: -16px; /* Adjusted to sit on the line */
      }
      .content {
        padding: 20px;
        background-color: white;
        position: relative;
        border-radius: 6px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }
      .left .content {
        margin-right: 20px; /* Space between content and circle */
      }
      .right .content {
        margin-left: 20px; /* Space between content and circle */
      }
      .content img {
        width: 100%;
        height: auto;
        border-radius: 5px;
        margin-bottom: 15px;
      }
      @media screen and (max-width: 600px) {
        .timeline::after {
          left: 31px;
        }
        .timeline-item {
          width: 100%;
          padding-left: 70px;
          padding-right: 25px;
        }
        .timeline-item::after {
          left: 15px;
        }
        .left::after,
        .right::after {
          left: 15px;
        }
        .right {
          left: 0%;
        }
        .left .content,
        .right .content {
          margin-left: 20px;
          margin-right: 0;
        }
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>타임 라인</h1>
      <div class="timeline">
        <div class="timeline-item left">
          <div class="content">
            <img src="https://via.placeholder.com/400x200.png?text=해운대해수욕장" alt="해운대해수욕장" />
            <h2>해운대해수욕장</h2>
            <p>부산광역시 해운대구 해운대해변로 264 (우동)</p>
            <p>간단메모: 부산의 대표적인 해변으로, 아름다운 백사장과 현대적인 도시 경관이 어우러진 곳입니다.</p>
          </div>
        </div>
        <div class="timeline-item right">
          <div class="content">
            <img src="https://via.placeholder.com/400x200.png?text=해운대원조할매국밥" alt="해운대원조할매국밥" />
            <h2>해운대원조할매국밥</h2>
            <p>부산광역시 해운대구 구남로 32-10</p>
            <p>간단메모: 부산의 유명한 국밥집으로, 든든한 아침 식사나 점심 식사로 좋습니다.</p>
          </div>
        </div>
        <div class="timeline-item left">
          <div class="content">
            <img src="https://via.placeholder.com/400x200.png?text=해운대달맞이길" alt="해운대달맞이길" />
            <h2>해운대달맞이길</h2>
            <p>부산광역시 해운대구 중동 산 180</p>
            <p>간단메모: 아름다운 해안 산책로로, 특히 일출과 일몰 시간에 방문하면 좋습니다.</p>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
>
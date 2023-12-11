# smart_farm_android_project
SmartFarmPlatform응용 프로그램

각 작물값의 데이터 확인 및 원격이 가능한 웹 어플리케이션

어플리케이션의 모습


![image](https://github.com/jmjmff/IoTCloudPlatform-/assets/127866363/cd277d02-a9f0-42b1-8077-a391a4e7aa76)

사물조회
![image](https://github.com/jmjmff/IoTCloudPlatform-/assets/127866363/7a9bab0a-517e-42c0-bd17-9d5fc00fbc3c)

최신 상태 조회
![image](https://github.com/jmjmff/IoTCloudPlatform-/assets/127866363/b58ce81f-4145-4e6f-ba72-4ac9362a6c27)

로그 조회
![image](https://github.com/jmjmff/IoTCloudPlatform-/assets/127866363/8a43eeec-66fa-4ac2-b168-e7735930ad64)

1.데이터 조회 아두이노로부터 받은 데이터(온도, 습도, 빛, 토양습도)를 바로 밑의 각 칸에 표시한다.

2.로그 조회 로그 조회 페이지로 이동


사용법 순서
1. AWS_IoT.ino 아두이노 파일과 Lambda함수, AWS를 이용해 API를 구축한다.

2. 구축된 API 링크중 상태 조회 링크는 app.js의 API_URL에, 로그 조회 API링크는 log.js의 API_URL에 넣는다.

3.SmartFarmApplication 폴더의 index.html 파일 실행

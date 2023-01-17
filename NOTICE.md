admin page 접속 URL : http://localhost:9090/admin/getAdNoticeList // 유효성 체크로 접속막힘

관리자 테스트 계정 : ID : admin / pw : 1111

2023-01-11 업데이트 내용
1. userDTO 에 private String userRole; 추가했습니다.
2. userRole 이용하여 관리자권한이 있는 이용자가 로그인 해야만 등록/수정/삭제 가능하도록 수정
3. 1:1문의 답변 / 신고관리 / 회원관리 미구현

2023-01-17 업데이트 내용
1. header admin 권한 유저 로그인시 ADMIN PAGE 나타나도록 수정
2. style.css .v9_14 width: 300px -> width: 400px 으로 수정
3. 비회원 / 비 관리자 회원 ADMIN PAGE 접속시 로그인 요청 / 관리자 계정 접속 안내 창 띄우며 로그인 화면 이동 구현
4. 마이페이지 공지사항 링크를 고객센터쪽으로 수정해야 할거 같네요
5. templates.admin 에서 FAQ 관련 HTML 삭제했습니다.
6. templates.admin => updateAdUser.html 추가되었습니다.
7. /dorandoran/src/main/resources/static/css => getAdUserList, insertAdQnA(이름변경), insertAdReport, updateAdUser 추가되었습니다.
8. user inputDtm 부분은 데이터 타입때문에 에러나서 일단은 막아놨습니다. 회원가입 구현되면 적용해볼게요
9. 남은 작업은 로그인시 유저 롤 확인하여 허용/제한, 검색기능 입니다.
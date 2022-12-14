<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member"%>
	// 로그인한 사용자의 정보를 sesion에 담았기 때문에 // 이 웹 애플리케이션의 어디서든지 해당 키 값을 제시해서 로그인한 사용자의 정보를 가져올 수 있음 
<% Member loginUser = (Member) session.getAttribute("loginUser"); %> 
// System.out.println(loginUser); 
// 로그인 전 menubar.jsp 로딩 시 : null 
// 로그인 후 menubar.jsp 로딩 시 : 로그인한 회원의 정보가 담긴 Member 객체 
<% 
String contextPath = request.getContextPath(); // 성공 시 알람 문구 또한 session에 담았기 때문에 // session으로부터 뽑기 
String alertMsg = (String) session.getAttribute("alertMsg");

// 쿠키 불러오기
// -> request.getCookies() 메소드 -> Cookie 타입의 배열로 리턴
Cookie[] cookies = request.getCookies();
String saveId = "";
// 배열에 담긴 여러 개의 쿠키 세트들 중에 내가 원하는 쿠키만 골라내는 작업 진행
if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		// System.out.println(i + " : " + cookies[i].getName() + " / " + cookies[i].getValue());
		// 서버는 기본적으로 JSESSIONID 라는 쿠키를 만들어준다.
		// 쿠키로부터 name(키값)을 뽑아내려면 getName(), value를 뽑아내려면 getValue() 메소드를 이용

		if (cookies[i].getName().equals("saveId")) {
	saveId = cookies[i].getValue();
	break;
		}
	}
}
// 이 시점 기준으로 "saveId"라는 키값을 가진 쿠키가 있었다면
// String 타입의 saveId 라는 변수에 해당 아이디 값 자체가 담겨있을 것
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
</script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
#login-form, #user-info {
	float: right;
}

#user-info a {
	text-decoration: none;
	color: black;
	font-size: 12px;
}

.nav-area {
	background-color: black;
}

.menu {
	display: table-cell; /* 인라인 요소처럼 배치 가능 */
	height: 50px;
	width: 150px;
}

.menu a {
	text-decoration: none;
	color: white;
	font-size: 20px;
	font-weight: bold;
	display: block;
	width: 100%;
	height: 100%;
	line-height: 50px;
}

.menu a:hover {
	background-color: darkgrey;
}
</style>
</head>
<body>

	<script>
		// script 태그 내에도 스크립틀릿과 같은 jsp 요소를 쓸 수 있다.

		let msg = '<%=alertMsg%>';
		// 성공적으로 로그인이 되었습니다 / "null"

		if (msg !== 'null') {
			alert(msg);
			// 알림창을 띄워준 후에 session에 담긴 값을 지워줘야 한다
	<%session.removeAttribute("alertMsg");%>
		}
	</script>

	<h1 align="center">Welcome D Class</h1>

	<div class="login-area">

		<%
			if (loginUser == null) {
		%>
		<!-- 로그인 전에 보여지는 로그인 form -->
		<!-- 로그인 버튼을 클릭했을 때 이동하고자 하는 위치 : http://localhost:8888/jsp/login.me 
		            
		            	절대경로 : /JSP/login.me
		            	상대경로 : login.me
		            -->
		<form id="login-form" action="<%=contextPath%>/login.me" method="post">
			<table>
				<tr>
					<th>아이디 :</th>
					<td><input type="text" name="userId" id="" required /></td>
				</tr>
				<tr>
					<th>비밀번호 :</th>
					<td><input type="password" name="userPwd" id="" required /></td>
				</tr>
				<tr align="right">
					<th colspan="2"><input type="checkbox" id="saveId"
						name="saveId" value="y"> <label for="saveId">아이디
							저장</label></th>
				</tr>
				<tr>
					<th colspan="2">
						<button type="submit">로그인</button>
						<button type="button" onclick="enrollPage();">회원가입</button>
					</th>
				</tr>
			</table>
		</form>

		<script>

		$(() => {
			let saveId = "<%=saveId%>"; // "user01" or ''
			
			if(saveId != ''){
				$('#login-form input[name=userId]').val(saveId);
				$('#saveId').attr('checked',true);
			}
		})
		
		function enrollPage() {

		// 단순한 페이지 요청이라고 하더라도 반드시 Servlet을 거쳐갈 것
		location.href = "<%=contextPath%>/enrollForm.me";
		}
		
		</script>
		<%
			} else {
		%>
		<!-- 로그인 성공 후에 보여지는 프로필 화면 -->

		<div id="user-info">
			<b> <%=loginUser.getUserName()%>님
			</b> 환영합니다~ <br /> <br />

			<div align="center">

				<a href="<%=contextPath%>/myPage.me">마이페이지</a> <a
					href="<%=contextPath%>/logout.me">로그아웃</a>
			</div>
		</div>
		<%
			}
		%>
	</div>

	<br clear="both" />
	<!-- float 속성 해제-->
	<br />
	<div class="nav-area" align="center">
		<div class="menu">
			<a href="<%=contextPath%>">HOME</a>
		</div>
		<div class="menu">
			<a href="<%=contextPath%>/list.no">공지사항</a>
		</div>
		<div class="menu">
			<a href="<%=contextPath%>/list.bo?currentPage=1">일반게시판</a>
		</div>
		<div class="menu">
			<a href="<%=contextPath%>/list.th">사진게시판</a>
		</div>
	</div>
</body>

</html>
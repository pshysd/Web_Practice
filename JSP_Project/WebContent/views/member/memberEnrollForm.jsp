<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.outer {
	background-color: black;
	color: white;
	width: 1000px;
	margin: auto;
	margin-top: 50px;
}

#enroll-form table {
	margin: auto;
}

#enroll-form input {
	margin: 5px;
}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>
	<!-- ../ : 현재 폴더로부터 한겹 빠져나가겠다. -->

	<div class="outer">

		<br>
		<h2 align="center">회원가입</h2>

		<!-- 
			현재 나의 주소 : http://localhost:8888/jsp/enrollForm.me
			내가 요청을 보내고자 하는 주소 : http://localhost:8888/jsp/insert.me
			
			절대경로 : /jsp/insert.me
			상대경로 : insert.me
		-->
		<form id="enroll-form" action="<%=contextPath%>/insert.me"
			method="post">

			<!-- 아이디, 비밀번호, (비밀번호확인), 전화번호, 이메일, 주소, 취미 -->
			<table>
				<!-- (tr>td*3)*8 + Enter -->
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="userId" maxlength="12" required></td>
					<td><button type="button" onclick="idCheck();">중복확인</button></td>
					<!-- 중복확인 나중에 AJAX 라는 기술을 배우고 할 것 -->
				</tr>

				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="userPwd" maxlength="15"
						required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" maxlength="15" required></td>
					<!-- 서버로 넘기지는 않을것이기 때문에 name 속성은 생략 -->
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="6" required></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" placeholder="- 포함해서 입력"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;관심분야</td>
					<td colspan="2">
						<!-- (input[type=checkbox name=interest id= value=]+label)*6 + Enter -->
						<input type="checkbox" name="interest" id="sports" value="운동">
						<label for="sports">운동</label> <input type="checkbox"
						name="interest" id="hiking" value="등산"> <label
						for="hiking">등산</label> <input type="checkbox" name="interest"
						id="fishing" value="낚시"> <label for="fishing">낚시</label> <br>
						<input type="checkbox" name="interest" id="cooking" value="요리">
						<label for="cooking">요리</label> <input type="checkbox"
						name="interest" id="game" value="게임"> <label for="game">게임</label>
						<input type="checkbox" name="interest" id="movie" value="영화">
						<label for="movie">영화</label>
					</td>
				</tr>
			</table>

			<br>
			<br>

			<div align="center">
				<button type="submit">회원가입</button>
				<button type="reset">초기화</button>
			</div>

			<br>
			<br>
		</form>
	</div>
	<script>
                    	function idCheck() {

                            // 아이디를 입력하는 input 요소 객체
                            const userId = document.querySelector('#enroll-form input[name=userId]');
                            const userPwd = document.querySelector('#enroll-form input[name=userPwd]');
                            // name 속성이 userId인 요소가 menubar.jsp에도 있기 때문에
                            // 확실하게 어디에 속해있는 요소인지 잘 적어줘야 함

                    		$.ajax({
                    			url : 'idCheck.me',
                                data : {checkId : $(userId).val()},
                                type : 'get',
                                success : (resp) => {
                                    // resp의 값은 "NNNNN" 또는 "NNNNY"
                                    if(resp === "NNNNN"){ // 사용 불가
                                        alert('이미 존재하거나 탈퇴한 회원의 아이디입니다.');
                                        $(userId).focus(); // 포커스 맞춰서 재입력 유도
                                    }else{
                                        if(confirm('사용 가능한 아이디입니다. 사용하시겠습니까?')){
                                            // 아이디 값 확정 -> 다시 수정 못하게 readonly 속성 추가
                                            $(userId).attr('readonly', true);
                                            $(userPwd).focus();
                                        }else{ // 사용하지 않겠다

                                            // 재입력 유도
                                            $(userId).val('');
                                            $(userId).focus();
                                        }    
                                    }
                                },
                                error : () => {
                                    console.log('아이디 중복 체크용 AJAX 통신 실패 ...');
                                }
                    		})
                    	}
                    </script>
</body>
</html> 
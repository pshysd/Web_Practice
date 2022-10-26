<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.notice.model.vo.Notice"%>
<%
	// 해당 공지사항 게시글 뽑기
Notice n = (Notice) request.getAttribute("n");
%>
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
	height: 500px;
	margin: auto;
	margin-top: 50px;
}

#update-form>table {
	border: 1px solid white;
}

#update-form input, #update-form textarea {
	width: 100%;
	box-sizing: border-box;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">공지사항 수정하기</h2>
		<br>

		<!-- 
			현재 나의 위치 : http://localhost:8888/jsp/updateForm.no?nno=x
			타겟 URL : http://localhost:8888/jsp/update.no
			
			절대경로 : /jsp/update.no
			상대경로 : update.no
		 -->
		<form action="<%=contextPath%>/update.no" id="update-form"
			method="post">
			<input type="hidden" name="nno" value="<%=n.getNoticeNo()%>">
			<table align="center">
				<tr>
					<th width="50">제목</th>
					<td width="350"><input type="text" name="title"
						value="<%=n.getNoticeTitle()%>" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="content" rows="10"
							style="resize: none;" required><%=n.getNoticeContent()%></textarea></td>
				</tr>
			</table>
			<br> <br>

			<div align="center">
				<button type="submit">수정하기</button>
				<button type="button" onclick="history.back();">뒤로가기</button>
				<!-- history.back() : 이전 페이지로 돌악가게 해주는 함수 -->
			</div>
		</form>
	</div>
</body>
</html>
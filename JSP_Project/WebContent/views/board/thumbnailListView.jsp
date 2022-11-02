<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.Board, java.util.ArrayList"%>
<%
	ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
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
	height: 1000px;
	margin: auto;
	margin-top: 50px;
}

.list-area {
	width: 760px;
	margin: auto;
}

.thumbnail {
	border: 1px solid white;
	width: 220px;
	display: inline-block;
	margin: 14px;
}

.thumbnail:hover {
	cursor: pointer;
	opacity: 0.7;
}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">사진게시판</h2>
		<br>

		<%
			if (loginUser != null) {
		%>
		<div style="width: 850px" align="right">
			<!-- 로그인한 회원만 보이는 버튼 -->
			<a href="enrollForm.th" class="btn btn-secondary">글작성</a>
		</div>
		<%
			}
		%>
		<br> <br>

		<div class="list-area">
			<!-- 
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>
			<div class="thumbnail" align="center">
				<img src="" width="200px" height="150px">
				<p>
					No.123 제목입니다. <br> 조회수 : 230
				</p>
			</div>-->
			
			<%
				if (!list.isEmpty()) {
			%>
			<%
				for (Board b : list) {
			%>
			<div class="thumbnail" align="center">
			<input type="hidden" value="<%=b.getBoardNo() %>">
				<img src="<%=contextPath%>/<%=b.getTitleImg()%>" width="200px"
					height="150px">
				<p>
					No.<%=b.getBoardNo()%>
					<%=b.getBoardTitle()%><br> 조회수 :
					<%=b.getCount()%>
				</p>
			</div>
			<%
				}
			%>
			<%
				} else {
			%>
			조회된 게시글이 없습니다.
			<%
				}
			%>
		</div>
		
		<script>
			$(() => {
				$('.thumbnail').click((event) => {
					location.href="<%=contextPath%>/detail.th?bno=" + $(event.currentTarget).children().eq(0).val();
				})
			});
		</script>
</body>
</html>
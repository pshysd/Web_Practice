<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.kh.common.model.vo.PageInfo, java.util.ArrayList, com.kh.board.model.vo.Board"%>
<%
	PageInfo pi = (PageInfo) request.getAttribute("pi"); // 페이징 바 만들기
ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list"); // 조회된 내용을 출력

//	자주 쓰는 필드 값 미리 빼두기
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
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
	height: 550px;
	margin: auto;
	margin-top: 50px;
}

.list-area {
	border: 1px solid white;
	text-align: center;
}

.list-area>tbody>tr  :hover {
	background-color: gray;
	cursor: pointer;
}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">일반 게시판</h2>
		<br>

		<!-- 로그인한 회원만 보여지는 글 작성버튼 -->
		<% if(loginUser != null) {%>
		<div style="width: 850px;" align="right">
			<a href="<%=contextPath%>/enrollForm.bo" class="btn btn-secondary">글 작성</a>
		</div>
		<%} %>
		<br>
		<!-- 조회한 게시물들이 보여질 자리 -->
		<table align="center" class="list-area">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (list.isEmpty()) {
				%>
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
				<%
					} else {
				%>
				<%
					for (Board b : list) {
				%>
				<tr>
					<td><%=b.getBoardNo()%></td>
					<td><%=b.getCategory()%></td>
					<td><%=b.getBoardTitle()%></td>
					<td><%=b.getBoardWriter()%></td>
					<td><%=b.getCount()%></td>
					<td><%=b.getCreateDate()%></td>
				</tr>
				<%
					}
				%>
				<%
					}
				%>
				<!--
				<tr>
					<td>10</td>
					<td>게임</td>
					<td>게시글 제목입니다.</td>
					<td>user02</td>
					<td>78</td>
					<td>2022-05-01</td>
				</tr>
				  -->
			</tbody>
		</table>
		
		<script>
			$(() => {
				$('.list-area>tbody>tr').click((event) => {
					location.href = "<%=contextPath%>/detail.bo?bno="+$(event.currentTarget).children().eq(0).text()
				});
			})
		</script>
		<br> <br>

		<!-- 페이징 바 만들기 -->
		<div align="center" class="paging-area">
			<!-- 
			<button>1</button>
			<button>2</button>
			<button>3</button>
			<button>4</button>
			<button>5</button>
			<button>6</button>
			<button>7</button>
			<button>8</button>
			<button>9</button>
			<button>10</button>
			 -->
			<%
				if (currentPage != 1) {
			%>
			<button
				onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage - 1%>'">&lt;</button>
			<%
				}
			%>
			<%
				for (int p = startPage; p <= endPage; p++) {
			%>
			<%
				if (p != currentPage) {
			%>
			<button
				onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=p%>'"><%=p%></button>
			<%
				} else {
			%>
			<button disabled><%=p%></button>
			<%
				}
			%>
			<%
				}
			%>
			<%
				if (currentPage != maxPage) {
			%>
			<button
				onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage + 1%>'">&gt;</button>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>
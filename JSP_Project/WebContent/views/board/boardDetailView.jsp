<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.kh.board.model.vo.Board, com.kh.board.model.vo.Attachment"%>
<%
	// 필요한 데이터 뽑기
Board b = (Board) request.getAttribute("b");
// 게시글 번호, 카테고리명, 글제목, 글내용, 작성자아이디, 작성일

Attachment at = (Attachment) request.getAttribute("at");
// 파일번호, 원본명, 수정명, 저장경로
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
	height: 700px;
	margin: auto;
	margin-top: 50px;
}

.outer table {
	border-clolor: white;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="outer">
		<br>
		<h2 align="center">일반 게시판 상세보기</h2>
		<br>

		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="70">카테고리</th>
				<td width="70"><%=b.getCategory()%></td>
				<th width="70">제목</th>
				<td width="350"><%=b.getBoardTitle()%></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter()%></td>
				<th>작성일</th>
				<td><%=b.getCreateDate()%></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><p style="height: 200px;">
						<%=b.getBoardContent()%></p></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<%
						if (at == null) {
					%> 첨부파일이 없습니다. <!-- 첨부파일이 없는 경우 : 첨부 파일이 없습니다. --> <!-- 첨부파일이 있는 경우 : 첨부파일을 다운로드 받을 수 있게끔 링크 걸기 (첨부파일의 원본명) -->
					<%
						} else {
					%> <a download="<%=at.getOriginName() %>" href="<%=contextPath%>/<%=at.getFilePath() + at.getChangeName()%>"><%=at.getOriginName()%></a>
					<%
						}
					%>
				</td>
			</tr>
		</table>

		<br>

		<div align="center">
			<a href="<%= contextPath %>/list.bo?currentPage=1" class="btn btn-secondary btn-sm">목록가기</a>

			<% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())) {%>
			<!-- 로그인한 사용자가 게시글 작성자일 경우에만 보여지게끔 -->
			<a href="<%=contextPath %>/updateForm.bo?bno=<%=b.getBoardNo() %>" class="btn btn-warning btn-sm">수정하기</a> 
			<a href="" class="btn btn-danger btn-sm">삭제하기</a>
			<%} %>
		</div>

		<br>

		<!-- 댓글 들어갈 자리 (일단 화면 구현만) -->
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<!-- 로그인이 되어있을 경우 -->
					<!--<tr>
                    <th>댓글 작성</th>
                    <td><textarea name="" id="" cols="50" rows="3" style="resize:none;"></textarea></td>
                    <td><button>댓글 등록</button></td>
                </tr> -->
					<!-- 로그인이 되어있지 않은 경우-->
					<tr>
						<th>댓글 작성</th>
						<td><textarea name="" id="" cols="50" rows="3"
								style="resize: none;" readonly>
                                로그인 후 이용 가능한 서비스 입니다.
                            </textarea></td>
						<td>
							<button disabled>댓글 등록</button>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>admin</td>
						<td>댓글 내용이 들어갈 자리</td>
						<td>2022-10-21</td>
					</tr>
					<tr>
						<td>admin</td>
						<td>댓글 내용이 들어갈 자리</td>
						<td>2022-10-21</td>
					</tr>
					0
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
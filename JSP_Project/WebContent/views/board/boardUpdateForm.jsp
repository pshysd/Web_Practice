<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.*, java.util.ArrayList"%>
<%
	// 필요한 데이터 뽑기
ArrayList<Category> list = (ArrayList<Category>) request.getAttribute("list");

Board b = (Board) request.getAttribute("b");

Attachment at = (Attachment) request.getAttribute("at");
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

#update-form>table {
	border: 1px solid white;
}

#update-form>table input, #update-form>table textarea {
	width: 100%;
	box-sizing: border-box;
}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>
	<div class="outer">

		<br>
		<h2 align="center">일반게시판 수정하기</h2>
		<br>

		<form id="update-form" action="<%=contextPath%>/update.bo" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
			<table align="center">
				<tr>
					<!--
						 만약 카테고리가 추가되거나, 삭제되거나, 수정가능하다면
						 DB로부터 카테고리를 조회해서 보여주게끔 해야한다.
					 -->
					<th width="100">카테고리</th>
					<td width="500"><select name="category" id="">

							<%
								for (Category c : list) {
							%>
							<option value="<%=c.getCategoryNo()%>"><%=c.getCategoryName()%></option>
							<%
								}
							%>

					</select> <script>
					$(function() {
						$('#update-form option').each(function(){
							if($(this).text() == "<%=b.getCategory()%>"){
								$(this).attr('selected', true);
							}	
							})
						});
						
					</script></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" required
						value="<%=b.getBoardTitle()%>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" rows="10" required
							style="resize: none;"><%=b.getBoardContent()%></textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<%
							if (at != null) {
						%> <!-- 기존의 파일이 있다면 원본명을 보여주자 --> <%=at.getOriginName()%> <input
						type="hidden" name="originFileNo" value="<%=at.getFileNo()%>">
						<input type="hidden" name="originFileName"
						value="<%=at.getChangeName()%>"> <%
 	}
 %> <input type="file" name="reUpfile">

					</td>
				</tr>
			</table>

			<br>

			<div align="center">
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>
</body>
</html>
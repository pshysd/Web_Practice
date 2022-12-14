<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/menubar.jsp"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice"%>
<%
	// request에 담았던 list의 값을 뽑아오기 (==공지사항 전체 리스트) 
ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
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

.list-area {
	border: 1px solid white;
	text-align: center;
}

.list-area>tbody>tr :hover {
	background-color: grey;
	cursor: pointer;
}
</style>
</head>

<body>

	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<br>

		<!-- 공지사항은 관리자만 작성 가능하므로 -->
		<%
			if (loginUser != null && loginUser.getUserId().equals("admin")) {
		%>
		<div style="width: 850px;" align="right">
			<a href="<%=contextPath %>/enrollForm.no" class="btn btn-secondary">글작성</a>
		</div>
		<%
			}
		%>
		<table class="list-area" align="center">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="400">글제목</th>
					<th width="100">작성자</th>
					<th>조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>

			<tbody>
				<!-- 보통 작성일 기준 내림차순, 최신글이 가장 위에 오도록 구현 -->

				<!-- 
				<tr>
					<td>3</td>
					<td>test title</td>
					<td>admin</td>
					<td>0</td>
					<td>2021-05-07</td>
				</tr>
				<tr>
					<td>2</td>
					<td>test ing</td>
					<td>admin</td>
					<td>70</td>
					<td>2021-04-27</td>
				</tr>
				<tr>
					<td>1</td>
					<td>공지사항 서비스를 시작합니다.</td>
					<td>admin</td>
					<td>320</td>
					<td>2021-02-08</td>
				</tr>
				 -->

				<%
					if (list.isEmpty()) {
				%>
				<tr>
					<td colspan="5">존재하는 공지사항이 없습니다.</td>
				</tr>
				<%
					} else {
				%>
				<!-- 리스트가 비어있지 않을 경우 : 조회된 공지사항이 적어도 한 건이라도 있을 경우 -->
				<%
					for (Notice n : list) {
				%>
				<tr>
					<td><%=n.getNoticeNo()%></td>
					<td><%=n.getNoticeTitle()%></td>
					<td><%=n.getNoticeWriter()%></td>
					<td><%=n.getCount()%></td>
					<td><%=n.getCreateDate()%></td>
				</tr>
				<%
					}
				%>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

    <script>
        $(() => {
            $('.list-area>tbody>tr').click((event) => {
                // console.log("클릭됨");

                // 클릭했을 때 공지사항 상세보기 페이지를 요청
                // 단, 해당 공지사항의 게시글 번호를 넘겨야지만 그 게시글만 조회 가능 (pk)
                // => 해당 클릭된 tr 요소의 자손들 중에서 첫번째 td 영역의 내용
                const nno = $(event.currentTarget).children().eq(0).text();
                console.log(nno);

                // 게시글 번호를 넘기면서 url 요청
                // 요청할url주소?쿼리스트링 => GET방식
                // 요청할url주소?키=밸류&키=밸류&...
                // ? 뒤의 내용물 === 쿼리스트링, 직접 만들어서 넘길 예정...
                // /jsp/detail.no?nno=클릭했을때의 글번호
                location.href = "<%=contextPath%>/detail.no?nno=" +nno;
            });
        })
    </script>
</body>

</html>
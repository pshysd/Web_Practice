<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer {
        background-color: black;
        color:white;
        width: 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }

    #detail-area {
        border:1px solid white;
    }
</style>
</head>
<body>

    <div class="outer">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>

        <table id="detail-area" align="center" border="1">
            <tr>
                <th width="70">제목</th>
                <td width="350" colspan="3">공지사항 제목입니다.</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>admin</td>
                <th>작성일</th>
                <td>2022-10-18</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height:150px">
                    </p>
                </td>
            </tr>
        </table>
        <br><br>

        <div>
            <a href="<%=contextPath %>/list.no" class="btn btn-secondary btn-sm">목록가기</a>
            
            <!-- 현재 로그인한 사용자가 해당 글을 작성한 경우에만 보여지게끔 -->
            <a href="<%=contextPath %>/" class="btn btn-warning btn-sm">수정하기</a>
            <a href="<%=contextPath %>/" class="btn btn-danger btn-sm" >삭제하기</a>
        </div>
    </div>
</body>
</html>
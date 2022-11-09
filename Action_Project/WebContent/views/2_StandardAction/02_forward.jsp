<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1>여기는 02_forward.jsp 페이지야</h1>
    <!-- 이 페이지의 url : http://localhost:8888/action/views/2_StandardAction/02_forward.jsp-->

    <jsp:forward page="footer.jsp" />
    <!-- forward의 특징 상 url은 그대로고 화면만 바꿔치기됨-->

    <!--request.getRequestDispatcher().forward(request.response) 와 마찬가지-->
</body>
</html>
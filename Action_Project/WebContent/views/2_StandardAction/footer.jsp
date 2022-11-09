<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String year = new SimpleDateFormat("yyyy").format(new Date());
	%>
		Copyright © 1998-${year} KH Information Educational Institute All Right Reserved
		<!-- 매년 해가 바뀔 때 마다 직접 연도를 바꾸는 것은 너무 번거롭다-->

        <br>

        test : ${param.test}
</body>
</html>
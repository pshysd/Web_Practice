<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h3>include</h3>

    <p>
        include 지시어
    </p>

    <p>
        또 다른 페이지를 포함하고자 할 때 쓰이는 태그
    </p>

    <h4>1. 기존의 include 지시어를 이용한 방식 (정적 include 방식)</h4>

	<%--
    <%@ include file="footer.jsp"%>

    <br><br>

    특징 : include 하고 있는 페이지 상에 선언되어있는 변수를 현재 이 페이지에서도 사용 가능함<br>
    include한 페이지의 year 변수 값 : <%= year %> <br><br>

    단, 현재 이 페이지에서 동일한 이름의 변수를 선언할 수 없음 <br>
	
	
    <%
        String year = "2022";
    %>
    --%>
    
    <hr>

    <h4>2. JSP 표준 액션 태그를 이용한 방식 (동적 include 방식)</h4>

    <!--
        반드시 시작 태그와 종료 태그를 같이 써야만 한다!! (XML 기술을 이용했으므로)
        단, 시작 태그와 종료 태그 사이에 넣을 값이 따로 없다면 다음과 같이 표현한다.
    -->

    <jsp:include page="footer.jsp" />
    <br><br>

    특징 1. include 하고 있는 페이지에 선언된 변수를 공유하지 않는다. (즉, 동일한 이름의 변수가 선언 가능하다.) <br>
    <%
        String year = "2022";
    %>

    특징 2. 페이지 포함 시 include하는 페이지로 값을 전달할 수 있음 <br><br>

    <jsp:include page="footer.jsp">
        <jsp:param name="test" value="HELLO" />
    </jsp:include>

    <br>

    <jsp:include page="footer.jsp">
        <jsp:param name="test" value="GOODBYE!" />
    </jsp:include>

</body>
</html>
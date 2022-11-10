<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1>1. FormatNumber</h1>

    <p>
        숫자 데이터의 포맷(형식) 지정 <br>
        - 표현하고자 하는 숫자 데이터의 형식을 통화기호, % 등의 원하는 쓰임에 맞게 지정하는 태그 <br><br>

        &lt;fmt:formatNumber value="출력할값" groupingUsed="true/false" type="percent/currency" currencySymbol="$" /&gt;

        => groupingUsed, type, currencySymbol 속성은 생략 가능 <br>
        => groupingUsed : 숫자 단위의 구분자 (,) 표시 여부를 지정하는 속성 (생략 가능, 생략시 기본값 true)<br>
        => type : "percent"(백분율 단위) / "currency"(통화 단위 => 로컬 컴퓨터 기준이 기본값)<br>
        => currencySymbol : type 속성이 "currency"일 경우 통화기호 문자의 종류를 지정 <br>
    </p>

    <!-- 포맷팅 테스트를 위한 변수 먼저 세팅 -->

    <c:set var="num1" value="123456789" />
    <c:set var="num2" value="0.75"/>
    <c:set var="num3" value="50000" />

    그냥 출럭 : ${num1} <br>
    formatNumber 태그를 이용해서 출력 : <fmt:formatNumber value="${num1}"/> <br>
    숫자 그대로 출력 : <fmt:formatNumber value="${num1}" groupingUsed="false" /> <br><br>

    <!--
        3자리마다 ,가 찍혀있음
        - groupingUsed 기본값 : true (생략 시 기본값) -> 3자리마다 ,가 찍혀있음
        즉, groupingUsed는 숫자 단위의 구분자(,) 표시 여부 지정
    -->

    그냥 출력 : ${num2} <br>
    percent : <fmt:formatNumber value="${num2}" type="percent" /> <br><br>
    <!--
        type="percent" : 소숫점을 백분율로 변경해서 보여줌
    -->

    그냥 출력 : ${num3} <br>
    currency: <fmt:formatNumber value="${num3}" type="currency" /> <br><br>
    <!--
        type="currency": 통화(돈)의 단위로 보여짐
                            현재 내가 쓰고있는 컴퓨터의 로컬 정보에 따라서 통화 단위가 지정됨
        (groupingUsed 속성의 기본값이 true이기 때문에 3자리마다 ,도 찍혀있음)
    -->

    currency $ : <fmt:formatNumber value="${num3}" type="currency" currencySymbol="$" />

    <!--
        currenycySymbol : 통화기호 문자의 종류를 지정
    -->

    <hr>

    <h3>2. formatDate</h3>

    <p>
        날짜 및 시간 데이터의 포맷(형식) 지정 <br>
        - 단, java.util.Date 객체를 사용해야 한다
    </p>

    <!-- 테스트를 위한 날짜 변수 세팅-->
    <c:set var="current" value="<%= new java.util.Date()%>" />

    그냥 출력 : ${current} <br>
    <ul>
        <li>
            현재 날짜 : <fmt:formatDate value="${current}"/>
            <!-- type 생략 가능, 생략시 기본값 "date" -->
        </li>
    </ul>

</body>
</html>
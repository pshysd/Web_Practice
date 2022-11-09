<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>EL 연산</h2>
	
	<h3>1. 산술 연산</h3>
	
	<p>
		* 기존 방식 <br>
		10 + 3 = <%= (int)request.getAttribute("big") + (int)request.getAttribute("small") %>
	</p>

	<p>
		* EL 연산 <br>
		10 + 3 = ${big + small} <br>
		10 - 3 = ${big - small} <br>
		10 * 3 = ${big * small} <br>
		10 / 3 = ${big / small}  또는 ${big div small} <br>
		10 % 3 = ${big % small} 또는 ${big mod small}
	</p>

	<hr>

	<h3>2. 숫자간 대소비교 연산</h3>
	
	<p>
		* 기존 방식 <br>
		10 > 3 : <%=(int)request.getAttribute("big") > (int)request.getAttribute("small")%>
	</p>

	<p>
		* EL 연산 <br>
		10 > 3 : ${big > small} 또는 ${big gt small} <br>
		10 < 3 : ${big < small} 또는${big lt small} <br>
		10 >= 3 : ${big >= small} 또는 ${big ge small} <br>
		10 <= 3 : ${big <= small} 또는 ${big le small}
	</p>

	<hr>

	<h3>3. 동등 비교 연산</h3>
	<p>
		* 기존 방식<br>
		10과 3이 일치합니까? : <%=(int)request.getAttribute("big") == (int)request.getAttribute("small")%> <br>
		sOne과 sTwo가 일치합니까? (주소값 비교) : <%= request.getAttribute("sOne") == (String)request.getAttribute("sTwo")%> <br>
		sOne과 sTwo가 일치합니까? (내용물 비교) : <%= ((String)request.getAttribute("sOne")).equals((String)request.getAttribute("sTwo")) %> <br>
	</p>
	<p>
		* EL 연산 <br>
		10과 3이 일치합니까? : ${big == small} 또는 ${big eq small} <br>
		big에 담긴 값이 10과 일치합니까? : ${big==10} 또는 ${big eq 10} <br>

		sOne과 sTwo가 일치합니까? : ${sOne == sTwo} 또는 ${sOne eq sTwo} <br>
		<!-- EL에서의 == 비교는 자바에서의 equals 메소드와 같은 동작을 함 (내용물 비교를 수행) -->
		
		sOne과 sTwo가 일치하지 않습니까? : ${sOne != sTwo} 또는 ${sOne ne sTwo} <br>
		<!-- ne : (N)ot (E)quals -->

		sOne에 담긴 값이 "안녕"과 일치합니까? : ${sOne == "안녕"} 또는 ${sOne eq "안녕"} <br>
												또는 ${sOne == '안녕'} 또는 ${sOne eq '안녕'}
		<!-- EL에서의 문자열 리터럴 제시시 홑따옴표든 쌍따옴표든 상관이 없음 -->
	</p>

	<hr>

	<h3>4. 객체가 null인지 또는 리스트가 비어있는지 체크하는 연산</h3>

	<p>
		* EL 연산 <br>
		pTwo가 null 입니까? : ${pTwo == null} 또는 ${pTwo eq null} 또는 ${empty pTwo} <br>
		pOne이 null 입니까? : ${pOne == null} 또는 ${pOne eq null} 또는 ${empty pOne} <br>
		pOne이 null이 아닙니까? : ${pOne != null} 또는 ${pOne ne null} 또는 ${!empty pOne} <br>

		lOne이 텅 비어있습니까? : ${empty lOne} <br>
		lTwo가 텅 비어있습니까? : ${empty lTwo}
	</p>

	<hr>
	
	<h3>5. 논리연산자</h3>

	<p>
		* EL 연산 <br>
		AND 연산 : ${true && true} 또는 ${true and true} <br>
		OR 연산 : ${true || false} 또는 ${true or false} <br>
	</p>

	<hr>

	<h3>연습문제</h3>

	<p>
		* EL 연산에서 배운 키워드를 주로 가지고 써볼 것 <br>
		big이 small보다 크고 lOne이 텅 비어있습니까? : 
		${big gt small and empty lOne}<br>

		big과 small의 곱은 4의 배수입니까? : 
		${big * small mod 4 eq 0} <br>

		lTwo가 텅 비어있지 않거나 또는 sOne에 담긴 값이 "안녕하세요"와 일치합니까? : 
		${!empty lTwo or sOne eq "안녕하세요"}<br>
	</p>
</body>
</html>
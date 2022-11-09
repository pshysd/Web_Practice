<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Insert title here</title>
    </head>

    <body>
        <h1>JSTL Core Library</h1>

        <h3>1. 변수(속성)</h3>

        <pre>
        * 변수 선언 (&lt;c:set var="key" value="literal" scope="scope"&gt;)(=setAttribute)

        - 변수를 선언하고 초기값을 대입해두는 기능을 제공
        - 해당 변수를 어떤 scope에 담아둘 것인지 지정 가능(scope 속성을 생략 시 기본값 pageScope)
        -> 즉 해당 scope 영역에 setAttribute 메소드를 이용해서 key+value 형태로 데이터를 담아놓는 개념과 동일
        -> c:set을 통해 선언된 변수는 EL로도 접근해서 사용 가능함(단, 스크립팅 원소로는 접근 불가능함)

        * 주의사항
        - 변수의 타입을 별도로 지정하지 않음
        - 반드시 해당 변수의 담아두고자 하는 초기값(value) 속성을 무조건 세팅해야함 (항상 key:value 세트로 넣어야 함, 선언과 동시에 초기화)
    </pre
        >

        <c:set var="num1" value="10" />
        <!-- scope 생략했으므로 pageScope에 담김 -->
        <!-- pageContext.setAttribute("num1", 10)과 동일 -->

        <c:set var="num2" value="20" scope="request" />
        <!-- requestScope-->
        <!-- request.setAttribute("num2", 20) -->

        num1 변수값 : ${num1} <br />
        num2 변수값 : ${num2} <br />

        <c:set var="result" value="${num1 + num2}" scope="session" />
        <!-- session.setAttribute("result", (int)pageContext.getAttribute("num1") + (int)request.getAttribute("num2")) -->

        num1 + num2 : ${result} <br /><br />

        <!--
        EL 구문 사용 시 변수명만 제시하면 공유 범위가 가장 작은 곳 부터 찾앚게 됨
        (티가 나지는 않지만 속도가 좀 느려질 수는 있다.) -> 스코프영역.변수명 을 권장
    -->

        스코프영역.변수명으로 값을 출력 <br />
        num1 : ${pageScope.num1} <br />
        num2 : ${requestScope.num2} <br />
        result : ${requestScope.result} <br />
        result : ${sessionScope.result} <br /><br />

        이런 표기법도 있음 !! <br />
        <c:set var="result" scope="request">9999</c:set>
        <!-- request.setAttribute("result", 9999) -->
        result : ${result} <br />
        <!-- 9999가 출력, 상대적으로 더 작은 범위인 request에도 result가 있기 때문 -->
        result : ${requestScope.result} <br />
        <!-- 9999 -->
        result : ${sessionScope.result} <br />
        <!-- 30 -->

        <hr />

        <pre>
        * 변수삭제(&lt; c:remove var="key" scope="scope"&gt;)

        -해당 변수를 scope에서 찾아서 제거하는 태그
        - scope 지정 생략 시 모든 scope에서 해당 변수를 다 찾아서 제거함
        - scope를 지정했따면 해당 scope에서 해당 key-value 세트를 찾아서 제거함
        - 즉, 해당 scope로 부터 .removeAttribute("key")라는 메소드를 이용해서 key-value 세트를 제거하는 것과 동일
    </pre
        >

        삭제 전 result : ${result} <br /><br />
        <!-- requestScope.result-->

        1) 특정 scope를 지정해서 삭제 <br />
        <c:remove var="result" scope="request" />
        <!-- resuest.removeAttribute("result")와 동일 -->
        request로부터 삭제 후 result : ${result} <br /><br />

        2) 모든 scope로부터 삭제 <br />
        <c:remove var="result" />
        <!--
            pageContext.removeAttribute("result");
            request.removeAttribute("result");
            session.removeAttribute("result");
            application.removeAttribute("result");
        -->
        전체 scope의 result 삭제 후 result : ${result} <br /><br />

        <hr />

        <pre>
            * 변수(데이터) 출력(&lt; c:out value="출력하고자 하는 값" default="기본값(생략가능)" escapeXml="true(기본값, 생략가능)/false"&gt;)

            - 데이터를 출력하고자 할 떄 사용하는 태그
            - default : value에 기술한 출력하고자 하는 값이 없을 경우 기본 값으로 대체해서 출력할 내용물을 기술하는 속성
            - escapeXml : 태그로써 해석해서 출력할지에 대한 여부 (생략 가능, 생략 시 true가 기본값)
        </pre>

        그냥 EL 구문으로 result 출력 : ${result} <br /><br />
        <!-- 없으므로 아무것도 안찍힌다 -->
        c:out 태그로 result 출력 (default 속성 생략) :
        <c:out value="${result}" /> <br />
        c:out 태그로 result 출력 (default 속성 추가) :
        <c:out value="${result}" default="없음" /> <br>

        <!-- escapeXml 속성 테스트-->
        <c:set var="outTest" value="<b>출력테스트</b>" />
        <!-- pageContext.setAttribute("outTest", "<b>출력테스트</b>"); -->
        escapeXml 속성이 true인 경우 (기본값) : <c:out value="${outTest}" /> <!-- 태그가 해석이 안됨 (문자열로 취급) --> <br>
        escapeXml 속성이 false인 경우 : <c:out value="${outTest}" escapeXml="false" />

        <hr>

        <h3>2. 조건문 - if (&lt;c:if test="조건식"&gt;)</h3>

        <pre>
            - JAVA의 단일 if문과 비슷한 역할을 하는 태그
            - 조건식은 test라는 속성에 작성 (*** 단, 반드시 EL 구문으로 작성해줘야 함)
        </pre>
        <%--
        기존 방식
        <%
            <% 
                if(조건식) {


                }
            %>
        %>
        --%>

        <c:if test="${num1 gt num2}">
            <!-- 만약 조건이 true라면 이 if 태그 안쪽의 구문이 실행될 것 -->
            <b>num1이 num2보다 큽니다.</b>
        </c:if>
        <!-- if(num1>num2) <b>num1이 num2보다 큽니다</b> -->

        <c:if test="${num1 le num2}">
            <b>num1이 num2보다 작거나 같습니다.</b>
        </c:if>

        <br>

        <c:set var="str" value="안녕하세요" />
        <%--
        기존 방식
        <% if(str.equals("안녕하세요")) { %>
            ~~~~
        <% } %>
        --%>

        <c:if test="${str eq '안녕하세요'}">
            <mark>Hello World!</mark>
        </c:if>

        <c:if test="${str ne '안녕하세요'}">
            <mark>Goodbye World!</mark>
        </c:if>

        
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.model.vo.Person"%> <%@ taglib prefix="c"
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

        <hr>

        <h3>3. 조건문 - choose, when, otherwise (&lt;c:choose&gt;, &lt;c:when test="조건식"&gt;, &lt;c:otherwise&gt;)</h3>

        <pre>
            - JAVA의 if-else, if-else if 문 또는 switch문과 비슷한 역할을 하는 태그
            - 각 조건들은 c:choose 태그의 하위요소로 묶으면 됨
            - 각 조건들은 c:when 태그를 통해서 작성
            - 작성했던 모든 조건들에 대해서 이도저도 아닐 때 (else 블럭) c:otherwise로 표현함
        </pre>

        <%-- 
            기존 방법
            <% if(num1 == 20) { %>
                ~~~
            <%  } else if (num1 == 10) { %>
                ~~~

            <% } else { %> 
                ~~~~
            <% } %>
        --%>

        <c:choose>
            <c:when test="${num1 eq 20}"><b>처음 뵙겠습니다.</b></c:when>
            <c:when test="${num1 eq 10}"><b>다시 봐서 반갑습니다.</b></c:when>
            <c:otherwise><b>안녕하세요.</b></c:otherwise>
        </c:choose>

        <hr>

        <h3>4. 반복문 - forEach</h3>

        <pre>
            for loop 표현법
            &lt;c:forEach var="변수명" begin="초기값" end="끝값" step="증감값(생략시 기본값 1)"&gt;
                반복적으로 실행할 구문
            &lt;c:forEach&gt;`

            향상된 for loop 표현법
            &lt;c:forEach var="변수명" items="순차적으로접근할배열또는컬렉션명" varStatus="현재접근된요소의상태값을보관할변수명(생략가능)"&gt;
                반복적으로 실행할 구문
            &lt;c:forEach%>

            -> step : 증감식에 해당, 생략 시 기본 값은 1 (반복이 돌 떄 마다 1씩 증가한다.)
            -> varStatus : 현재 접근한 요소의 상태 값을 보관할 변수명 (지정하기 나름)
                            부가적인 기능 제공 (반복의 횟수를 알려준다든지, 현재 접근한 요소의 인덱스를 알려준다든지)
        </pre>

        <!-- for loop -->
        <%--

        <% for(int i=1; i<=10; i++) { %>
            ~~~
        <% } %>

        --%>

        <c:forEach var="i" begin="1" end="10" step="1">
            반복 확인 : ${i} <br>
        </c:forEach>

        <br>
        <%--
        
        <% for(int i=1; i<=10; i += 2) { %>
        ~~~
        <% } %>

        --%>

        <c:forEach var="i" begin="1" end="10" step="2">
            반복 확인 : ${i} <br>
        </c:forEach>

        <br>

        <!-- 태그 안에서도 사용 가능함 -->
        <c:forEach var="i" begin="1" end="6" step="1">
            <h${i}>태그 안에서도 적용 가능함</h${i}>
        </c:forEach>

        <br>

        <!-- 향상된 for loop -->
        <%--

        <% for(String s : list) { %>
                ~~~
        <% } %>
        
        --%>
        <c:set var="colors">
            red, yellow, green, pink
        </c:set> <!-- 배열과 같은 역할 -->

        color 값 : ${ colors } <br>

        <ul>
            <c:forEach var="c" items="${colors}">
                <li style="color:${c}">${c}</li>
            </c:forEach>
        </ul>

        <br>
        
        <!-- 종합 응용 -->

        <%
            // DB로부터 조회 후 서블릿으로부터 넘겨받았다는 가정하에 테스트 데이터 세팅
            ArrayList<Person>list = new ArrayList<>();
            list.add(new Person("홍길동", 20, "남자"));
            list.add(new Person("김말순", 30, "여자"));
            list.add(new Person("박말똥", 40, "남자"));

            request.setAttribute("pList", list);
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>순번</th>
                    <th>이름</th>
                    <th>나이</th>
                    <th>성별</th>
                </tr>
            </thead>
            <tbody>
                <%--
                <% if(pList.isEmpty()) {%>
                조회된 회원이 없습니다
                <%} else { %>
                    <% for(Person p : pList) { %>

                    <% } %>
                <% } %>
                --%>

                <c:choose>
                    <c:when test="${empty pList}">
                        <tr align="center">
                            <td colspan="4">조회 결과가 없습니다.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${pList}">
                            <tr>
                                <td>${status.count}</td> <!-- index : 0부터 시작하는 인덱스값 / count : 1부터 시작하는 반복 횟수 -->
                                <td>${p.name}</td>
                                <td>${p.age}</td>
                                <td>${p.gender}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <h3>5. 반복문 - forTokens</h3>

        <pre>
            &lt;c:forTokens var="각값을보관할변수" items="분리시키고자하는문자열" delims="구분자"&gt;

            - 구분자를 통해서 분리된 각각의 문자열에 순차적으로 접근하면서 반복을 수행
            - JAVA의 StringTokenizer 또는 split(구분자)와 비슷한 역할
        </pre>

        <c:set var="device" value="컴퓨터, 휴대폰, TV, 에어컨/냉장고.세탁기"></c:set>

        <ul>
            <c:forTokens var="d" items="${device}" delims=",./">
                <li>${d}</li>
            </c:forTokens>
        </ul>

        <hr>

        <h3>6. 쿼리스트링 관련 - url, param</h3>

        <pre>
            &lt;c:url var="변수명" value="list.bo"&gt;
                &lt;c:param name="currentPage" value="1" /&gt; // -> http:11231:123123/asd/list.bo?currentPage=1
                &lt;c:param name="keyword" value="검색어" /&gt; // -> http:11231:123123/asd/list.bo?currentPage=1&keyword=검색어
                ...
            &lt;/c:url&gt;
            - url 경로를 생성하고, 쿼리스트링을 정의할 수 있는 태그
            - 넘겨야 할 쿼리스트링이 길 경우 사용하면 편하다.
        </pre>

        <a href="list.do?cPage=1&num1=2&city=서울&name=홍길동">기존 방식</a> <br>

        <!-- url, param 태그를 이용한 방식 -->
        <c:url var="query" value="list.do">
            <c:param name="cPage" value="1" />
            <c:param name="num1" value="2" />
            <c:param name="city" value="서울" />
            <c:param name="name" value="홍길동" />
        </c:url>

        <a href="${query}">c:url을 이용한 방식</a>
    </body>
</html>

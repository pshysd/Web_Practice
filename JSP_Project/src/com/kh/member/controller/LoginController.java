package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
         * < HttpServletRequest 객체와 HttpServletResponse 객체 >
         * - request : 서버로 요청할 때의 정보들이 담겨있음
         * (요청시 전달값, 요청 전송 방식, 요청자의 ip 주소 등등..)
         * request.getParameter() / request.getParamterValues() 로 값 뽑기
         * - response : 요청에 대해 응답할 때 필요한 객체
         * 자바 코드로 응답페이지를 만들때 주로 사용
         * 
         * < GET 방식과 POST 방식 >
         * - GET : 사용자가 입력한 값이 url 에 노출 / 데이터의 길이 제한 / 대신 즐겨찾기가 편리
         * - POST : 사용자가 입력한 값이 url 에 노출 X / 데이터의 길이 제한 X / 대신 즐겨찾기가 불편 / Timeout 이 존재
         */

        // 1) 인코딩 처리해야함 (POST 방식일 경우)
        request.setCharacterEncoding("UTF-8");

        // 2) 요청 시 전달값을 꺼내기 (request 의 parameter 영역으로부터)
        // 요청시 전달값을 뽑아서 변수에 담기!!
        // request.getParameter("키값") : String 타입의 밸류값 한개 리턴
        // request.getParameterValues("키값") : String[] 타입의 밸류값 여러개가 묶여서 리턴

//		요청시 전달값
//		userId : 아이디값
        String userId = request.getParameter("userId"); // "user01"
//		userPwd : 비밀번호값
        String userPwd = request.getParameter("userPwd"); // "pass01"

//		saveId : 아이디 저장 여부에 대한 체크값
        String saveId = request.getParameter("saveId"); // "y" or null

        if (saveId != null && saveId.equals("y")) {

            // 아이디를 저장하겠다.
            // -> userId라는 키값으로 넘겨받았던 아이디 값을 쿠키로 저장

            /*
             * * Cookie : 브라우저에서 사용되는 일종의 저장소 개념 (브라우저에서 키-밸류 세트로 값을 저장하고 있음)
             * 사이트가 사용하고 있는 서버에서 만들어져서 사용자의 컴퓨터(브라우저)에 저장하는 정보
             * 주로 보안과 관련없는 기능을 다룰 때 사용한다.
             * 
             * * 쿠키 생성
             * - 쿠키는 객체를 생성한 다음 응답 정보에 같이 첨부해서 브라우저로 넘겨야만 사용이 가능함
             * - 쿠키 생성 시 name(키값), value(밸류) 세트로 반드시 지정해야 함
             * - name, value는 모두 문자열이여야만 한다.
             * - name이 중복될 경우 덮어씌워짐
             * - expires (max age - 만료기간) 은 초 단위로 작성, 필수는 아님...
             * 
             * [ 표현법 ]
             * Cookie cookie = new Cookie('name', 'value');
             * cookie.setMaxAge(초 단위 시간); <- 만료기간 지정
             */

            Cookie cookie = new Cookie("saveId", userId); // saveId : "user01"
            cookie.setMaxAge(1 * 24 * 60 * 60);
            
//            쿠키 브라우저로 넘기기 -> response로 넘기기
            response.addCookie(cookie);
        } else {

            // 아이디를 저장하지 않겠다.
            // -> 아이디를 저장하고 있던 쿠키 자체를 아예 삭제
            // 쿠키를 삭제시키지 않으면 만료일까지 살아있기 때문...
            
            /*
             * * 쿠키 삭제
             * - 쿠키 삭제 구문은 따로 명령어가 없다
             * - 그 대신 같은 키 값으로 쿠키를 하나 생성하고 (덮어씌워짐), 그 쿠키의 만료 시간을 0으로 세팅하면 됨
             */
            Cookie cookie = new Cookie("saveId",userId);
            cookie.setMaxAge(0); // 0초
            
            response.addCookie(cookie);
        }

        // 주의사항!!
        // 키값을 제시시 오타가 나면 없는 키값을 찾는 꼴이기 때문에 null 이 리턴됨

        // System.out.println(userId);
        // System.out.println(userPwd);

        // 3) 요청시 전달값들을 VO 객체로 가공하기
        Member m = new Member();
        m.setUserId(userId);
        m.setUserPwd(userPwd);

        // 4) 가공한 VO 객체를 해당 요청을 처리하는 서비스 클래스의 메소드로 넘기기 (호출)
        Member loginUser = new MemberService().loginMember(m);

        // 5) 처리된 결과를 가지고 사용자가 보게될 응답뷰 지정
        // System.out.println(loginUser);

        /*
         * * 응답 페이지에 전달할 값이 있다면 값을 어딘가에 담아야 함
         * (담아줄 수 있는 Servlet Scope 내장객체 4종류)
         * 1) application : application 객체에 담은 데이터는 웹 애플리케이션 전역에서 다 꺼내 쓸 수 있음
         * 2) session : session 객체에 담은 데이터는 웹 애플리케이션 전역에서 다 꺼내 쓸 수 있음
         * 단, 한번 담은 데이터는 내가 직접 지우기 전까지 쓸 수 있음
         * 한번 담은 데이터는 서버가 멈추기 전까지 쓸 수 있음
         * 한번 담은 데이터는 브라우저가 종료되기 전까지 쓸 수 있음
         * 3) request : request 객체에 담은 데이터는 해당 요청에 대한 응답 페이지에서만 사용 가능함
         * 해당 그 요청을 담당하는 Servlet 과 그 요청에 대한 응답페이지인 jsp 에서 (한세트) 쓸 수 있음
         * 4) page : 해당 jsp 페이지에서만 데이터를 담고 꺼내 쓸 수 있음
         * 
         * => session 과 request 를 주로 많이 쓴다.
         * 
         * 공통적으로 데이터를 담고자 한다면 객체명.setAttribute("키", 밸류);
         * 데이터를 꺼내고자 한다면 객체명.getAttribute("키"); : Object 타입의 밸류 리턴
         * 데이터를 지우고자 한다면 객체명.removeAttribute("키");
         */

        if (loginUser == null) { // 로그인 실패 => 에러문구를 담아서 에러페이지로 응답

            // 응답 페이지에서 필요로 하는 데이터
            request.setAttribute("errorMsg", "로그인에 실패했습니다.");

            // 응답 페이지 지정 => RequestDispatcher 객체 생성 후 포워딩
            RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
            view.forward(request, response);

            /*
             * 포워딩 방식 : 해당 url 경로로 선택된 뷰가 보여질 뿐
             * url 은 절대 변경되지 않음 (요청했을때의 이 Servlet 의 url 이 주소창에 그대로 남아있음)
             */
        } else { // 로그인 성공 => 응답페이지에 loginUser 데이터 전달, 메인페이지로 응답

            // 응답 페이지에서 필요로 하는 데이터
            // 로그인한 회원의 정보를 로그아웃 하기 전까지
            // 계속 가져다 써야함 (글 작성, 마이페이지, 글 수정, 댓글작성, ..)
            // => session 객체에 담기

            // Servlet 에서 JSP 내장 객체인 session 에 접근하고자 하려면
            // 우선 session 객체 (HttpSession) 를 얻어와야함
            // => request 객체로부터 getSession() 메소드를 통해
            request.getSession().setAttribute("loginUser", loginUser);
            request.getSession().setAttribute("alertMsg", "성공적으로 로그인이 되었습니다.");

            /*
             * 응답 페이지 지정 방식
             * 
             * 1. 포워딩 방식 : 해당 선택된 jsp 가 보여질뿐 url 주소는 여전히 이 서블릿의 url 맵핑값으로 지정되어있음
             * http://localhost:8888/jsp/login.me
             * 
             * RequestDispatcher 객체 생성 -> forward 메소드 호출
             * 
             * 2. url 재요청방식 (sendRedirect 방식)
             * : 자바 코드로 url 주소를 요청하는 방식 (즉, 새로고침의 개념)
             * 자바스크립트때 location.href = "~~~"; 와 같은 원리
             * 
             * response.sendRedirect() 메소드 호출
             */
            response.sendRedirect(request.getContextPath()); // 내가 요청한 url 주소로 이동하겠다.
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.model.vo.Person;

/**
 * Servlet implementation class ELServlet
 */
@WebServlet("/el.do")
public class ELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ELServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
         * *데이터들을 담을 수 있는 JSP 내장객체 종류(Servlet Scope내장객체)
         * 1. ServletContext(applicationScope)
         *      한 애플리케이션 당 단 1개 존재하는 객체
         *      이 영역에 데이터를 담으면 애플리케이션 전역에서 사용 가능
         *      => 공유범위가 가장 큼
         * 2. HTTPSession(sessionScope)
         *      한 브라우저 당 단 1개 존재하는 객체
         *      이 영역에 데이터를 담으면 애플리케이션 전역에서 사용 가능
         *      단,값이 한번 담기면 서버가 멈추거나,브라우저가 종료되거나,값이 지워지기 전까지만 사용 가능
         *      => 공유범위가 다소 제한적임
         * 3. HTTPServletRequest(requestScope)
         *      요청 및 응답시 매번 생성되는 객체
         *      이 영역에 데이터를 담으면 해당 request 객체를 포워딩 받는 응답 jsp에서만 사용 가능(1회성)
         *      => 공유범위가 해당 요청에 대한 응답 jsp 단 하나뿐임
         * 4.   PageContext(pageScope)
         *      현재 그 jsp 페이지에서만 사용 가능
         *      => 공유범위가 가장 작음(현재 그 페이지에서 값을 담을 수 있고 그 페이지에서만 값을 꺼낼 수 있음)
         * 
         * 위의 객체들에 값을 담을때는 .setAttribute("키",담고자하는데이터);
         *          값을 꺼낼때는 .getAttribute("키"); =>Object 타입의 벨류값 리턴(다형성 적용)
         *          값을 지우고자 할 때는.removeAttribute("키");
         */
        
        // request 에 데이터 담기
        request.setAttribute("classRoom", "D강의장");
        request.setAttribute("student",new Person("홍길동",15,"남자"));
        
        // sessionScope 에 데이터 담기
        HttpSession session = request.getSession();
        session.setAttribute("academy", "KH정보교육원");
        session.setAttribute("teacher", new Person("김가현",20,"여자"));
        
        //requestScope 와 sessionScope 에 동일한 키값으로 데이터를 담기
        request.setAttribute("scope", "request");
        session.setAttribute("scope", "session");
        
        // applicationScope 에 데이터 담기
        //ServletContext application = request.getSession().getServletContext();//세션으로부터 얻어내기
        ServletContext application=request.getServletContext(); //리퀘스트로 부터 얻어내기
        application.setAttribute("scope", "application");
        
        //jsp 페이지로 포워딩
        request.getRequestDispatcher("views/1_El/01_el.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

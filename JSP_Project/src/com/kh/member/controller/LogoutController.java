package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그아웃 요청 처리
		// session 에 담긴 회원 정보를 지워주면됨
		// => 전역에서 데이터를 꺼내 쓸 수 있되, 서버가 종료되기 전까지만, 브라우저가 종료되기 전까지만, 데이터가 지워지기 전까지만 사용가능
		
		// 1. session 으로부터 키-밸류 세트를 지우기
		// request.getSession().removeAttribute("loginUser");
		
		// 2. session 객체를 무효화 시키기
		request.getSession().invalidate();
		
		// 응답페이지 => /jsp
		// url 재요청방식 (response.sendRedirect() 메소드 호출)
		// response.sendRedirect("/jsp");
		
		// 앞으로는 응답페이지 요청 시 contextRoot (== contextPath) 를 직접 작성하는 것이 아니라
		// request.getContextPath() 메소드를 통해 반환해서 쓸 것!!
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

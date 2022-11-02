package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqAjaxController1
 */
@WebServlet("/jqAjax1.do")
public class JqAjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController1() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		요청 시 전달 값 뽑기
		String str = request.getParameter("input");
		
		System.out.println("요청 시 전달 값 : " + str);
		
//		요청 처리를 다 했다는 가정하에 응답할 데이터
		String responseData = "입력된 값 :" + str + ", 길이 :" + str.length();
		
//		요청을 보냈던 곳으로 응답 데이터를 돌려주기
//		(기존의 동기식 요청에서는 응답 데이터를 Servlet 내장 객체에 실어서 페이지째로 돌려줌)
//		-> 비동기식 요청에서는 응답 데이터만 넘겨줌 !!
		
//		혹시라도 응답 데이터에 한글이 있을 경우 응답 데이터가 깨질 수 있기 때문에
//		응답 데이터에 대한 mimetype과 charset을 설정해야함
		response.setContentType("text/html; charset=UTF-8");

//		2. jsp와의 통로를 열어준다.
//		-> PrintWriter 형식의 통로를 열어줌
		response.getWriter().print(responseData);
//		-> 자바 코드 안에 html 코드를 넣을 때 사용했던 메소드들 그대로 사용
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

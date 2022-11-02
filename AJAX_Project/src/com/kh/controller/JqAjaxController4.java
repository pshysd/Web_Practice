package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController4
 */
@WebServlet("/jqAjax4.do")
public class JqAjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController4() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		DB로부터 회원 정보를 전체 조회했다 라는 가정하에
//		간단히 Member만 들어갈 수 있는 ArrayList 객체 생성하기
		ArrayList<Member> list = new ArrayList<>();
		list.add(new Member(1, "고길동", 50, "남"));
		list.add(new Member(2, "박말똥", 30, "여"));
		list.add(new Member(3, "김말똥", 22, "남"));
		list.add(new Member(4, "이길동", 35, "여"));
		
//		응답 데이터로 ArrayList를 통째로 넘기기
//		-> 그냥 넘기면 문자열 형식으로 응답이 넘어간다
		
//		해결방안 1. 각각의 Member를 JSONObject로 가공후에
//					JSONObject를 각각 JSONArrayList로 가공해서 보내는 방법 (순수 JSON jar 파일 이용)
		
//		해결방안 2. GSON 사용
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

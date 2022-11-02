package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController3() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		요청시 전달 값인 회원번호(no) 뽑기
		int memberNo = Integer.parseInt(request.getParameter("no"));
		
//		데이터를 조회했다는 가정하에 간단하게 Member 객체 구성
		Member m = new Member(memberNo, "고길동", 50, "남");
		
//		만들어진 객체를 응답 데이터로 넘기기
//		response.setContentType("text/html; charset=UTF-8");
		
//		내부적으로 toString() 메소드가 호출돼서 문자열로 응답이 넘어감
//		response.getWriter().print(m /*.toString()*/);
		
//		JSON 객체타입으로 가공해서 넘기기
		/*
		JSONObject jObj = new JSONObject(); // {}
		
		jObj.put("memberNo", m.getMemberNo()); // {memberNo : 30}
		jObj.put("memberName", m.getMemberName()); // {memberNo : 30, memberName : "고길동"}
		jObj.put("age", m.getAge()); // {... age : 50}
		jObj.put("gender", m.getGender()); // {... gender : "남"}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
		*/
		
//		JSON 가공 쉽게 만들어주는 라이브러리 : GSON(Google JSON) <- 쓸라면 연동해야됨
		response.setContentType("application/json; charset=UTF-8");
		
//		Gson gson = new Gson();
//		toJson(응답할 객체, 응답할스트림객체);
//		-> response.getWriter() 스트림으로 m 객체를 응답 데이터로 넘기겠다
//		변환 시 전달되는 키 값은 VO 객체의 각 필드명을 자동으로 잡혀서 넘어간다.
		new Gson().toJson(m,response.getWriter()); // 한번에 날려버리는 것도 가능...
		
//		VO 객체 하나만 응답시 JSONObject 타입으로 만들어져서 응답
//		ArrayList 응답시 JSONArray 타입으로 만들어져서 응답
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class jqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JqAjaxController2() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		기존의 동기식 요청에서는
//		POST 방식으로 요청이 들어왔을 경우 인코딩 설정을 먼저 해주고 나서
//		요청시 전달 값을 뽑았어야 했음
//		-> 비동기식 요청에서는 기본 인코딩 세팅이 UTF-8이기 때문에 따로 해줄 필요없음

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));

//		요청 처리를  다 했다는 가정하에 응답할 데이터 (문자열)
		/*
		String responseData = "이름 : " + name + ", 나이 : " + age;
//		System.out.println(responseData);
		
//		데이터 응답하기
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
		*/
		
//		ajax로 응답 데이터를 여러 개 만들어 뿌리고 싶은 경우
//		요청 처리를 다 했다는 가정하에 응답할 데이터 (문자열)
		/*
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(name);
		response.getWriter().print(age);
		*/
//		-> 한 개의 문자열처럼 이어져서 응답됨
//		-> 즉, ajax는 기본적으로 결과를 오로지 한 개만 응답할 수 있는 구조임
		
//		만약 여러 개의 응답 데이터를 넘기고 싶다면 JSON 이라는 개념을 활용해야 함
		/*
		 * * JSON (JavaScript Object Notation ) : 자바스크립트 객체 표기법
		 * - ajax 통신 시 데이터 전송에 사용되는 포맷 형식 중 하나
		 * - JSON 처리 시 사용되는 클래스 종류는 기본적으로 자바에서 제공하지 않음 (외부 라이브러리 필요)
		 * 
		 * 응답 데이터가 여러 개일 경우 JSON에서 제공하는 두가지 형태 중 선택하여 가공
		 * 1. JSONArray[value, value, value] -> 자바스크립트의 배열 형식
		 * 2. JSONObject[{key:value, key:value, key:value ...} -> 자바스크립트의 객체 형식
		 */
		
//		JSONArray 타입으로 넘기기
		/*
		JSONArray jArr = new JSONArray();
		jArr.add(name); // ["김말똥"]
		jArr.add(age); // ["김말똥", 23]
//		-> 자바스크립트에서의 배열은 자바에서의 ArrayList와 유사(타입 제한x, 사이즈 제한x, 인덱스 개념o)
		
//		jArr을 응답 데이터로 넘기기
		response.setContentType("application/json; charset=UTF-8"); // 클라이언트에 json 타입으로 넘기는 세팅
		response.getWriter().print(jArr); // text/html로 설정 시 ["김말똥",23] 이라는 String 타입으로 넘어감
		*/
		
//		JSONObject 타입으로 넘기기
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("name", name);
		jObj.put("age", age);
//		-> 자바스크립트에서의 객체는 자바에서의 HashMap과 유사하다 (key-value 세트, 사이즈 제한x, 키값 중복x 밸류 중복o, 인덱스 개념x)
		
//		jObj를 응답데이터로 넘기기
		response.setContentType("apllication/json; charset=UTF-8");
		response.getWriter().print(jObj);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

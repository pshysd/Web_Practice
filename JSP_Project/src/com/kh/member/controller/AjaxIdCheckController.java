package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxIdCheckController
 */
@WebServlet("/idCheck.me")
public class AjaxIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxIdCheckController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
//	    요청 시 전달 값 뽑기
	    String checkId = request.getParameter("checkId");
	    
//	    전달 값을 서비스로 넘겨서 요청 처리 후 결과 받기
	    int count = new MemberService().idCheck(checkId); // 중복된 아이디가 있으면 1, 없으면 0
	    
//	    조건에 따른 응답 데이터 넘겨주기
//	    -> 어차피 응답 데이터 하나만 넘어가기 때문에 굳이 JSON은 쓰지 않음
	    response.setContentType("text/html; charset=UTF-8");
	    
	    if(count > 0) {
	        response.getWriter().print("NNNNN");
	    }else {
	        response.getWriter().print("NNNNY");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberDeleteController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		인코딩 설정
		request.setCharacterEncoding("UTF-8");

//		요청 시 전달 값 뽑기 및 변수나 객체에 담기

//		뽑아야 할 값
//		userPwd : 비밀번호
		String userPwd = request.getParameter("userPwd");

//		추가적으로 필요한 값 : userId
//		로그인한 회원의 정보를 알아내는 방법
//		1. input type="hidden" 으로 애초에 넘기기
//		2. session에 담겨있는 회원의 정보를 뽑아오는 방법

//		세션 객체 얻어내기 => loginUser 키 값에 해당되는 밸류로부터 아이디 값만 getter로 뽑기
		HttpSession session = request.getSession();
		String userId = ((Member) session.getAttribute("loginUser")).getUserId();

//		Member 객체로 가공합시다
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);

		int result = new MemberService().deleteMember(m);

		if (result > 0) { // 성공 -> 성공 문구를 담아서 메인 페이지로 url 요청 (로그아웃도 돼야함)
			
			session.setAttribute("alertMsg", "회원탈퇴 되었습니다. 그동안 이용해주셔서 감사합니다.");
			// invalidate() 메소드를 이용해서 세션을 무효화시켜 로그아웃 처리
//			session.invalidate();
//			-> invalidate() 메소드 사용 시 세션 자체가 만료되어 alert가 되지 않기 때문에
//			(값을 담은 그릇 자체를 없애버림)
			session.removeAttribute("loginUser");
			response.sendRedirect(request.getContextPath());
			
			
		} else { // 실패 -> 에러 문구를 request에 담아서 (errorMsg 키값) 에러페이지로 포워딩
			
			request.setAttribute("errorMsg", "에러가 발생했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}
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

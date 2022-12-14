package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    클릭했을 때의 글 번호
	    int noticeNo = Integer.parseInt(request.getParameter("nno"));
	    
//	    조회수 증가용 서비스 먼저 호출
	    int result = new NoticeService().increaseCount(noticeNo);
	    
//	    조회수가 증가 되었으면 1 RETURN, 아니면 0 RETURN 되었을 것
	    if(result > 0) {
//	      증가 되었으니 게시글 상세조회 요청, noticeDetailView.jsp forwarding
	        Notice n = new NoticeService().selectNotice(noticeNo);
//	        System.out.println(n);
	        request.setAttribute("n", n);
	        
//	      공지사항 상세보기 페이지 포워딩
	        request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
	        
	    }else {
	        request.setAttribute("errorMsg", "공지사항 조회 실패");
	        request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/delete.no")
public class NoticeDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//	    삭제하고자 하는 글 번호
        int noticeNo = Integer.parseInt(request.getParameter("nno"));

//	    서비스 클래스로 글 번호를 넘기면서 삭제 요청 및 결과 받기
        int result = new NoticeService().deleteNotice(noticeNo);

        if (result > 0) {
            request.getSession().setAttribute("alertMsg", "삭제되었습니다.");
            response.sendRedirect(request.getContextPath() + "/list.no");
        } else {
            request.setAttribute("errorMsg", "공지사항 삭제 중 문제가 발생했습니다.");
            request.getRequestDispatcher("views/common/errorPage.jsp");
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

package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
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

//		뽑아야 할 값

//		title : 글 제목
        String noticeTitle = request.getParameter("title");

//		content : 글 내용
        String noticeContent = request.getParameter("content");

//		추가적으로 필요한 값 : 작성자의 회원번호(userNo)
        String userNo = request.getParameter("userNo");
//		noticeWriter 필드는 String 타입으로 정의해뒀기 때문에 파싱안함

        Notice n = new Notice();
        n.setNoticeTitle(noticeTitle);
        n.setNoticeContent(noticeContent);
        n.setNoticeWriter(userNo);

        int result = new NoticeService().insertNotice(n);

        if (result > 0) {
            request.getSession().setAttribute("alertMsg", "공지사항 작성 완료.");
            response.sendRedirect(request.getContextPath() + "/list.no");
        } else {
            request.setAttribute("errorMsg", "공지사항 작성에 문제가 있습니다.");
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

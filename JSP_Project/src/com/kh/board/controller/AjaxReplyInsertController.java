package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.bo")
public class AjaxReplyInsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//	    요청시 전달값 먼저 뽑기
//	    content : 댓글 내용
        String replyContent = request.getParameter("content");

//	    bno : 참조게시글 번호
        int boardNo = Integer.parseInt(request.getParameter("bno"));

//	    추가적으로 필요한 데이터 : 댓글 작성자(지금 로그인한 회원)의 회원번호
        int userNo = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();

//	    Reply 타입으로 가공
        Reply r = new Reply();
        r.setReplyContent(replyContent);
        r.setRefBoardNo(boardNo);
        r.setReplyWriter(String.valueOf(userNo)); // String이 아닌 타입을 String 타입으로 파싱해줌

        int result = new BoardService().insertReply(r);

//	    응답데이터 보내기
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(result);
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

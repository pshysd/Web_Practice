package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//	    해당 게시글 번호 먼저 뽑기
        int boardNo = Integer.parseInt(request.getParameter("bno"));

//	    조회수 증가 / 게시글 조회(Board) / 첨부파일 조회(Attachment)
//	    -> BoardService로 요청을 3번 보내야 함
        BoardService bs = new BoardService();

//	    1. 조회수 증가
        int result = bs.increaseCount(boardNo);

        if (result > 0) { // 성공하면

//	        게시글 조회, 첨부파일 조회
            Board b = bs.selectBoard(boardNo);
            Attachment at = bs.selectAttachment(boardNo);

            request.setAttribute("b", b);
            request.setAttribute("at", at);

            request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
        } else { // 실패하면

            // 에러문구 담아서 에러페이지로 포워딩
            request.setAttribute("errorMsg", "게시글 상세조회 중 문제 발생");
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

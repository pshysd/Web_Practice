package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/updateForm.bo")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
//	    카테고리 정보들 전체 조회, 해당 게시글 정보 조회, 해당 게시글에 딸린 첨부파일 정보 조회
	    BoardService bs = new BoardService();

	    int boardNo = Integer.parseInt(request.getParameter("bno"));
	    
	    ArrayList<Category> list = bs.selectCategoryList();
	    
	    Board b = bs.selectBoard(boardNo);
//	    글번호, 카테고리명, 글제목, 글내용, 작성자 아이디, 작성일
	    
	    Attachment at = bs.selectAttachment(boardNo);
//	    첨부파일 번호, 원본명, 수정명, 저장경로
	    
	    request.setAttribute("list", list);
	    request.setAttribute("b", b);
	    request.setAttribute("at", at);
	    
//	    수정하기 페이지 포워딩
	    request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

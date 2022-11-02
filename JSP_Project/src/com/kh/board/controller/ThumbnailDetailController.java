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

/**
 * Servlet implementation class ThumbnailDetailController
 */
@WebServlet("/detail.th")
public class ThumbnailDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailDetailController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    int boardNo = Integer.parseInt(request.getParameter("bno"));
	    
//	    조회수 증가용 서비스 먼저 요청 후 성공 시 상세조회 요청
	    int result = new BoardService().increaseCount(boardNo);
	    
	    if(result > 0) { // 성공 -> 게시글 정보, 첨부파일들 정보 조회
	        
//	        Board 테이블로부터 해당 게시글 정보만 뽑아오기
//	        일반 게시판용 selectBoard 쿼리 활용 -> 기존 INNER JOIN 에서 LEFT JOIN으로 변경
//	        INNER JOIN의 경우 일치하는 컬럼만을 가져오는 구조였는데
//	        사진 게시판의 경우 카테고리가 NULL이기 때문에 일치하는 것이 없어서 반환되는 것이 없었던 것
//	        -> 카테고리 컬럼을 기준으로 일치하는 컬럼, 일치하지 않는 컬럼 모두 가져오려면 OUTER JOIN으로 변경
//	        -> 어찌되었든 BOARD 테이블의 내용물은 조회하고싶기 때문에 BOARD 테이블을 기준으로 LEFT OUTER JOIN
	        Board b = new BoardService().selectBoard(boardNo);
	        
//	        Attachment 테이블로부터 해당 게시글에 딸린 첨부파일을 전부 조회
	        ArrayList<Attachment> list = new BoardService().selectAttachmentList(boardNo);
	        
	        request.setAttribute("b", b);
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("views/board/thumbnailDetailView.jsp").forward(request, response);
	        
	    }else { // 실패
	        request.setAttribute("errorMsg", "문제 발생");
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

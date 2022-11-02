package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//	    인코딩 설정
        request.setCharacterEncoding("UTF-8");

//	    multipart/form-data 형식으로 요청이 들어왔는지 검사
        if (ServletFileUpload.isMultipartContent(request)) {

//	        1. 전송된 파일에 대한 정보들 지정(전송파일 용량 제한, 전달될 파일을 저장할 서버의 실 경로)

//	        1-1. 전송파일 용량 제한 (byte 단위)
            int maxSize = 10 * 1024 * 1024;

//	        1-2. 전달된 파일을 저장할 서버의 실제 경로
//	        애플리케이션 객체에서 얻어오기
            String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");

//	        2. 전달된 파일명 수정 작업 후 서버에 업로드 & MultipartRequest 타입으로 변환
//	        -> 매개변수 생성자 호출
            MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
                    new MyFileRenamePolicy());

//          3. 요청 시 전달 값 뽑기
            int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
            String category = multiRequest.getParameter("category");
            String boardTitle = multiRequest.getParameter("title");
            String boardContent = multiRequest.getParameter("content");

            Board b = new Board();
            b.setBoardNo(boardNo);
            b.setCategory(category);
            b.setBoardTitle(boardTitle);
            b.setBoardContent(boardContent);

//          첨부파일에 대한 정보를 담아둘 변수
            Attachment at = null;

//          요청 시 전달 값 중에 넘어온 첨부파일이 있는지 먼저 검사
            if (multiRequest.getOriginalFileName("reUpfile") != null) {
//                넘어온 첨부파일이 있을 경우

//                -> Attachment 테이블에 UPDATE 또는 INSERT 해야함

//                2개의 쿼리문 중 공통적으로 필요한 항목들 먼저 세팅
//                -> ORIGIN_NAME, CHANGE_NAME 컬럼값
                at = new Attachment();
                at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
                at.setChangeName(multiRequest.getFilesystemName("reUpfile"));

//                INSERT 구문에서 추가적으로 필요로 하는 FILE_PATH 컬럼 값도 세팅
                at.setFilePath("resources/board_upfiles/");

//                기존 파일이 있었는지 없었는지 검사
//                -> 기존 파일이 있었다면 : Attachment 테이블에 UPDATE 구문 실행
//                -> 기존 파일이 없다면 : Attachment 테이블에 INSERT 구문 실행

//                기존 파일이 있을 경우 originFileNo, originFileName을 넘겼었음
                if (multiRequest.getParameter("originFileNo") != null) {
//                    기존 첨부파일이 있을 경우

//                    UPDATE 구문에서 추가적으로 필요로 하는 기존 파일의 고유번호 담기
                    at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
                } else {
//                    기존 첨부파일이 없을 경우

//                    INSERT 구문에서 추가적으로 필요로 하는 참조 게시글 번호를 담기
                    at.setRefNo(boardNo);
                }
            }

//          이 시점 기준으로 각 케이스 별로 필요한 데이터들이 at에 담겨있음

//                                                    b   at
//          CASE 1. 기존 첨부파일 X, 새 첨부파일 X -> b, null
//          CASE 2. 기존 첨부파일 O, 새 첨부파일 O -> b, fileNo 담김 -> BOARD UPDATE, ATTACHMENT UPDATE
//          CASE 3. 기존 첨부파일 X, 새 첨부파일 O -> b, refNo 담김 -> BOARD UPDATE, ATTACHMENT INSERT

//          모두 하나의 트랜잭션으로 처리해야함
            int result = new BoardService().updateBoard(b, at);
            if (result > 0) { // 수정 성공 -> 상세조회 페이지로 url 요청

//              만약 기존 첨부파일이 있고 새로운 첨부파일도 있을 경우 기존 첨부파일 삭제(용량만 차지함)
                if (multiRequest.getParameter("originFileName") != null
                        && multiRequest.getOriginalFileName("reUpfile") != null) {
                    new File(savePath + multiRequest.getParameter("originFileName")).delete();
                }

                request.getSession().setAttribute("alertMsg", "수정되었습니다.");
                response.sendRedirect(request.getContextPath() + "/detail.bo?bno=" + b.getBoardNo());
            } else { // 수정 실패 -> 에러문구 담아서 에러페이지 포워딩
                request.setAttribute("errorMsg", "게시글 수정 실패");
                request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            }
        }

//	    
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

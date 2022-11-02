package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        인코딩 설정
        request.setCharacterEncoding("UTF-8");

//        이 요청이 multipart/form-data 형식인지 검사
        if (ServletFileUpload.isMultipartContent(request)) {
//            1. 전달된 파일에 대한 정보 먼저 지정(전송파일 용량제한, 저장할 파일의 물리적인 경로)
//            1-1. 용량 제한
            int maxSize = 10 * 1024 * 1024;
//            1-2. 저장할 파일의 물리적인 경로
            String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfiles/");

//            2. 전달할 파일명 수정 작업 후 서버에 업로드 + MultipartRequest 타입으로 변환
            MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
                    new MyFileRenamePolicy());

//            3. DB에 전달할 값 뽑기
//            Board에 INSERT -> userNo, title, content
            Board b = new Board();
            b.setBoardWriter(multiRequest.getParameter("userNo"));
            b.setBoardTitle(multiRequest.getParameter("title"));
            b.setBoardContent(multiRequest.getParameter("content"));

//            ATTACHMENT에 INSERT
//            단, 여러 개의 첨부파일이 있을 예정...
//            -> ArrayList<Attachment>에 담기 (최소 1개 ~ 4개)
            ArrayList<Attachment> list = new ArrayList<>();

            for (int i = 1; i <= 4; i++) {
//                키값 먼저 세팅
                String key = "file" + i;

//                해당 키값에 대한 첨부파일이 있다면 처리
                if (multiRequest.getOriginalFileName(key) != null) {
//                    Attachment 객체 생성
//                    원본명, 수정명, 폴더경로, 파일레벨
                    Attachment at = new Attachment();
                    at.setOriginName(multiRequest.getOriginalFileName(key));
                    at.setChangeName(multiRequest.getFilesystemName(key));
                    at.setFilePath("resources/thumbnail_upfiles/");
                    if (i == 1) { // 대표 이미지일 경우 1
                        at.setFileLevel(1);
                    } else { // 아닐 경우 == 상세 이미지일 경우
                        at.setFileLevel(2);
                    }
                    list.add(at);
                }
            }

//            Service 단으로 요청 후 결과 받기
            int result = new BoardService().insertThumbnailBoard(b, list);

            if (result > 0) { // 성공 -> list.th로 url 재요청
                request.getSession().setAttribute("alertMsg", "작성 완료");
                response.sendRedirect(request.getContextPath() + "/list.th");
            } else { // 실패
                request.setAttribute("errorMsg", "업로드 실패");
                request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            }
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

package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.model.vo.PageInfo;

public class BoardService {

    public int selectListCount() {

        Connection conn = getConnection();

        int listCount = new BoardDao().selectListCount(conn);

        close(conn);

        return listCount;
    }

    public ArrayList<Board> selectList(PageInfo pi) {

        Connection conn = getConnection();

        ArrayList<Board> list = new BoardDao().selectList(conn, pi);

        close(conn);

        return list;
    }

    public ArrayList<Category> selectCategoryList() {
        Connection conn = getConnection();

        ArrayList<Category> list = new BoardDao().selectCategoryList(conn);

        close(conn);

        return list;
    }

    public int insertBoard(Board b, Attachment at) {

        Connection conn = getConnection();

//        주의사항 => DAO의 메소드 1개 == 쿼리문 1개

//        BOARD 테이블 INSERT 요청 먼저(첨부파일이 있든 없든 무조건 일어나야 함)
        int result1 = new BoardDao().insertBoard(conn, b);

//        한 개의 트랜잭션에 테이블의 변동이 있는 DML이 두번 실행
//        두 번의 DML 중 하나라도 실행한다면 전부 ROLLBACK 해야함
//        둘 다 성공해야만 COMMIT

//        두번째 요청에 대한 결과 값을 담을 변수 먼저 세팅
        int result2 = 1;

        if (at != null) {
            result2 = new BoardDao().insertAttachment(conn, at);
        }

//        트랜잭션 처리
//        result1 > 0 && result2 > 0 => 둘다 성공일 경우
        if (result1 > 0 && result2 > 0) {

//            경우의 수
//            1. 첨부파일이 있는 경우 result1 == 1, result2 == 1

//            2. 첨부파일이 없는 경우 result1 == 1, result2 == 0(!)
//            첨부파일이 없는 경우 트랜잭션이 모두 성공했을 때도 result2는 여전히 0이기 때문에 else 블럭으로 갈 것
//            애초에 result2 변수 세팅을 1로 변경해준다.

            commit(conn);
        } else {
            rollback(conn);
        }

        close(conn);

//        자바 문법상 메소드 한 개의 리턴 값을 한 개 뿐... -> result1, result2를 같이 넘겨야 함
//        경우의 수
//        result1 == 1, result2 == 1 => result1 * result2 == 1 (둘다성공)
//        result1 == 1, result2 == 0 => result1 * result2 == 0 (1만 성공)
//        result1 == 0, result2 == 0 => result1 * result2 == 0 (둘다 실퍠)
//        result1 == 0, result2 == 1 => result1 * result2 == 0 (2만 성공)
//        혹시라도 하나라도 실패해서 0이 될 경우 아예 실패 값을 전달하기 위해 곱셈 결과를 리턴

        return result1 * result2;
    }

}
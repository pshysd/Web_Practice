package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

//import com.kh.common.JDBCTemplate;
import static com.kh.common.JDBCTemplate.*; //JDBCTemplate class 내 모든 메소드를 그냥 갖다 쓰겠음. 클래스 명시안하고

import com.kh.common.JDBCTemplate;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

    public ArrayList<Notice> selectNoticeList() {

        Connection conn = getConnection();

        ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);

        close(conn);

        return list;
    }

//  공지사항 작성용 서비스

    public int insertNotice(Notice n) {
        Connection conn = getConnection();

        int result = new NoticeDao().insertNotice(conn, n);

        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);

        return result;
    }

}
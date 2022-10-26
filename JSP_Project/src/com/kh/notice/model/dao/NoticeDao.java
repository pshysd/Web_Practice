package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.model.vo.Notice;

public class NoticeDao {
    private Properties prop = new Properties();

    public NoticeDao() {

        String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Notice> selectNoticeList(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectNoticeList");

        ArrayList<Notice> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                list.add(new Notice(
                        rset.getInt("NOTICE_NO"),
                        rset.getString("NOTICE_TITLE"),
                        rset.getString("USER_ID"),
                        rset.getInt("COUNT"),
                        rset.getDate("CREATE_DATE")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    public int insertNotice(Connection conn, Notice n) {
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertNotice");

        int result = 0;

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, n.getNoticeTitle());
            pstmt.setString(2, n.getNoticeContent());
            pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter()));

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int increaseCount(Connection conn, int noticeNo) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = prop.getProperty("increaseCount");

        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticeNo);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public Notice selectNotice(Connection conn, int noticeNo) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectNotice");
        Notice n = null;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, noticeNo);

            rset = pstmt.executeQuery();
            if (rset.next()) {
                n = new Notice(rset.getInt("NOTICE_NO"),
                        rset.getString("NOTICE_TITLE"),
                        rset.getString("NOTICE_CONTENT"),
                        rset.getString("USER_ID"),
                        rset.getDate("CREATE_DATE"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }

        return n;
    }

    public int updateNotice(Connection conn, Notice n) {

//        UPDATE문 -> int(처리된 행의 갯수)

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateNotice");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, n.getNoticeTitle());
            pstmt.setString(2, n.getNoticeContent());
            pstmt.setInt(3, n.getNoticeNo());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int deleteNotice(Connection conn, int noticeNo) {

//      UPDATE문 -> int (처리된 행의 갯수)

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteNotice");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticeNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

}

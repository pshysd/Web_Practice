package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class BoardDao {

    private Properties prop = new Properties();

    public BoardDao() {
        String fileName = BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int selectListCount(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectListCount");
        int listCount = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                listCount = rset.getInt("COUNT");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return listCount;
    }

    public ArrayList<Board> selectList(Connection conn, PageInfo pi) {

        ArrayList<Board> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectList");

        try {
            pstmt = conn.prepareStatement(sql);

//            boardLimit가 10이라는 가정 하에
//            currentPage = 1 -> 시작값 1, 끝값 10
//            currentPage = 2 -> 시작값 11, 끝값 20
//            currentPage = 3 -> 시작값 21, 끝값 30

//            시작값 = (currentPage - 1) * boardLimit + 1
//            끝값 = 시작값 +  boardLimit - 1
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;

            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                list.add(new Board(rset.getInt("BOARD_NO"),
                        rset.getString("CATEGORY_NAME"),
                        rset.getString("BOARD_TITLE"),
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

    public ArrayList<Category> selectCategoryList(Connection conn) {
        String sql = prop.getProperty("selectCategoryList");
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<Category> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                list.add(new Category(rset.getInt("CATEGORY_NO"),
                        rset.getString("CATEGORY_NAME")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    public int insertBoard(Connection conn, Board b) {

//        INSERT문 -> int (처리된 행의 갯수)

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertBoard");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(b.getCategory()));
            pstmt.setString(2, b.getBoardTitle());
            pstmt.setString(3, b.getBoardContent());
            pstmt.setInt(4, Integer.parseInt(b.getBoardWriter()));

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertAttachment(Connection conn, Attachment at) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = prop.getProperty("insertAttachment");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, at.getOriginName());
            pstmt.setString(2, at.getChangeName());
            pstmt.setString(3, at.getFilePath());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }
}
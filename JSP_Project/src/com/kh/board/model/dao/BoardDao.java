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
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;

import oracle.net.aso.p;

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

    public int increaseCount(Connection conn, int boardNo) {

//        UPDATE문 -> int(처리된 행의 갯수)
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = prop.getProperty("increaseCount");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public Board selectBoard(Connection conn, int boardNo) {
        Board b = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectBoard");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                b = new Board(rset.getInt("BOARD_NO"),
                        rset.getString("CATEGORY_NAME"),
                        rset.getString("BOARD_TITLE"),
                        rset.getString("BOARD_CONTENT"),
                        rset.getString("USER_ID"),
                        rset.getDate("CREATE_DATE"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return b;
    }

    public Attachment selectAttachment(Connection conn, int boardNo) {

//        SELECT문 -> ResultSet 객체 (한 행 조회)
        Attachment at = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAttachment");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                at = new Attachment(rset.getInt("FILE_NO"),
                        rset.getString("ORIGIN_NAME"),
                        rset.getString("CHANGE_NAME"),
                        rset.getString("FILE_PATH"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return at;
    }

    public int updateBoard(Connection conn, Board b) {

//        UPDATE문 -> int (처리된 행의 갯수)

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateBoard");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(b.getCategory()));
            pstmt.setString(2, b.getBoardTitle());
            pstmt.setString(3, b.getBoardContent());
            pstmt.setInt(4, b.getBoardNo());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int updateAttachment(Connection conn, Attachment at) {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateAttachment");
        int result = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, at.getOriginName());
            pstmt.setString(2, at.getChangeName());
            pstmt.setInt(3, at.getFileNo());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertNewAttachment(Connection conn, Attachment at) {

        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertNewAttachment");
        int result = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, at.getRefNo());
            pstmt.setString(2, at.getOriginName());
            pstmt.setString(3, at.getChangeName());
            pstmt.setString(4, at.getFilePath());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertThumbnailBoard(Connection conn, Board b) {

        // INSERT문 -> int (처리된 행의 갯수)

        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertThumbnailBoard");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b.getBoardTitle());
            pstmt.setString(2, b.getBoardContent());
            pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {

        int result = 1; // 한번이라도 실패하면 0 나오고 실패처리 해야되니까 1로 둔다
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertAttachmentList");

//        DAO 메소드 하나로 동일한 쿼리문을 여러번 실행해야 함
        try {
            for (Attachment at : list) {
                pstmt = conn.prepareStatement(sql);

//                쿼리문 완성시키기
                pstmt.setString(1, at.getOriginName());
                pstmt.setString(2, at.getChangeName());
                pstmt.setString(3, at.getFilePath());
                pstmt.setInt(4, at.getFileLevel());
//                쿼리문 실행 후 결과 받기
                result *= pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }

    public ArrayList<Board> selectThumbnailList(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectThumbnailList");
        ArrayList<Board> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                Board b = new Board();
                b.setBoardNo(rset.getInt("BOARD_NO"));
                b.setBoardTitle(rset.getString("BOARD_TITLE"));
                b.setCount(rset.getInt("COUNT"));
                b.setTitleImg(rset.getString("TITLEIMG"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    public ArrayList<Attachment> selectAttachmentList(Connection conn, int boardNo) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<Attachment> list = new ArrayList<>();
//        일반게시판 상세조회시 썼던 쿼리 재활용
        String sql = prop.getProperty("selectAttachment");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                Attachment at = new Attachment();
                at.setFileNo(rset.getInt("FILE_NO"));
                at.setOriginName(rset.getString("ORIGIN_NAME"));
                at.setChangeName(rset.getString("CHANGE_NAME"));
                at.setFilePath(rset.getString("FILE_PATH"));

                list.add(at);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    public ArrayList<Reply> selectReplyList(Connection conn, int boardNo) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReplyList");
        ArrayList<Reply> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                list.add(new Reply(rset.getInt("REPLY_NO"),
                        rset.getString("REPLY_CONTENT"),
                        rset.getString("USER_ID"),
                        rset.getString("CREATE_DATE")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    public int insertReply(Connection conn, Reply r) {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertReply");
        int result = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, r.getReplyContent());
            pstmt.setInt(2, r.getRefBoardNo());
            pstmt.setString(3, r.getReplyWriter());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(pstmt);
        }
        return result;
    }
}
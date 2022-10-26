package com.kh.board.model.vo;

import java.sql.Date;

public class Board {

//    필드
    private int boardNo;
    private int boardType;
    private String category;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private int count;
    private Date createDate;
    private String status;

//    생성자
    public Board() {
    }

    public Board(int boardNo, int boardType, String category, String boardTitle, String boardContent,
            String boardWriter,
            int count, Date createDate, String status) {
        this.boardNo = boardNo;
        this.boardType = boardType;
        this.category = category;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.count = count;
        this.createDate = createDate;
        this.status = status;
    }

    public Board(int boardNo, String category, String boardTitle, String boardWriter, int count, Date createDate) {
        this.boardNo = boardNo;
        this.category = category;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.count = count;
        this.createDate = createDate;
    }

    // 메소드
    public int getBoardNo() {
        return boardNo;
    }

    public int getBoardType() {
        return boardType;
    }

    public String getCategory() {
        return category;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public int getCount() {
        return count;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", category=" + category + ", boardTitle="
                + boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
                + ", createDate=" + createDate + ", status=" + status + "]";
    }
}

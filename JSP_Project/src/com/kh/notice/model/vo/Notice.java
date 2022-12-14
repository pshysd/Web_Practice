package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
    private int noticeNo;
    private String noticeTitle;
    private String noticeContent;
    private String noticeWriter; // 조회 시 작성자 아이디 값 "admin" / 작성하기 시 로그인한 회원 번호 "1"
    private int count;
    private Date createDate;
    private String status;

    public Notice() {

    }

    public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, Date createDate) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeWriter = noticeWriter;
        this.createDate = createDate;
    }

    public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, int count,
            Date createDate, String status) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeWriter = noticeWriter;
        this.count = count;
        this.createDate = createDate;
        this.status = status;
    }

//	공지사항 전체 조회용 생성자
    public Notice(int noticeNo, String noticeTitle, String noticeWriter, int count, Date createDate) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeWriter = noticeWriter;
        this.count = count;
        this.createDate = createDate;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public String getNoticeWriter() {
        return noticeWriter;
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

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public void setNoticeWriter(String noticeWriter) {
        this.noticeWriter = noticeWriter;
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
        return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
                + ", noticeWriter=" + noticeWriter + ", count=" + count + ", createDate=" + createDate + ", status="
                + status + "]";
    }

}

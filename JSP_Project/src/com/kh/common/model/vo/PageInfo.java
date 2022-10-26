package com.kh.common.model.vo;

public class PageInfo {

    // 필드
    private int listCount; // 현재 총 게시글 갯수
    private int currentPage; // 현재 요청한 페이지
    private int pageLimit; // 페이지 하단에 보여질 페이징 바의 페이지 최대 갯수
    private int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
    private int maxPage; // 가장 마지막 페이지 수
    private int startPage; // 페이지 하단에 보여질 페이징 바의 시작 수
    private int endPage; // 페이지 하단에 보여질 페이징 바의 끝 수

    // 생성자
    public PageInfo() {
    }

    public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit, int maxPage, int startPage,
            int endPage) {
        super();
        this.listCount = listCount;
        this.currentPage = currentPage;
        this.pageLimit = pageLimit;
        this.boardLimit = boardLimit;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
    }

    // 메소드

    public int getListCount() {
        return listCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public int getBoardLimit() {
        return boardLimit;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public void setBoardLimit(int boardLimit) {
        this.boardLimit = boardLimit;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    @Override
    public String toString() {
        return "PageInfo [listCount=" + listCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
                + ", boardLimit=" + boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
                + endPage + "]";
    }

}

package com.kh.member.model.service;

import java.sql.Connection;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

    // 로그인 요청 서비스
    public Member loginMember(Member m) {

        // 1) Connection 객체 생성
        Connection conn = getConnection();

        // 2) 요청시 전달값과 만들어진 Connection 객체를 DAO 의 메소드한테 전달 (호출)
        Member loginUser = new MemberDao().loginMember(conn, m);

        // 3) INSERT, UPDATE, DELETE 문이라면 commit / rollback 처리

        // 4) Connection 객체 반납
        close(conn);

        // 5) 결과 리턴
        return loginUser;
    }

    // 회원가입용 서비스
    public int insertMember(Member m) {

        // 1) Connection 객체 생성
        Connection conn = getConnection();

        // 2) 전달값과 만들어진 Connection 객체를 DAO 에 전달하면서 요청
        int result = new MemberDao().insertMember(conn, m);

        // 3) 트랜잭션 처리
        if (result > 0) { // 성공
            commit(conn);
        } else { // 실패
            rollback(conn);
        }

        // 4) Connection 객체 반납
        close(conn);

        // 5) 결과 반환
        return result;
    }

    // 회원 정보 수정용 서비스
    public Member updateMember(Member m) {

        // 1) Connection 객체 생성
        Connection conn = getConnection();

        // 2) 만들어진 Connection 과 전달값을 DAO 로 넘기면서 요청
        int result = new MemberDao().updateMember(conn, m);

        Member updateMem = null;

        // 3) 트랜잭션 처리
        if (result > 0) { // 회원정보 수정 성공 => commit
            commit(conn);

            // 갱신된 회원 객체를 다시 조회해오기
            updateMem = new MemberDao().selectMember(conn, m.getUserId());
        } else { // 회원정보 수정 실패 => rollback
            rollback(conn);
        }

        // 4) Connection 객체 반납
        close(conn);

        // 5) 결과 리턴
        return updateMem; // 성공 : 갱신된 회원의 정보, 실패 : null
    }

    // 비밀번호 변경용 서비스
    public Member updatePwdMember(Member m, String updatePwd) {

        // 1) Connection 객체 생성
        Connection conn = getConnection();

        // 2) 만들어진 Connection 객체와 전달값을 DAO 로 넘겨서 요청 처리 후 결과 받기
        int result = new MemberDao().updatePwdMember(conn, m, updatePwd);

        Member updateMem = null;

        // 3) 결과에 따른 트랜잭션 처리
        if (result > 0) { // 성공 => commit
            commit(conn);

            // 갱신된 회원의 정보 다시 조회하기
            updateMem = new MemberDao().selectMember(conn, m.getUserId());
        } else { // 실패 => rollback
            rollback(conn);
        }

        // 4) Connection 객체 반납
        close(conn);

        // 5) 결과 반환
        return updateMem; // 성공 : 해당 갱신된 회원의 정보, 실패 : null
    }

    public int deleteMember(Member m) {

        Connection conn = getConnection();

        int result = new MemberDao().deleteMember(conn, m);

        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int idCheck(String checkId) {
        Connection conn = getConnection();

        int count = new MemberDao().idCheck(conn, checkId);

        close(conn);

        return count;
    }

}

package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, Member m) {
		
		// SELECT 문 => ResultSet 객체 (unique 제약조건에 의해 많아봤자 한건만 조회됨) => Member 객체
		
		// 1) 필요한 변수들 먼저 셋팅
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			// 2) 쿼리문 실행에 필요한 PreparedStatement 객체를 생성하기
			pstmt = conn.prepareStatement(sql);
			
			// 3_1) 미완성된 쿼리문일 경우 완성시키기
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			// 3_2) 궈리문 실행 후 결과받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 커서를 움직여가며 값 뽑아내기
			if(rset.next()) {
				
				loginUser = new Member(rset.getInt("USER_NO"), 
									   rset.getString("USER_ID"), 
									   rset.getString("USER_PWD"),
									   rset.getString("USER_NAME"), 
									   rset.getString("PHONE"), 
									   rset.getString("EMAIL"), 
									   rset.getString("ADDRESS"),
									   rset.getString("INTEREST"),
									   rset.getDate("ENROLL_DATE"), 
									   rset.getDate("MODIFY_DATE"),
									   rset.getString("STATUS"));
			}
			
			// 이 시점 기준으로
			// 만약 일치하는 회원을 찾았다면 loginUser 에는 해당 회원의 정보가 다 담겨있을 것
			// 만약 일치하는 회원을 못찾았다면 loginUser 에는 null 값이 들어있을 것
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5) 자원 반납 (생성된 순서의 역순)
			// pstmt -> rset 순서로 생성됬었음
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 6) 결과 리턴
		return loginUser;
	}
	
	public int insertMember(Connection conn, Member m) {
		
		// INSERT 문 => 처리된 행의 갯수
		
		// 1) 필요한 변수 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			// 2) pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 3_1) 미완성 쿼리문 완성
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			// 3_2) 쿼리문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4) 자원 반납 (생성된 순서의 역순으로)
			JDBCTemplate.close(pstmt);
		}
		
		// 5) 결과 반환
		return result; // 처리된 행의 갯수 (성공 : 1, 실패 : 0)
	}
	
	public int updateMember(Connection conn, Member m) {
		
		// UPDATE 문 => int (처리된 행의 갯수)
		
		// 1) 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			// 2) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 3_1) 미완성된 쿼리문을 완성시키기
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			// 3_2) 쿼리문 실행후 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4) 자원 반납 (생성 순서의 역순)
			JDBCTemplate.close(pstmt);
		}
		
		// 5) 결과 리턴
		return result; // 처리된 행의 갯수 (성공 : 1, 실패 : 0)
	}
	
	public Member selectMember(Connection conn, String userId) {
		
		// SELECT 문 => ResultSet 객체 (unique 제약조건에 의해 많아봤자 1건 조회) => Member 객체
		
		// 1) 필요한 변수들 먼저 셋팅
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			// 2) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 3_1) 미완성된 쿼리문 완성시키기
			pstmt.setString(1, userId);
			
			// 3_2) 쿼리문 실행후 결과받기
			rset = pstmt.executeQuery();
			
			// 4) rset 으로부터 값을 뽑아서 Member 객체에 담기
			if(rset.next()) {
				
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 5) 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 6) 결과 리턴
		return m;
	}
	
	public int updatePwdMember(Connection conn, Member m, String updatePwd) {
		
		// UPDATE 문 => int (처리된 행의 갯수)
		
		// 1) 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			// 2) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 3_1) 미완성된 쿼리문 완성시키기
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, m.getUserPwd());
			
			// 3_2) 실행 후 결과받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 4) 자원 반납
			JDBCTemplate.close(pstmt);
		}
		
		// 5) 결과 리턴
		return result; // 성공 : 1, 실패 : 0
	}
	
	
	
	
	

}

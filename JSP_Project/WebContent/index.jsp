<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		[ CRUD ]
		웹사이트 입장에서 기본적으로 갖춰야 할 데이터 처리 기능들을 의미함 (데이터 추가, 조회, 수정, 삭제)
		C : Create(생성) 의 약자 => INSERT 구문 (데이터를 생성, 추가의 의미)
		R : Read(읽기) 의 약자 => SELECT 구문 (데이터를 조회의 의미)
		U : Update(갱신) 의 약자 => UPDATE 구문 (데이터를 수정의 의미)
		D : Delete(삭제) 의 약자 => DELETE 구문 (데이터를 삭제, 제거의 의미)
		
		   * 회원서비스		| 	C (INSERT) 	| 	R (SELECT) 	| 	U (UPDATE) 	| 	D (DELETE)
		======================================================================================
		           로그인		    |				|		O		|				|
		          회원가입			|	   O		|			    |				|
		  [아이디 중복확인]		|			    |		O		|				|
		         마이페이지		|				|	    O		|				|
		        회원정보변경		|				|				|		O		|
		          회원탈퇴			|				|				|  O (문맥상 DELETE 는 맞지만 실제 구현은 UPDATE 로)
		          
		   * 공지사항서비스 - 공지사항리스트조회(R) / 공지사항상세조회(R) /
		   				    공지사항작성(C) / 공지사항수정(U) / 공지사항삭제(U-D)
		   				    
		   * 일반게시판서비스 - 게시판리스트조회(R)-페이징처리 / 게시판상세조회(R) /
		   					게시판작성(C)-첨부파일1개업로드 / 게시판수정(U) / 게시판삭제(U-D) / 
		   					[댓글리스트조회(R) / 댓글작성(C)]
		  
		   * 사진게시판서비스 - 게시판리스트조회(R)-썸네일 / 게시판상세조회(R) / 
							게시판작성(C)-다중첨부파일업로드
	-->
	
	<!-- 메인페이지의 상단에 항상 menubar.jsp 가 존재하도록 include -->
	<%@ include file="views/common/menubar.jsp" %>

</body>
</html>
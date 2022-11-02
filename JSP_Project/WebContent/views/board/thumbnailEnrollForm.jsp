<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer {
        background-color: black;
        color:white;
        width: 1000px;
        height: 700px;
        margin: auto;
        margin-top: 50px;
    }

    #enroll-form>table{
        border:1px solid white;
    }

    #enroll-form input, #enroll-form textarea {
        width: 100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
<div class="outer">
    <br>
    <h2 align="center">사진게시판 작성하기</h2>
    <br>

    <form action="<%=contextPath%>/insert.th" id="enroll-form" method="post" enctype="multipart/form-data">
	<input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
        <table align="center">
            <tr>
                <th width="100">제목</th>
                <td colspan="3"><input type="text" name="title" id="" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3"><textarea name="content" rows="5" style="resize: none;" required></textarea></td>
            </tr>
            <tr> <!-- 이미지 미리보기 영역 -->
                <th>대표이미지</th>
                <td colspan="3">
                    <img id="titleImg" width="250" height="170">
                </td>
            </tr>
            <tr>
                <th>상세이미지</th>
                <td><img id="contentImg1" width="150" height="120"></td>
                <td><img id="contentImg2" width="150" height="120"></td>
                <td><img id="contentImg3" width="150" height="120"></td>
            </tr>
        </table>

        <!--
            img는 단순히 사진을 보여주는 용도
            원하는 사진 파일을 서버로 보내려면 input type="file"이 필요함 (form 내부)
        -->
        <div id="file-area"">
            <input type="file" id="file1" onchange="loadImg(this, 1);" name="file1" required> <!-- 대표이미지 업로드용 -->
            <input type="file" id="file2" onchange="loadImg(this, 2);" name="file2"> <!-- 상세이미지 업로드용 -->
            <input type="file" id="file3" onchange="loadImg(this, 3);" name="file3">
            <input type="file" id="file4" onchange="loadImg(this, 4);" name="file4">
            <!-- onchange : input 태그의 내용물이 변경될 시 발생하는 이벤트 속성 (change 이벤트) -->
            <!-- loadImg() : 내가 직접 만든 선언적 함수, 매개변수 this : 이벤트 당한 요소, 1 : input 태그의 위치값  -->
        </div>
        <br><br>

        <script>

            $(() => {
                $('#file-area').hide();
                $('#titleImg').click(() => {
                    $('#file1').click();
                });
                $('#contentImg1').click(() => {
                    $('#file2').click();
                });
                $('#contentImg2').click(() => {
                    $('#file3').click();
                });
                $('#contentImg3').click(() => {
                    $('#file4').click();
                });
            })

            function loadImg(inputFile, num) {
                // inputFile : 현재 변화가 생긴 input type="file" 요소 객체
                // num : 몇번째 input 요소인지 확인 후 해당 그 영역에 미리보기 하기 위한 변수

                // input type="file" 요소 객체는 내부적으로 files 라는 속성을 가지고 있음
                // -> 현재 이 input 태그로 선택된 파일들의 정보를 배열 형식으로 가지고 있음
                // console.log(inputFile.files.length);
                // 파일 선택 시 1, 파일 선택 취소 시 0이 출력됨
                // -> 즉, 파일의 존재 유무를 알 수 있다.

                if(inputFile.files.length == 1){ // 선택된 파일이 있을 경우
                    // 선택된 파일을 읽어들여서 그 영역에 맞는 곳에 미리보기 기능 추가

                    // 파일을 읽어들일 FileReader 객체 생성
                    const reader = new FileReader();

                    // 파일을 읽어들이는 메소드 속성을 호출
                    // -> 어느 파일을 읽어들일건지 그 파일의 정보 자체를 매개변수로 제시해야 함
                    // -> inputFile.files 라는 배열의 0번째 인덱스에 파일 정보가 담겨있음

                    reader.readAsDataURL(inputFile.files[0]);
                    // -> 해당 파일을 읽어들이는 순간
                    // 그 파일만의 고유한 URL 주소가 하나 부여됨
                    // -> 이 URL 주소를 각 img 태그의 src 속성으로 부여

                    // 파일 읽기가 완료되었을 때 실행할 함수를 정의
                    reader.onload = (e) => {
                        // e : 현재 발생한 이벤트의 정보 (이벤트 객체)
                        // e.target : 현재 이벤트가 발생된 요소 (이벤트를 당한 요소 객체)

                        // e.target == reader == function()의 this
                        // $('#titleImg').attr("src", e.currentTarget.result)

                        // 각 영역에 맞춰서 이미지 미리보기
                        switch(num){
                            case 1:
                                $('#titleImg').attr("src", e.currentTarget.result);
                                break;
                            case 2:
                                $('#contentImg1').attr("src", e.currentTarget.result);
                                break;
                            case 3:
                                $('#contentImg2').attr("src", e.currentTarget.result);
                                break;
                            case 4:
                                $('#contentImg3').attr("src", e.currentTarget.result);
                                break;
                        }
                    }
                }else{ // 선택된 파일이 사라졌을 경우
                // 미리보기 이미지를 사라지게 하기 -> src 속성에 null
                switch(num){
                    case 1:
                        $('#titleImg').attr("src", null);
                        break;
                    case 2:
                        $('#contentImg1').attr("src", null);
                        break;
                    case 3:
                        $('#contentImg2').attr("src", null);
                        break;
                    case 4:
                        $('#contentImg3').attr("src", null);
                        break;
                    }
                }
            }
        </script>
        <div align="center">
            <button type="submit">등록하기</button>
        </div>
    </form>
</div>
</body>
</html>
package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 인터페이스 구현 -> 반드시 미완성된 메소드를 구현해서 써야함 (오버라이딩)
public class MyFileRenamePolicy implements FileRenamePolicy {

//    기존의 파일을 전달받아서 파일명 수정작업 후에 수정된 파일 자체를 리턴
    @Override
    public File rename(File originFile) {

//        원본파일명 ("aaa.jpg")
        String originName = originFile.getName();

//        수정파일명
//        -> 파일업로드시간 (연월일시분초) + 5자리랜덤값(10000 ~ 99999)

//        확장자 : 원본파일의 확장자 그대로

//        원본명  ----->   수정명
//        aaa.jpg ----->    20221020163855xxxxx.jpg

//        1. 파일이 업로드된 시간 (연월일시분초)
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

//        2. 5자리 랜덤값
        int ranNum = (int) (Math.random() * 90000) + 10000;

//        3. 원본파일 확장자
//        -> 파일명 중간에 .이 들어가는 경우도 있기 때문에
//        원본 파일명에서 가장 맨 마지막에 나오는 . 기준으로 파일명과 확장자를 나눈다.
        String ext = originName.substring(originName.lastIndexOf("."));

//        4. 결합
        String changeName = currentTime + ranNum + ext;

//        원본파일(originFile)을 수정된 파일명으로 적용시켜서 파일 객체로 변환

        return new File(originFile.getParent(), changeName);
    }

}

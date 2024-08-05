<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용 공고 수정</title>
</head>
<body>
    <h1>채용 공고 수정</h1>
    
    <form action="/jobBoard/update" method="POST">
        <input type="hidden" name="NOTICE_ID" value="${map.NOTICE_ID}">
        
        <label for="TITLE">제목</label>
        <input type="text" id="TITLE" name="TITLE" value="${map.TITLE}" required>
        
        <label for="COMPANY">회사</label>
        <input type="text" id="COMPANY" name="COMPANY" value="${map.COMPANY}" required>
        
        <label for="COUNTRY">국가</label>
        <input type="text" id="COUNTRY" name="COUNTRY" value="${map.COUNTRY}" required>
        
        <label for="REGION">지역</label>
        <input type="text" id="REGION" name="REGION" value="${map.REGION}" required>
        
        <label for="POSITION">포지션</label>
        <input type="text" id="POSITION" name="POSITION" value="${map.POSITION}" required>
        
        <label for="EXPERIENCE">경력</label>
        <input type="text" id="EXPERIENCE" name="EXPERIENCE" value="${map.EXPERIENCE}" required>
        
        <label for="COMP">보상</label>
        <input type="number" id="COMP" name="COMP" value="${map.COMP}" required>
        
        <label for="SKILL">기술</label>
        <input type="text" id="SKILL" name="SKILL" value="${map.SKILL}" required>
        
        <label for="DETAIL">상세 설명</label>
        <textarea id="DETAIL" name="DETAIL" required>${map.DETAIL}</textarea>
        
        <input type="submit" value="수정">
    </form>
</body>
</html>
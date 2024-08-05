<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용 공고 등록</title>
</head>
<body>
    <h1>채용 공고 등록</h1>
    
    <form action="/jobBoard/insert" method="POST">
        <label for="TITLE">제목</label>
        <input type="text" id="TITLE" name="TITLE" required>
        
        <label for="COMPANY">회사</label>
        <input type="text" id="COMPANY" name="COMPANY" required>
        
        <label for="COUNTRY">국가</label>
        <input type="text" id="COUNTRY" name="COUNTRY" required>
        
        <label for="REGION">지역</label>
        <input type="text" id="REGION" name="REGION" required>
        
        <label for="POSITION">포지션</label>
        <input type="text" id="POSITION" name="POSITION" required>
        
        <label for="EXPERIENCE">경력</label>
        <input type="text" id="EXPERIENCE" name="EXPERIENCE" required>
        
        <label for="COMP">보상</label>
        <input type="number" id="COMP" name="COMP" required>
        
        <label for="SKILL">기술</label>
        <input type="text" id="SKILL" name="SKILL" required>
        
        <label for="DETAIL">상세 설명</label>
        <textarea id="DETAIL" name="DETAIL" required></textarea>
        
        <input type="submit" value="등록">
    </form>
</body>
</html>
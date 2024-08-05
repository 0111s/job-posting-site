<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용 공고 리스트</title>
</head>
<body>
    <h1>채용 공고 리스트</h1>
    
    <c:if test="${not empty success}">
        <div style="color: green; margin-bottom: 10px;">${success}</div>
    </c:if>
    <c:if test="${not empty info}">
        <div style="color: blue; margin-bottom: 10px;">${info}</div>
    </c:if>
    <c:if test="${not empty warning}">
        <div style="color: red; margin-bottom: 10px;">${warning}</div>
    </c:if>
    
    <form action="/jobBoard/list/search" method="GET" class="search-form">
	    <select name="category">
	        <option value="">전체</option>
	        <option value="TITLE" ${category == 'TITLE' ? 'selected' : ''}>제목</option>
	        <option value="COMPANY" ${category == 'COMPANY' ? 'selected' : ''}>회사</option>
	        <option value="COUNTRY" ${category == 'COUNTRY' ? 'selected' : ''}>국가</option>
	        <option value="POSITION" ${category == 'POSITION' ? 'selected' : ''}>포지션</option>
	        <option value="EXPERIENCE" ${category == 'EXPERIENCE' ? 'selected' : ''}>경력</option>
	        <option value="SKILL" ${category == 'SKILL' ? 'selected' : ''}>기술</option>
	    </select>
	    <input type="text" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요">
	    <button type="submit">검색</button>
	</form>
    
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>회사</th>
                <th>국가</th>
                <th>지역</th>
                <th>포지션</th>
                <th>경력</th>
                <th>보상</th>
                <th>기술</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="job" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td><a href="/jobBoard/detail/${job.NOTICE_ID}">${job.TITLE}</a></td>
                    <td>${job.COMPANY}</td>
                    <td>${job.COUNTRY}</td>
                    <td>${job.REGION}</td>
                    <td>${job.POSITION}</td>
                    <td>${job.EXPERIENCE}</td>
                    <td><fmt:formatNumber value="${job.COMP}" type="currency" currencySymbol="₩" /></td>
                    <td>${job.SKILL}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <c:if test="${empty list}">
        <p>등록된 채용 공고가 없습니다.</p>
    </c:if>
    
    <div style="margin-top: 20px;">
        <a href="/jobBoard/insertForm" class="btn">공고 등록</a>
    </div>
    
    <p>총 공고 수: <c:out value="${TOTAL}" default="0"/></p>

    <script>
        // 필요한 경우 여기에 JavaScript 코드를 추가할 수 있습니다.
    </script>
</body>
</html>
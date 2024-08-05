<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용 공고 검색 결과</title>
</head>
<body>
    <h1>채용 공고 검색 결과</h1>
    
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
    
    <c:if test="${not empty boardSearchList}">
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
                <c:forEach items="${boardSearchList}" var="job" varStatus="status">
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
    </c:if>
    
    <c:if test="${empty boardSearchList}">
        <p class="no-results">검색 결과가 없습니다.</p>
    </c:if>
    
    <div style="margin-top: 20px;">
        <a href="/jobBoard/list" class="btn">전체 리스트</a>
    </div>
</body>
</html>
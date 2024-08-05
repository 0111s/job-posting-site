<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${map.TITLE} - 채용 공고 상세</title>
</head>
<body>
    <div class="container">
        <h1>${map.TITLE}</h1>
        
        <table class="info-table">
            <tr>
                <th>회사</th>
                <td>${map.COMPANY}</td>
            </tr>
            <tr>
                <th>국가</th>
                <td>${map.COUNTRY}</td>
            </tr>
            <tr>
                <th>지역</th>
                <td>${map.REGION}</td>
            </tr>
            <tr>
                <th>포지션</th>
                <td>${map.POSITION}</td>
            </tr>
            <tr>
                <th>경력</th>
                <td>${map.EXPERIENCE}</td>
            </tr>
            <tr>
                <th>보상</th>
                <td><fmt:formatNumber value="${map.COMP}" type="currency" currencySymbol="₩" /></td>
            </tr>
            <tr>
                <th>기술</th>
                <td>${map.SKILL}</td>
            </tr>
        </table>
        
        <h2>상세 설명</h2>
        <p>${map.DETAIL}</p>
        
        <div class="other-jobs">
            <h2>이 회사의 다른 채용 공고</h2>
            <c:choose>
                <c:when test="${not empty listByCompany}">
                    <ul>
                        <c:forEach items="${listByCompany}" var="job">
                            <li>
                                <a href="/jobBoard/detail/${job.NOTICE_ID}">
                                    <strong>제목:</strong> ${job.TITLE}<br>
                                    <strong>포지션:</strong> ${job.POSITION}<br>
                                    <strong>경력:</strong> ${job.EXPERIENCE}<br>
                                    <strong>기술:</strong> ${job.SKILL}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>이 회사의 다른 채용 공고가 없습니다.</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        <div style="margin-top: 20px;">
            <a href="/jobBoard/updateForm?NOTICE_ID=${map.NOTICE_ID}" class="btn edit">수정</a>
            <a href="#" onclick="deleteJob()" class="btn delete">삭제</a>
            <a href="/jobBoard/list" class="btn">목록으로</a>
        </div>
    </div>

    <script>
        function deleteJob() {
            if (confirm('정말로 이 공고를 삭제하시겠습니까?')) {
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = '/jobBoard/delete';
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'NOTICE_ID';
                input.value = '${map.NOTICE_ID}';
                form.appendChild(input);
                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
</body>
</html>
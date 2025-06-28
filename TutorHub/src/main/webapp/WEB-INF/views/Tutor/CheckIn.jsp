<%-- 
    Document   : CheckIn
    Created on : Jun 26, 2025, 8:23:53 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card">
    <div class="card-header">
        <h4 class="card-title">Danh sách các khóa học đang hoạt động</h4>

        <form action="${pageContext.request.contextPath}/tutor/CheckIn" method="GET" class="mt-3">
            <div class="input-group" style="max-width: 400px;">
                <input type="text" class="form-control" name="subjectQuery" 
                       placeholder="Tìm kiếm theo tên môn học..." value="${subjectQuery}">
                <button class="btn btn-primary" type="submit">
                    <i class="bi bi-search"></i>
                </button>
            </div>
        </form>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-hover mb-0">
                <thead>
                    <tr>
                        <th>TÊN KHÓA HỌC</th>
                        <th>TÊN MÔN HỌC</th>
                        <th class="text-center">THAO TÁC</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- 
                        Phần này sẽ được điền động bằng JSTL <c:forEach> 
                        dựa trên kết quả tìm kiếm từ backend.
                    --%>
                    <c:forEach var="course" items="${courseList}">
                        <tr>
                            <td class="text-bold-500">${course.getTitle()}</td>
                            <td>${course.getSu_Name()}</td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/tutor/CheckIn/${course.getSessionId()}" class="btn btn-primary btn-sm">
                                    <i class="bi bi-card-checklist me-1"></i> Điểm danh
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <%-- Hiển thị thông báo nếu không có khóa học nào --%>
                    <c:if test="${empty courseList}">
                        <tr>
                            <td colspan="3" class="text-center p-4">Không có khóa học nào phù hợp với tìm kiếm của bạn.</td>
                        </tr>
                    </c:if>

                </tbody>
            </table>
        </div>
    </div>
</div>

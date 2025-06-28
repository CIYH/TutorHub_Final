<%-- 
    Document   : DanhSachHocSinh
    Created on : Jun 28, 2025, 11:58:29 AM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content">
    <section class="section">
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h4 class="card-title mb-0">Danh sách học viên</h4>
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="exportButton" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-download me-2"></i> Xuất File
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="exportButton">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/tutor/export/excel"><i class="bi bi-file-earmark-csv me-2"></i>Xuất Excel</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/tutor/export/csv"><i class="bi bi-file-earmark-csv me-2"></i>Xuất CSV</a></li>
                    </ul>
                </div>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                            <tr>
                                <th>HỌC VIÊN</th>
                                <th>KHÓA HỌC HIỆN TẠI</th>
                                <th>DỰ KIẾN HOÀN TẤT</th>
                                <th class="text-center">CÓ MẶT</th>
                                <th class="text-center">MUỘN</th>
                                <th class="text-center">VẮNG</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${studentList}">
                                <tr>
                                    <td>${student.getP_name()}</td>
                                    <td>${student.getTitle()}</td>
                                    <td class="checkin-date-cell">
                                        <%-- Sửa lại pattern ở đây, bỏ đi phần giây (:ss) cho khớp với dữ liệu đầu vào --%>
                                        <fmt:parseDate value="${student.getSessionEnd()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" />

                                        <%-- Bước định dạng để hiển thị vẫn giữ nguyên, nó sẽ tự thêm :00 cho phần giây --%>
                                        <fmt:formatDate value="${parsedDateTime}" pattern="dd/MM/yyyy" />
                                    </td>
                                    <td class="text-center"><span class="badge bg-success">${student.getPresent()}</span></td>
                                    <td class="text-center"><span class="badge bg-warning text-dark">${student.getLate()}</span></td>
                                    <td class="text-center"><span class="badge bg-danger">${student.getAbsent()}</span></td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty studentList}">
                                <tr>
                                    <td colspan="6" class="text-center p-4">Không có học viên nào để hiển thị.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</div>

<%-- 
    Document   : DuyetBooking
    Created on : Jun 25, 2025, 2:07:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-content">
    <section class="section">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Danh sách chờ duyệt</h4>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                            <tr>
                                <th>TÊN HỌC VIÊN</th>
                                <th>TÊN KHÓA HỌC</th>
                                <th class="text-center">THAO TÁC</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${listBs}">
                                <tr>
                                    <td class="d-flex align-items-center">
                                        <div class="avatar avatar-md me-3">
                                            <img src="${pageContext.request.contextPath}/src/images/faces/3.jpg" alt="Avatar">
                                        </div>
                                        <span class="text-bold-500">${a.getP_name()}</span>
                                    </td>
                                    <td>${a.getTitle()}</td>
                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/tutor/DuyetBooking/${a.getBookingId()}" class="btn btn-info btn-sm">
                                            <i class="bi bi-eye-fill me-1"></i> Xem chi tiết
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <%-- Hiển thị thông báo nếu không có đăng kí nào --%>
                            <c:if test="${empty listBs}">
                                <tr>
                                    <td colspan="3" class="text-center p-4">Không có yêu cầu đăng kí nào đang chờ duyệt.</td>
                                </tr>
                            </c:if>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</div>

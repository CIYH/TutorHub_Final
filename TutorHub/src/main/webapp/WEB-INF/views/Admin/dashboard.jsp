<%-- File: /WEB-INF/views/admin/dashboard.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- 
    Phần này giả định được đặt trong layout chính của Flexy, 
    nơi đã có sidebar và header. 
    Nó sẽ điền nội dung vào vùng <div class="container-fluid">
--%>

<div class="container-fluid">
    <div class="row">
        <%-- Card Thống kê Gia sư --%>
        <div class="col-sm-6 col-xl-4">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex align-items-center">
                        <div>
                            <h4 class="fw-semibold mb-0">${stats.tutorCount}</h4>
                            <span class="fs-2 text-muted">Tổng số Gia sư</span>
                        </div>
                        <div class="ms-auto">
                            <span class="text-bg-primary p-3 rounded-circle">
                                <i class="ti ti-user-plus fs-6"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%-- Card Thống kê Học viên --%>
        <div class="col-sm-6 col-xl-4">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex align-items-center">
                        <div>
                            <h4 class="fw-semibold mb-0">${stats.studentCount}</h4>
                            <span class="fs-2 text-muted">Tổng số Học viên</span>
                        </div>
                        <div class="ms-auto">
                            <span class="text-bg-success p-3 rounded-circle">
                                <i class="ti ti-users fs-6"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%-- Card Thống kê Môn học --%>
        <div class="col-sm-6 col-xl-4">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex align-items-center">
                        <div>
                            <h4 class="fw-semibold mb-0">${stats.subjectCount}</h4>
                            <span class="fs-2 text-muted">Tổng số Môn học</span>
                        </div>
                        <div class="ms-auto">
                            <span class="text-bg-warning p-3 rounded-circle">
                                <i class="ti ti-book fs-6"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- Bảng điểm danh trong ngày --%>
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title fw-semibold">Điểm danh các buổi học trong ngày</h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Học viên</th>
                                    <th scope="col">Môn học</th>
                                    <th scope="col">Buổi học</th>
                                    <th scope="col">Thời gian</th>
                                    <th scope="col">Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty stats.dailyCheckIns}">
                                        <c:forEach var="item" items="${stats.dailyCheckIns}">
                                            <tr>
                                                <td>${item.studentName}</td>
                                                <td>${item.subjectName}</td>
                                                <td>${item.sessionTitle}</td>
                                                <td>${item.formattedStartTime}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${item.status == 'Present'}">
                                                            <span class="badge rounded-pill bg-success">Có mặt</span>
                                                        </c:when>
                                                        <c:when test="${item.status == 'Absent'}">
                                                            <span class="badge rounded-pill bg-danger">Vắng</span>
                                                        </c:when>
                                                        <c:when test="${item.status == 'Late'}">
                                                            <span class="badge rounded-pill bg-warning">Trễ</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge rounded-pill bg-secondary">Chưa điểm danh</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="5" class="text-center">Không có buổi học nào hôm nay.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/sidebarmenu.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/simplebar/dist/simplebar.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap5.min.js"></script>
    <!-- solar icons -->
<script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>
    </body>
</html>
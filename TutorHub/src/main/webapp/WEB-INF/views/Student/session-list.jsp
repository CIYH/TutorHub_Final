<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container my-sessions-container">

    <!-- ======================================================= -->
    <!-- ========= BỘ LỌC KẾT HỢP TÌM KIẾM VÀ DROPDOWN ========= -->
    <!-- ======================================================= -->
    <div class="filter-container mb-4">
        <form action="${pageContext.request.contextPath}my-sessions" method="get" class="filter-form">

            <div class="form-group">
                <label for="search-input" class="form-label">Tên khóa học / Gia sư</label>
                <input class="form-control" type="search" id="search-input" name="search" placeholder="Nhập từ khóa..." value="${searchTerm}">
            </div>

            <div class="form-group">
                <label for="subject-select" class="form-label">Môn học</label>
                <select name="subjectId" id="subject-select" class="form-control filter-select">
                    <option value="">-- Tất cả môn học --</option>
                    <c:if test="${not empty subjects}">
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.subId}" ${subject.subId == selectedSubjectId ? 'selected' : ''}>
                                ${subject.suName}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>

            <button class="btn btn-primary filter-button" type="submit">Lọc</button>
        </form>
    </div>

    <c:choose>
        <%-- TRƯỜNG HỢP 1: CÓ KHÓA HỌC ĐỂ HIỂN THỊ --%>
        <c:when test="${not empty mySessions}">
            <div class="row">
                <c:forEach var="session" items="${mySessions}">
                    <div class="col-12 col-md-6 col-lg-4 mb-4">
                        <%-- Thẻ <a> bao bọc toàn bộ thẻ khóa học để có thể bấm vào --%>
                        <a href="${pageContext.request.contextPath}/my-sessions/detail/${session.bookingId}" class="my-course-link">
                            <div class="my-course-card">
                                <div class="card-status ${session.bookingStatus == 'Accept' ? 'status-accept' : (session.bookingStatus == 'Decline' ? 'status-decline' : 'status-pending')}">
                                    <c:out value="${!empty session.bookingStatus ? session.bookingStatus : 'Chờ xét duyệt'}" />
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${session.courseTitle}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${session.subjectName}</h6>
                                    <p class="card-text"><strong>Gia sư:</strong> ${session.tutorName}</p>

                                    <c:if test="${session.bookingStatus == 'Accept'}">
                                        <hr>
                                        <p class="card-text mb-1"><strong>Địa điểm:</strong> ${session.location}</p>
                                        <p class="card-text">
                                            <strong>Thời gian:</strong>
                                            <fmt:formatDate value="${session.sessionStart}" pattern="HH:mm, dd/MM/yyyy" />
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <%-- TRƯỜNG HỢP 2: KHÔNG CÓ KHÓA HỌC NÀO --%>
        <c:otherwise>
            <div class="text-center p-5 border rounded-3 bg-light">
                <c:choose>
                    <c:when test="${not empty searchTerm or not empty selectedSubjectId}">
                        <h4>Không tìm thấy khóa học nào</h4>
                        <p>Không có kết quả nào phù hợp với điều kiện lọc của bạn.</p>
                        <a href="${pageContext.request.contextPath}/my-sessions" class="btn btn-secondary">Xem tất cả khóa học</a>
                    </c:when>
                    <c:otherwise>
                        <h4>Bạn chưa đăng ký khóa học nào.</h4>
                        <p>Hãy tìm một gia sư và bắt đầu ngay!</p>
                        <a href="${pageContext.request.contextPath}/findtutors" class="btn btn-primary">Tìm gia sư</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<style>
    /* CSS cho bộ lọc */
    .filter-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
    }
    .filter-form {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        align-items: flex-end;
    }
    .form-group {
        flex: 1;
        display: flex;
        flex-direction: column;
        min-width: 200px;
    }
    .form-label {
        font-size: 14px;
        font-weight: 500;
        color: #555;
        margin-bottom: 5px;
    }
    .form-control {
        padding: 10px 15px;
        font-size: 1rem;
        border: 1px solid #ced4da;
        border-radius: 8px;
    }
    .filter-select {
        background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E');
        background-repeat: no-repeat;
        background-position: right 15px top 50%;
        background-size: 10px;
    }
    .filter-button {
        padding: 10px 25px;
    }

    /* CSS cho thẻ khóa học */
    .my-sessions-container {
        padding-top: 20px;
        padding-bottom: 20px;
    }
    a.my-course-link {
        text-decoration: none;
        color: inherit;
    }
    .my-course-card {
        border: 1px solid #dee2e6;
        border-radius: .75rem;
        box-shadow: 0 .125rem .25rem rgba(0,0,0,.075);
        height: 100%;
        position: relative;
        overflow: hidden;
        transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
    }
    a.my-course-link:hover .my-course-card {
        transform: translateY(-5px);
        box-shadow: 0 .5rem 1rem rgba(0,0,0,.15);
    }
    .card-status {
        position: absolute;
        top: -1px;
        right: -1px;
        color: white;
        padding: .3rem .8rem .3rem 1.2rem;
        font-size: .8rem;
        font-weight: 600;
        border-bottom-left-radius: .75rem;
    }
    .status-pending {
        background-color: #ffc107;
        color: #000;
    }
    .status-accept {
        background-color: #198754;
    }
    .status-decline {
        background-color: #dc3545;
    }

    .my-course-card .card-body {
        padding-top: 2.5rem;
    }
    .my-course-card .card-title {
        font-weight: 600;
        color: #0056b3;
    }
</style>

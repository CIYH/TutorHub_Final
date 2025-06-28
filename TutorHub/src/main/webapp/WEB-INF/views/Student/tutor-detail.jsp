<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Giả định rằng file layout của bạn đã có link đến Bootstrap CSS & JS --%>

<div class="container tutor-detail-container">
    <%-- Hiển thị thông báo thành công hoặc thất bại từ RedirectAttributes --%>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${errorMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${not empty tutor}">
            <%-- ================================================= --%>
            <%-- ========= PHẦN 1: THÔNG TIN CƠ BẢN GIA SƯ ========= --%>
            <%-- ================================================= --%>
            <div class="tutor-detail-card">
                <div class="tutor-detail-header">
                    <div class="avatar-placeholder-large">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                    </div>
                    <div class="tutor-main-info">
                        <h1 class="detail-tutor-name">${tutor.pName}</h1>
                        <div class="detail-line">
                            <span class="detail-rating">⭐ ${!empty tutor.rating ? tutor.rating : 'Mới'}</span>
                        </div>
                        <div class="detail-line">
                            <span class="detail-item">
                                <svg class="icon" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
                                ${tutor.sessionCount} buổi học
                            </span>
                            <span class="detail-item">
                                <svg class="icon" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20v-15A2.5 2.5 0 0 0 17.5 2H6.5a2.5 2.5 0 0 0 0 5H17"></path></svg>
                                        ${!empty tutor.subjectName ? tutor.subjectName : 'Chưa có môn học'}
                            </span>
                        </div>
                        <div class="detail-line">
                            <span class="detail-item">
                                <svg class="icon" viewBox="0 0 24 24"><path fill="none" stroke="currentColor" d="M19 21l-7-5-7 5V5a2 2 0 012-2h10a2 2 0 012 2z"></path></svg>
                                Kinh nghiệm: ${tutor.experience}
                            </span>
                        </div>
                        <%-- CẬP NHẬT: Thêm Email và SĐT --%>
                        <div class="detail-line">
                            <span class="detail-item">
                                <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>
                                Email: ${tutor.email}
                            </span>
                        </div>
                        <div class="detail-line">
                            <span class="detail-item">
                                <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"><path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"></path></svg>
                                SĐT: ${tutor.phonenumber}
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <%-- ================================================= --%>
            <%-- ========= PHẦN 2: DANH SÁCH KHÓA HỌC ============== --%>
            <%-- ================================================= --%>
            <div class="col-12 mt-4">
                <h5 class="mb-3">
                    <c:choose>
                        <c:when test="${not empty sessionScope.loggedInUser}">Chọn một khóa học để đăng ký</c:when>
                        <c:otherwise>Các khóa học hiện có</c:otherwise>
                    </c:choose>
                </h5>
                <div class="row" id="course-list-container">
                    <c:forEach var="session" items="${sessions}">

                        <c:set var="isMyBooking" value="${not empty session.bookingId}" />
                        <c:set var="isCourseAvailable" value="${session.acceptedBookingsCount == 0}" />

                        <c:if test="${isMyBooking or isCourseAvailable}">
                            <div class="col-md-6 col-lg-4 mb-4">
                                <c:choose>
                                    <c:when test="${isMyBooking}">
                                        <div class="course-card-disabled">
                                            <p class="course-status ${session.bookingStatus == 'Accept' ? 'text-success' : (session.bookingStatus == 'Decline' ? 'text-danger' : 'text-warning')}">
                                                Trạng thái: 
                                                <strong>
                                                    <c:choose>
                                                        <c:when test="${session.bookingStatus == 'Accept'}">Đã duyệt</c:when>
                                                        <c:when test="${session.bookingStatus == 'Decline'}">Bị từ chối</c:when>
                                                        <c:otherwise>Chờ xét duyệt</c:otherwise>
                                                    </c:choose>
                                                </strong>
                                            </p>
                                            <h6 class="course-title">${session.title}</h6>
                                            <p class="course-description">${session.seDescription}</p>
                                            <div class="course-footer">
                                                <span class="course-fee"><fmt:formatNumber value="${session.fee}" type="currency" currencySymbol="" minFractionDigits="0" /> đ</span>
                                                <span class="course-duration">${session.duration} Tuần</span>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="course-card-selectable" data-session-id="${session.sessionId}" data-session-title="${session.title}">
                                            <h6 class="course-title">${session.title}</h6>
                                            <p class="course-description">${session.seDescription}</p>
                                            <div class="course-footer">
                                                <span class="course-fee"><fmt:formatNumber value="${session.fee}" type="currency" currencySymbol="" minFractionDigits="0" /> đ</span>
                                                <span class="course-duration">${session.duration} Tuần</span>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                
                    <div class="text-center mt-2">
                        <button type="button" id="register-button" class="btn btn-primary btn-lg">Đăng ký khóa học</button>
                        <div id="selection-error" class="text-danger mt-2" style="display: none;">Vui lòng chọn một khóa học.</div>
                    </div>
                
            </div>

            <%-- ================================================= --%>
            <%-- ========= PHẦN 3: LỊCH DẠY CỦA GIA SƯ ============== --%>
            <%-- ================================================= --%>
            <div class="col-12 mt-4">
                <h5 class="mb-3">Lịch Dạy Có Sẵn</h5>
                <div class="card shadow-sm rounded-3">
                    <div class="card-body">
                        <div class="schedule-grid">
                            <c:set var="days" value="${['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật']}" />
                            <c:forEach var="day" items="${days}">
                                <div class="day-column">
                                    <h6 class="day-header">${day}</h6>
                                    <div class="day-schedules">
                                        <c:choose>
                                            <c:when test="${not empty scheduleMap[day]}">
                                                <c:forEach var="schedule" items="${scheduleMap[day]}">
                                                    <div class="schedule-card-detail">
                                                        <div class="card-time-header">
                                                            <div class="start-time-badge">${fn:substring(schedule.startAt, 0, 5)}</div>
                                                        </div>
                                                        <p class="card-session-title">${!empty schedule.sessionTitle ? schedule.sessionTitle : 'Không có tiêu đề'}</p>
                                                        <p class="card-full-time-range">Ca học: ${fn:substring(schedule.startAt, 0, 5)} - ${fn:substring(schedule.endAt, 0, 5)}</p>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise><div class="no-schedule-placeholder"></div></c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-center">Không tìm thấy thông tin gia sư.</p>
        </c:otherwise>
    </c:choose>

    <!-- Modal đăng ký (giữ nguyên) -->
    <div class="modal fade" id="bookingModal" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/findtutors/book" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="bookingModalLabel">Xác nhận đăng ký</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p><strong>Gia sư:</strong> <span>${tutor.pName}</span></p>
                        <p><strong>Học viên:</strong> <span>${!empty studentName ? studentName : studentId}</span></p>
                        <p><strong>Khóa học:</strong> <span id="modal-session-title" class="fw-bold text-primary"></span></p>
                        <hr>
                            <div class="mb-3">
                                <label for="location" class="form-label">Địa chỉ học <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="location" name="location" placeholder="Nhập địa chỉ nhà hoặc link học online" required>
                            </div>

                            <%-- Các input ẩn để gửi dữ liệu cho Controller --%>
                            <input type="hidden" id="modal-session-id" name="sessionId">
                                <input type="hidden" name="tutorId" value="${tutor.id}">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <button type="submit" class="btn btn-success">Xác nhận đăng ký</button>
                                    </div>
                                    </form>
                                    </div>
                                    </div>
                                    </div>
                                    </div>

                                    <style>
                                        .tutor-detail-container {
                                            padding-top: 40px;
                                            padding-bottom: 40px;
                                        }
                                        .tutor-detail-card {
                                            background: #fff;
                                            padding: 30px;
                                            border-radius: 12px;
                                            box-shadow: 0 4px 15px rgba(0,0,0,0.07);
                                        }
                                        .tutor-detail-header {
                                            display: flex;
                                            gap: 25px;
                                            align-items: center;
                                        }
                                        .avatar-placeholder-large {
                                            width: 100px;
                                            height: 100px;
                                            border-radius: 50%;
                                            background-color: #e3e8f0;
                                            display: flex;
                                            align-items: center;
                                            justify-content: center;
                                            color: #5c6a82;
                                            flex-shrink: 0;
                                        }
                                        .tutor-main-info {
                                            display: flex;
                                            flex-direction: column;
                                            gap: 8px;
                                        }
                                        .detail-tutor-name {
                                            font-size: 26px;
                                            font-weight: 700;
                                            margin: 0;
                                            color: #1a2b4d;
                                        }
                                        .detail-line {
                                            display: flex;
                                            align-items: center;
                                            gap: 20px;
                                            color: #555;
                                            flex-wrap: wrap;
                                        }
                                        .detail-rating {
                                            font-size: 18px;
                                            font-weight: 500;
                                            color: #f5a623;
                                        }
                                        .detail-item {
                                            display: flex;
                                            align-items: center;
                                            gap: 8px;
                                            font-size: 16px;
                                            margin-right: 15px;
                                        }
                                        .detail-item .icon {
                                            width: 20px;
                                            height: 20px;
                                            flex-shrink: 0;
                                        }
                                        .text-center {
                                            text-align: center;
                                        }

                                        /* CSS CHO DANH SÁCH KHÓA HỌC */
                                        .course-card-selectable {
                                            background: #fff;
                                            border: 1px solid #e9ecef;
                                            border-radius: 10px;
                                            padding: 20px;
                                            height: 100%;
                                            display: flex;
                                            flex-direction: column;
                                            cursor: pointer;
                                            transition: all 0.2s ease-in-out;
                                        }
                                        .course-card-selectable:hover {
                                            transform: translateY(-3px);
                                            box-shadow: 0 6px 12px rgba(0,0,0,0.08);
                                        }
                                        .course-card-selectable.selected {
                                            border-color: #0d6efd;
                                            box-shadow: 0 0 0 3px rgba(13, 110, 253, 0.25);
                                        }
                                        .course-title {
                                            font-weight: 600;
                                            font-size: 1.1rem;
                                            color: #007bff;
                                            margin-bottom: 10px;
                                        }
                                        .course-description {
                                            font-size: 0.95rem;
                                            color: #6c757d;
                                            flex-grow: 1;
                                            margin-bottom: 15px;
                                        }
                                        .course-footer {
                                            display: flex;
                                            justify-content: space-between;
                                            align-items: center;
                                            border-top: 1px solid #e9ecef;
                                            padding-top: 10px;
                                            font-size: 0.9rem;
                                        }
                                        .course-fee {
                                            font-weight: bold;
                                            color: #198754;
                                        }
                                        .course-card-disabled {
                                            background: #f8f9fa;
                                            border: 1px solid #e9ecef;
                                            border-radius: 10px;
                                            padding: 20px;
                                            height: 100%;
                                            display: flex;
                                            flex-direction: column;
                                            opacity: 0.8;
                                        }
                                        .course-status {
                                            font-size: 0.95rem;
                                            margin-bottom: 15px;
                                            padding-bottom: 10px;
                                            border-bottom: 1px solid #eee;
                                        }
                                        .course-status strong {
                                            font-weight: 600;
                                        }

                                        /* CSS cho Lịch dạy */
                                        .schedule-grid {
                                            display: grid;
                                            grid-template-columns: repeat(7, 1fr);
                                            gap: 10px;
                                        }
                                        .day-column {
                                            padding: 0;
                                        }
                                        .day-column h6.day-header {
                                            text-align: center;
                                            padding: 8px;
                                            background-color: #f4f5f7;
                                            border-radius: 5px;
                                            margin-bottom: 12px;
                                            font-weight: 600;
                                            color: #333;
                                        }
                                        .schedule-card-detail {
                                            background-color: #f8f9fa;
                                            border: 1px solid #e9ecef;
                                            border-radius: 8px;
                                            padding: 15px;
                                            margin-bottom: 12px;
                                            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
                                        }
                                        .card-time-header {
                                            display: flex;
                                            justify-content: flex-start;
                                            align-items: center;
                                            margin-bottom: 10px;
                                        }
                                        .start-time-badge {
                                            background-color: #343a40;
                                            color: white;
                                            padding: 5px 10px;
                                            border-radius: 6px;
                                            font-weight: bold;
                                            font-size: 0.9em;
                                        }
                                        .card-session-title {
                                            font-weight: 600;
                                            color: #212529;
                                            margin-bottom: 5px;
                                            font-size: 1em;
                                            line-height: 1.4;
                                        }
                                        .card-full-time-range {
                                            font-size: 0.9em;
                                            color: #6c757d;
                                            margin: 0;
                                        }
                                        .no-schedule-placeholder {
                                            min-height: 50px;
                                        }
                                    </style>

                                    <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            const courseContainer = document.getElementById('course-list-container');
                                            const registerButton = document.getElementById('register-button');
                                            const selectionError = document.getElementById('selection-error');

                                            const bookingModalElement = document.getElementById('bookingModal');
                                            if (!bookingModalElement)
                                                return;
                                            const bookingModal = new bootstrap.Modal(bookingModalElement);

                                            let selectedSessionCard = null;

                                            if (courseContainer) {
                                                courseContainer.addEventListener('click', function (event) {
                                                    const card = event.target.closest('.course-card-selectable');
                                                    if (!card)
                                                        return;

                                                    if (card === selectedSessionCard) {
                                                        card.classList.remove('selected');
                                                        selectedSessionCard = null;
                                                    } else {
                                                        if (selectedSessionCard) {
                                                            selectedSessionCard.classList.remove('selected');
                                                        }
                                                        card.classList.add('selected');
                                                        selectedSessionCard = card;
                                                        selectionError.style.display = 'none';
                                                    }
                                                });
                                            }

                                            if (registerButton) {
                                                registerButton.addEventListener('click', function () {
                                                    if (!selectedSessionCard) {
                                                        selectionError.style.display = 'block';
                                                        return;
                                                    }

                                                    const sessionId = selectedSessionCard.getAttribute('data-session-id');
                                                    const sessionTitle = selectedSessionCard.getAttribute('data-session-title');

                                                    document.getElementById('modal-session-id').value = sessionId;
                                                    document.getElementById('modal-session-title').textContent = sessionTitle;

                                                    bookingModal.show();
                                                });
                                            }
                                        });
                                    </script>

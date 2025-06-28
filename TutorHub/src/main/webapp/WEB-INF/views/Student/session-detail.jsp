<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container my-session-detail-container">
    <c:choose>
        <c:when test="${not empty course}">
            <div class="row">
                <div class="col-lg-8">
                    <%-- THÔNG TIN KHÓA HỌC VÀ GIA SƯ --%>
                    <div class="card">
                        <div class="card-body">
                            <span class="badge rounded-pill bg-primary mb-3">${course.subjectName}</span>
                            <h4 class="card-title">${course.courseTitle}</h4>
                            <p class="card-text text-muted">${course.courseDescription}</p>
                            <hr>
                            <p><strong>Gia sư:</strong> ${course.tutorName}</p>
                            <p><strong>Địa điểm:</strong> ${course.location}</p>
                            <p><strong>Thời gian:</strong> <fmt:formatDate value="${course.sessionStart}" pattern="HH:mm, dd/MM/yyyy" /></p>
                            <p><strong>Trạng thái:</strong> 
                                <span class="fw-bold ${course.bookingStatus == 'Accept' ? 'text-success' : (course.bookingStatus == 'Decline' ? 'text-danger' : 'text-warning')}">
                                    <c:out value="${!empty course.bookingStatus ? course.bookingStatus : 'Chờ xét duyệt'}" />
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <%-- KHU VỰC CHỨC NĂNG --%>
                    
                    <%-- 1. Chức năng Đánh giá (chỉ hiện khi đã được duyệt) --%>
                   <c:if test="${course.bookingStatus == 'Accept'}">
                        <div class="card mb-4">
                            <div class="card-header">Đánh giá Gia sư</div>
                            <div class="card-body text-center">
                                <p>Chia sẻ trải nghiệm của bạn về gia sư này.</p>
                                <form action="${pageContext.request.contextPath}/my-sessions/rate" method="post">
                                    <input type="hidden" name="bookingId" value="${course.bookingId}">
                                    <input type="hidden" name="tutorId" value="${tutor.id}"> <%-- Cần truyền tutorId từ Controller --%>
                                    <div class="rating-stars mb-3">
                                        <%-- (Phần này sẽ cần JS để làm đẹp, tạm thời dùng select) --%>
                                        <select name="rating" class="form-select">
                                            <option value="5">5 sao - Rất hài lòng</option>
                                            <option value="4">4 sao - Hài lòng</option>
                                            <option value="3">3 sao - Bình thường</option>
                                            <option value="2">2 sao - Không hài lòng</option>
                                            <option value="1">1 sao - Rất tệ</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-warning">Gửi đánh giá</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                    

                    <%-- 2. Chức năng Hủy khóa học (chỉ hiện khi đang chờ duyệt) --%>
                    <c:if test="${empty course.bookingStatus}">
                        <div class="card">
                             <div class="card-header">Hủy đăng ký</div>
                            <div class="card-body">
                                <p>Bạn có chắc chắn muốn hủy đăng ký khóa học này không?</p>
                                <form action="${pageContext.request.contextPath}/my-sessions/cancel" method="post" onsubmit="return confirm('Hành động này không thể hoàn tác. Bạn có chắc chắn?');">
                                    <input type="hidden" name="bookingId" value="${course.bookingId}">
                                    <button type="submit" class="btn btn-danger w-100">Xác nhận Hủy</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-center">Không tìm thấy thông tin khóa học.</p>
        </c:otherwise>
    </c:choose>
</div>

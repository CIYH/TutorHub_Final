<%-- 
    Document   : CheckInForm
    Created on : Jun 27, 2025, 12:29:50 PM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-content">
    <section class="section">
        <div class="card">
            <div class="card-header">
                <button type="button" class="btn btn-primary" id="createCheckinBtn" data-bs-toggle="modal" data-bs-target="#createCheckinModal">
                    <i class="bi bi-plus-circle-fill me-2"></i>Tạo Phiếu Điểm Danh
                </button>
            </div>
            <div class="card-body">
                <h4 class="card-title mb-4">Lịch sử điểm danh</h4>
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                            <tr>
                                <th>THỜI GIAN ĐIỂM DANH</th>
                                <th>TÊN HỌC VIÊN</th>
                                <th>TRẠNG THÁI</th>
                                <th class="text-center">TÙY CHỈNH</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="history" items="${historyList}">
                                <tr>
                                    <td class="checkin-date-cell">
                                        <c:out value="${history.getCreateTime()}"/>
                                    </td>
                                    <td class="text-bold-500"><c:out value="${history.getP_name()}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${history.getPresentStatus() == 'Present'}"><span class="badge bg-success">Present</span></c:when>
                                            <c:when test="${history.getPresentStatus() == 'Late'}"><span class="badge bg-warning text-dark">Late</span></c:when>
                                            <c:when test="${history.getPresentStatus() == 'Absent'}"><span class="badge bg-danger">Absent</span></c:when>
                                            <c:otherwise><span class="badge bg-secondary"><c:out value="${history.getPresentStatus()}"/></span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">
                                        <c:set var="currentDateStr" value="${fn:substring(currentTime, 0, 10)}" />
                                        <c:set var="checkinDateStr" value="${fn:substring(history.getCreateTime(), 0, 10)}" />
                                        <c:if test="${currentDateStr eq checkinDateStr}">
                                            <button class="btn btn-light-secondary btn-sm edit-checkin-btn"
                                                    data-bs-toggle="modal" data-bs-target="#editCheckinModal"
                                                    data-checkin-id="${history.getCheckinId()}"
                                                    data-student-name="${history.getP_name()}"
                                                    data-current-status="${history.getPresentStatus()}">
                                                <i class="bi bi-pencil-fill"></i> Sửa
                                            </button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty historyList}">
                                <tr><td colspan="4" class="text-center p-4">Chưa có lịch sử điểm danh nào.</td></tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="mt-4">
                    <a href="${pageContext.request.contextPath}/tutor/CheckIn" class="btn btn-light-secondary">
                        <i class="bi bi-arrow-left"></i> Quay lại
                    </a>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="createCheckinModal" tabindex="-1" aria-labelledby="createCheckinModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/tutor/check-in/create" method="POST">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="createCheckinModalLabel">Điểm danh buổi học ngày <span id="current-date"></span></h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="sessionId" value="${sessionId}">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr><th>Học viên</th><th style="width: 150px;">Trạng thái</th></tr>
                            </thead>
                            <tbody>
                                <c:forEach var="student" items="${studentList}">
                                    <tr>
                                        <td class="d-flex align-items-center">
                                            <div class="avatar avatar-md me-3"><img src="${pageContext.request.contextPath}/src/images/faces/1.jpg" alt="Avatar"></div>
                                            <span class="text-bold-500"><c:out value="${student.getP_name()}"/></span>
                                        </td>
                                        <td>
                                            <select class="form-select" name="status_${student.getStudentId()}" required>
                                                <option value="Present" selected>Present</option>
                                                <option value="Absent">Absent</option>
                                                <option value="Late">Late</option>
                                            </select>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty studentList}">
                                    <tr><td colspan="2" class="text-center">Không có học viên nào trong khóa học này.</td></tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-light-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Lưu Điểm Danh</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="editCheckinModal" tabindex="-1" aria-labelledby="editCheckinModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/tutor/check-in/update" method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCheckinModalLabel">Sửa Trạng Thái</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="checkinRecordId" id="editCheckinRecordId">
                    <input type="hidden" name="sessionId" value="${sessionId}">
                    <p>Học viên: <strong id="editStudentName"></strong></p>
                    <div class="form-group">
                        <label for="editStatusSelect">Trạng thái mới:</label>
                        <select class="form-select" name="status" id="editStatusSelect">
                            <option value="Present">Present</option>
                            <option value="Absent">Absent</option>
                            <option value="Late">Late</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-light-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        
        // =================================================================================
        //  KHAI BÁO BIẾN DÙNG CHUNG - LẤY DỮ LIỆU TỪ SERVER
        // =================================================================================
        // Lấy chuỗi thời gian đầy đủ từ model attribute 'currentTime' bạn đã truyền.
        // Nếu Controller không truyền, giá trị này sẽ là chuỗi rỗng.
        const serverTimeFull = '${currentTime}'; 
        
        // Cắt chuỗi để chỉ lấy phần ngày "dd/MM/yyyy" để so sánh.
        const serverTodayStr = serverTimeFull.substring(0, 10);


        // =================================================================================
        //  PHẦN 1: Xử lý các tác vụ phụ
        // =================================================================================
        // Xử lý sự kiện khi modal "Sửa" được mở
        const editCheckinModal = document.getElementById('editCheckinModal');
        if (editCheckinModal) {
            editCheckinModal.addEventListener('show.bs.modal', function (event) {
                const button = event.relatedTarget;
                const checkinId = button.getAttribute('data-checkin-id');
                const studentName = button.getAttribute('data-student-name');
                const currentStatus = button.getAttribute('data-current-status');

                editCheckinModal.querySelector('#editStudentName').textContent = studentName;
                editCheckinModal.querySelector('#editCheckinRecordId').value = checkinId;
                editCheckinModal.querySelector('#editStatusSelect').value = currentStatus;
            });
        }
        
        // Cập nhật ngày tháng trên tiêu đề modal tạo mới (hiển thị ngày của server)
        const currentDateSpan = document.getElementById('current-date');
        if (currentDateSpan) {
            currentDateSpan.textContent = serverTodayStr;
        }

        // =================================================================================
        //  PHẦN 2: Xử lý logic chính cho nút "Tạo Phiếu Điểm Danh"
        // =================================================================================
        const createBtn = document.getElementById('createCheckinBtn');
        const createModalEl = document.getElementById('createCheckinModal');
        
        if (createBtn && createModalEl) {
            // Ngăn Bootstrap tự động mở modal
            createBtn.removeAttribute('data-bs-toggle');
            createBtn.removeAttribute('data-bs-target');
            
            const createModal = new bootstrap.Modal(createModalEl);

            createBtn.addEventListener('click', function() {
                // Kiểm tra lại serverTodayStr để đảm bảo nó không rỗng
                if (!serverTodayStr) {
                    console.error("Lỗi: serverTodayStr is empty. Vui lòng kiểm tra Controller có truyền 'currentTime' không.");
                    // Có thể hiện một thông báo lỗi khác ở đây nếu cần
                    Swal.fire('Lỗi', 'Không thể xác định ngày của server. Vui lòng thử lại.', 'error');
                    return; 
                }

                let recordForTodayExists = false;
                const dateCells = document.querySelectorAll('.checkin-date-cell');
                
                for (const cell of dateCells) {
                    const checkinDateStr = (cell.textContent || cell.innerText).trim().substring(0, 10);
                    
                    if (checkinDateStr === serverTodayStr) {
                        recordForTodayExists = true;
                        break; 
                    }
                }

                if (recordForTodayExists) {
                    Swal.fire({
                        title: 'Thông Báo',
                        text: 'Bạn đã tạo phiếu điểm danh cho ngày hôm nay!',
                        icon: 'warning',
                        confirmButtonText: 'Đã hiểu'
                    });
                } else {
                    createModal.show();
                }
            });
        }
    });
</script>


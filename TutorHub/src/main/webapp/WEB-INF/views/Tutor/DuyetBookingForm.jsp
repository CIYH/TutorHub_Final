<%-- 
    Document   : DuyetBookingForm
    Created on : Jun 25, 2025, 8:26:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-content">
    <section class="section">
        <div class="row">
            <%-- Cột hiển thị thông tin --%>
            <div class="col-12 col-lg-8">

                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Thông tin học viên</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-flex align-items-center mb-4">
                            <div class="avatar avatar-xl me-3">
                                <img src="${pageContext.request.contextPath}/src/images/faces/5.jpg" alt="Face 1">
                            </div>
                            <div>
                                <h5 class="mb-0"><c:out value="${listBs2.getP_name()}"/></h5>
                                <p class="text-muted mb-0">Học viên</p>
                            </div>
                        </div>

                        <div class="d-flex align-items-center mb-3">
                            <i class="bi bi-envelope-fill fs-5 me-3 text-primary"></i>
                            <span><c:out value="${listBs2.getEmail()}"/></span>
                        </div>
                        <div class="d-flex align-items-center mb-3">
                            <i class="bi bi-telephone-fill fs-5 me-3 text-primary"></i>
                            <span><c:out value="${listBs2.getPhonenumber()}"/></span>
                        </div>
                        <div class="d-flex align-items-center">
                            <i class="bi bi-check-circle-fill fs-5 me-3 text-primary"></i>
                            <span>Địa chỉ đăng ký: <strong><c:out value="${listBs2.getLocation()}"/></strong></span>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Thông tin khóa học đăng kí</h4>
                    </div>
                    <div class="card-body">
                        <h5><c:out value="${listBs2.getTitle()}"/></h5>
                        <p class="text-muted">Môn học: <c:out value="${listBs2.getSu_Name()}"/></p>
                        <p><strong>Mô tả:</strong> <c:out value="${listBs2.getSe_Description()}"/></p>
                    </div>
                </div>
            </div>

            <%-- Cột chứa các nút hành động --%>
            <div class="col-12 col-lg-4">
                <div class="card">
                    <div class="card-header">
                        <h4>Hành động</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-grid gap-2">
                            <button id="approve-btn" class="btn btn-success btn-lg">
                                <i class="bi bi-check-circle-fill me-2"></i>Duyệt Đăng Kí
                            </button>
                            <button id="decline-btn" class="btn btn-danger btn-lg" data-bs-toggle="modal" data-bs-target="#declineReasonModal">
                                <i class="bi bi-x-circle-fill me-2"></i>Từ Chối
                            </button>
                            <hr>
                            <a href="${pageContext.request.contextPath}/tutor/DuyetBooking" class="btn btn-light-secondary">
                                <i class="bi bi-arrow-left-circle me-2"></i>Quay Lại
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form id="approveForm" action="${pageContext.request.contextPath}/tutor/ChapNhanBooking" method="POST" style="display: none;">
            <%-- Lấy bookingId từ đối tượng đã được controller gửi sang --%>
            <input type="hidden" name="bookingId" value="${listBs2.getBookingId()}">
            <input type="hidden" name="sessionId" value="${listBs2.getSessionId()}">
            <input type="hidden" name="duration" value="${listBs2.getDuration()}">
        </form>

        <form id="declineFormForSubmit" action="${pageContext.request.contextPath}/tutor/TuChoiBooking" method="POST" style="display: none;">
            <input type="hidden" name="bookingId" value="${listBs2.getBookingId()}">
            <%-- Input này sẽ được JavaScript điền lý do từ chối vào trước khi gửi --%>
            <input type="hidden" name="declineReason" id="reasonInputForSubmit">
        </form>                        
    </section>
</div>

<div class="modal fade" id="declineReasonModal" tabindex="-1" aria-labelledby="declineReasonModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="declineReasonModalLabel">Lý do từ chối đăng kí</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="declineForm">
                    <div class="mb-3">
                        <label for="declineReasonText" class="form-label">Vui lòng nhập lý do từ chối:</label>
                        <%-- Thuộc tính 'required' để bắt buộc người dùng phải nhập --%>
                        <textarea class="form-control" id="declineReasonText" name="declineReason" rows="4" required></textarea>
                        <div class="invalid-feedback">
                            Lý do không được để trống.
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" id="confirm-decline-btn" class="btn btn-danger">Xác Nhận Từ Chối</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Lấy các nút bấm và form
        const successMessage = '<c:out value="${successMessage}"></c:out>';
        const errorMessage = '<c:out value="${errorMessage}"></c:out>';

        // Dùng setTimeout để đảm bảo trang tải xong hoàn toàn mới hiện popup
        setTimeout(function() {
            if (successMessage) {
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: successMessage,
                    confirmButtonColor: '#435ebe'
                });
            }
            if (errorMessage) {
                Swal.fire({
                    icon: 'error',
                    title: 'Đã có lỗi xảy ra!',
                    text: errorMessage,
                    confirmButtonColor: '#435ebe'
                });
            }
        }, 200);
        
        const approveBtn = document.getElementById('approve-btn');
        const confirmDeclineBtn = document.getElementById('confirm-decline-btn');
        const declineFormValidation = document.getElementById('declineForm'); // Form trong modal để validation

        // 1. Xử lý sự kiện khi nhấn nút "Duyệt Đăng Kí"
        if (approveBtn) {
            approveBtn.addEventListener('click', function (e) {
                e.preventDefault();

                Swal.fire({
                    title: 'Xác nhận duyệt?',
                    text: "Bạn có chắc chắn muốn duyệt đăng kí này cho học viên?",
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#28a745',
                    cancelButtonColor: '#6c757d',
                    confirmButtonText: 'Đồng ý duyệt!',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // THAY ĐỔI Ở ĐÂY: Gửi form ẩn để duyệt
                        document.getElementById('approveForm').submit();
                    }
                });
            });
        }

        // 2. Xử lý sự kiện khi nhấn nút "Xác Nhận Từ Chối" trong modal
        if (confirmDeclineBtn) {
            confirmDeclineBtn.addEventListener('click', function () {
                // Kiểm tra xem textarea lý do có được điền hay không
                if (!declineFormValidation.checkValidity()) {
                    declineFormValidation.classList.add('was-validated');
                } else {
                    // Nếu hợp lệ, lấy lý do và gửi form ẩn
                    const reason = document.getElementById('declineReasonText').value;

                    // THAY ĐỔI Ở ĐÂY: Điền lý do vào input ẩn và gửi form
                    document.getElementById('reasonInputForSubmit').value = reason;
                    document.getElementById('declineFormForSubmit').submit();
                }
            });
        }
    });
</script>
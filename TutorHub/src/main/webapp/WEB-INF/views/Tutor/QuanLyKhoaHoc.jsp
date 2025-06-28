<%-- 
    Document   : QuanLyKhoaHoc
    Created on : Jun 23, 2025, 4:54:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-content">
    <section class="section">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Danh sách khóa học của bạn</h4>
                <form action="${pageContext.request.contextPath}/tutor/QuanLyKhoaHoc" method="GET" class="mt-3">
                    <div class="input-group" style="max-width: 400px;">
                        <input type="text" class="form-control" name="title" placeholder="Tìm kiếm theo tên khóa học..." value="${title}">
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
                                <th>MÔN</th>
                                <th>THỜI GIAN</th>
                                <th>TRẠNG THÁI</th>
                                <th>TÙY CHỈNH</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${session}">
                                <tr>
                                    <td class="text-bold-500">${a.getTitle()}</td>
                                    <td>${a.getSu_Name()}</td>
                                    <td>${a.getDuration()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${a.getSe_Status() == 'Lock'}"><span class="badge bg-danger">Lock</span></c:when>
                                            <c:otherwise><span class="badge bg-success">Unlock</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/tutor/SuaKhoaHoc/${a.getSessionId()}" class="btn btn-primary btn-sm"><i class="bi bi-pencil-fill"></i></a>
                                            <c:if test="${a.getSe_Status() == 'Unlock'}">
                                            <a href="#" class="btn btn-danger btn-sm delete-course-btn" 
                                               data-session-id="${a.getSessionId()}" 
                                               data-course-title="${a.getTitle()}">
                                                <i class="bi bi-trash-fill"></i>
                                            </a>
                                        </c:if>    
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</div>

<form id="deleteCourseForm" action="${pageContext.request.contextPath}/tutor/XoaKhoaHoc" method="POST" style="display: none;">
    <input type="hidden" name="sessionId" id="sessionIdToDelete">
</form>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const tableBody = document.querySelector('.table tbody');

        tableBody.addEventListener('click', function (event) {
            // Tìm nút xóa được click, kể cả khi click vào icon bên trong
            const deleteButton = event.target.closest('.delete-course-btn');

            if (deleteButton) {
                event.preventDefault(); // Ngăn hành vi mặc định của thẻ <a>

                const sessionId = deleteButton.dataset.sessionId;
                const sessionTitle = deleteButton.dataset.courseTitle;

                Swal.fire({
                    title: 'Bạn chắc chắn muốn xóa?',
                    text: `Bạn sẽ không thể hoàn tác hành động này! Khóa học "${sessionTitle}" sẽ bị vô hiệu hóa.`,
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#d33',
                    cancelButtonColor: '#3085d6',
                    confirmButtonText: 'Xóa khóa học!',
                    cancelButtonText: 'Hủy bỏ'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Nếu người dùng xác nhận, điền ID vào form ẩn và submit
                        document.getElementById('sessionIdToDelete').value = sessionId;
                        document.getElementById('deleteCourseForm').submit();
                    }
                });
            }
        });
    });
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Dùng JSTL c:out để lấy giá trị từ model một cách an toàn
        // và đưa vào biến JavaScript.
        const successMessage = '<c:out value="${successMessage}"></c:out>';
        const errorMessage = '<c:out value="${errorMessage}"></c:out>';

        // Kiểm tra nếu có thông báo thành công thì hiển thị popup
        if (successMessage) {
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: successMessage,
                confirmButtonColor: '#435ebe' // Màu nút của template
            });
        }

        // Kiểm tra nếu có thông báo lỗi thì hiển thị popup
        if (errorMessage) {
            Swal.fire({
                icon: 'error',
                title: 'Đã có lỗi xảy ra!',
                text: errorMessage,
                confirmButtonColor: '#435ebe'
            });
        }
    });
</script>

<%--
    Document   : students
    Created on : Jun 27, 2025, 11:00:20 PM
    Author     : qnhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<div class="card bg-light-info shadow-none position-relative overflow-hidden">
    <div class="card-body px-4 py-3">
        <div class="row align-items-center">
            <div class="col-9">
                <h4 class="fw-semibold mb-8">Quản lý Học viên</h4>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a class="text-muted" href="<c:url value='/admin'/>">Trang chủ</a></li>
                        <li class="breadcrumb-item" aria-current="page">Danh sách Học viên</li>
                    </ol>
                </nav>
            </div>
            <div class="col-3">
                <div class="text-center mb-n5">
                    <img src="https://demos.adminmart.com/premium/bootstrap/flexy-bootstrap-admin-dashboard/dist/assets/images/breadcrumb/ChatBc.png" alt="" class="img-fluid mb-n4">
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bảng danh sách học viên -->
<div class="card w-100 position-relative overflow-hidden">
    <div class="card-body p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h5 class="card-title fw-semibold mb-0">Danh sách Học viên</h5>
            <button id="addStudentBtn" class="btn btn-primary rounded-pill">
                <i class="ti ti-plus me-2"></i>Thêm mới Học viên
            </button>
        </div>

        <div class="table-responsive">
            <table class="table border text-nowrap customize-table mb-0 align-middle" id="studentDataTable">
                <thead class="text-dark fs-4">
                    <tr>
                        <th><h6 class="fs-4 fw-semibold mb-0">Họ và Tên</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Email</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Số điện thoại</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Trạng thái</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0 text-center">Hành động</h6></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="bg-primary-subtle rounded-circle round-40 me-3">
                                        <span class="fs-6 text-primary fw-semibold">${student.pName.charAt(0)}</span>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="fs-4 fw-semibold mb-0">${student.pName}</h6>
                                        <span class="fw-normal">${student.id}</span>
                                    </div>
                                </div>
                            </td>
                            <td><p class="mb-0 fw-normal fs-4">${student.email}</p></td>
                            <td><p class="mb-0 fw-normal fs-4">${student.phonenumber}</p></td>
                            <td>
                                <c:choose>
                                    <c:when test="${student.active == 'On'}">
                                        <span class="badge bg-light-success text-success fw-semibold fs-2">Hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-light-danger text-danger fw-semibold fs-2">Đã khóa</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <div class="dropdown dropstart">
                                    <a href="#" class="text-muted" data-bs-toggle="dropdown" aria-expanded="false"><i class="ti ti-dots-vertical fs-6"></i></a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item d-flex align-items-center gap-3 view-btn" href="#" data-bs-toggle="modal" data-bs-target="#detailStudentModal" data-id="${student.id}"><i class="fs-4 ti ti-eye"></i>Xem chi tiết</a></li>
                                        <li><a class="dropdown-item d-flex align-items-center gap-3 edit-btn" href="#" data-bs-toggle="modal" data-bs-target="#studentModal" data-id="${student.id}"><i class="fs-4 ti ti-edit"></i>Sửa</a></li>
                                        <li><a class="dropdown-item d-flex align-items-center gap-3" href="<c:url value='/admin/students/delete/${student.id}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa học viên này?');"><i class="fs-4 ti ti-trash"></i>Xóa</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Modal Thêm/Sửa Học viên -->
<div class="modal fade" id="studentModal" tabindex="-1" aria-labelledby="studentModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <form id="studentForm" method="post">
        <div class="modal-header"><h5 class="modal-title" id="studentModalLabel">Thêm mới Học viên</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
        <div class="modal-body">
          <%-- Nội dung form sẽ được điền bằng JavaScript --%>
          <p class="text-center">Đang tải...</p>
        </div>
        <div class="modal-footer"><button type="button" class="btn btn-light-danger text-danger" data-bs-dismiss="modal">Đóng</button><button type="submit" class="btn btn-primary">Lưu</button></div>
      </form>
    </div>
  </div>
</div>

<!-- Modal Xem Chi tiết -->
<div class="modal fade" id="detailStudentModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
  <div class="modal-dialog"><div class="modal-content">
      <div class="modal-header"><h5 class="modal-title" id="detailModalLabel">Chi tiết Học viên</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>
      <div class="modal-body" id="detailModalBody"><p class="text-center">Đang tải...</p></div>
      <div class="modal-footer"><button type="button" class="btn btn-light-primary text-primary" data-bs-dismiss="modal">Đóng</button></div>
  </div></div>
</div>

<%--
    Lưu ý: Script này nên được đặt trong file layout chính (main.jsp)
    ngay trước thẻ đóng </body>, sau khi đã nạp jQuery và Bootstrap JS.
--%>



<script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/sidebarmenu.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/libs/simplebar/dist/simplebar.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap5.min.js"></script>
    <!-- solar icons -->
<script src="https://cdn.jsdelivr.net/npm/iconify-icon@1.0.8/dist/iconify-icon.min.js"></script>

<script>
$(document).ready(function() {

    // Khởi tạo DataTables cho bảng
    $('#studentDataTable').DataTable({
        "language": { "search": "Tìm kiếm:", "lengthMenu": "Hiển thị _MENU_ mục", "info": "Hiển thị từ _START_ đến _END_ của _TOTAL_ mục", "infoEmpty": "Không có dữ liệu", "infoFiltered": "(được lọc từ _MAX_ mục)", "paginate": { "next": "Tiếp", "previous": "Trước" }, "zeroRecords": "Không tìm thấy kết quả phù hợp" }
    });

    const studentModal = new bootstrap.Modal(document.getElementById('studentModal'));
    const detailModal = new bootstrap.Modal(document.getElementById('detailStudentModal'));
    const apiUrlBase = '<c:url value="/admin/students/api/"/>';

    // === Logic cho nút "Thêm mới" ===
    $('#addStudentBtn').on('click', function() {
        const form = $('#studentForm');
        form.attr('action', '<c:url value="/admin/students/add"/>');
        $('#studentModalLabel').text('Thêm mới Học viên');

        // Tạo form trống - Đã loại bỏ trường ID
        const formContent = `
            <div class="row">
                <div class="col-md-12 mb-3">
                    <label class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
            </div>
            <div class="mb-3"><label class="form-label">Họ và Tên</label><input type="text" class="form-control" name="pName" required></div>
            <div class="mb-3"><label class="form-label">Email</label><input type="email" class="form-control" name="email" required></div>
            <div class="mb-3"><label class="form-label">Địa chỉ</label><input type="text" class="form-control" name="address"></div>
            <div class="row">
                <div class="col-md-6 mb-3"><label class="form-label">Số điện thoại</label><input type="text" class="form-control" name="phonenumber"></div>
                <div class="col-md-6 mb-3"><label class="form-label">Giới tính</label><select name="gender" class="form-select"><option value="Nam">Nam</option><option value="Nu">Nữ</option></select></div>
            </div>`;
        form.find('.modal-body').html(formContent);
        studentModal.show();
    });

    // === Logic cho nút "Sửa" ===
    $('#studentDataTable').on('click', '.edit-btn', function() {
        const studentId = $(this).data('id');
        const form = $('#studentForm');
        const modalBody = form.find('.modal-body');

        form.attr('action', '<c:url value="/admin/students/update"/>');
        $('#studentModalLabel').text('Chỉnh sửa Học viên');
        modalBody.html('<p class="text-center">Đang tải...</p>');
        studentModal.show();

        $.ajax({
            url: apiUrlBase + studentId,
            method: 'GET',
            dataType: 'json',
            success: function(student) {

                console.log('Dữ liệu nhận được từ API:', student);
                const formContent = `
                    <input type="hidden" name="id" value="\${student.id}">
                    <div class="mb-3"><label class="form-label">Họ và Tên</label><input type="text" class="form-control" name="pName" value="\${student.pName}" required></div>
                    <div class="mb-3"><label class="form-label">Email</label><input type="email" class="form-control" name="email" value="\${student.email}" required></div>
                    <div class="mb-3"><label class="form-label">Mật khẩu mới (bỏ trống nếu không đổi)</label><input type="password" class="form-control" name="password"></div>
                    <div class="mb-3"><label class="form-label">Địa chỉ</label><input type="text" class="form-control" name="address" value="\${student.address || ''}"></div>
                    <div class="row">
                        <div class="col-md-6 mb-3"><label class="form-label">Số điện thoại</label><input type="text" class="form-control" name="phonenumber" value="\${student.phonenumber || ''}"></div>
                        <div class="col-md-6 mb-3"><label class="form-label">Giới tính</label><select name="gender" class="form-select"><option value="Nam" \${student.gender == 'Nam' ? 'selected' : ''}>Nam</option><option value="Nu" \${student.gender == 'Nu' ? 'selected' : ''}>Nữ</option></select></div>
                    </div>`;
                modalBody.html(formContent);
            },
            error: function() { modalBody.html('<p class="text-danger text-center">Lỗi tải dữ liệu.</p>'); }
        });
    });

    // === Logic cho nút "Xem chi tiết" ===
    $('#studentDataTable').on('click', '.view-btn', function() {
        const studentId = $(this).data('id');
        const modalBody = $('#detailModalBody');

        modalBody.html('<p class="text-center">Đang tải...</p>');
        detailModal.show();

        $.ajax({
            url: apiUrlBase + studentId,
            method: 'GET',
            dataType: 'json',
            success: function(student) {
                console.log('Dữ liệu nhận được từ API:', student);
                const activeStatus = student.active === 'On' ? '<span class="badge bg-light-success text-success">Hoạt động</span>' : '<span class="badge bg-light-danger text-danger">Đã khóa</span>';
                const detailHtml = `
                    <p><strong>ID:</strong> \${student.id}</p>
                    <p><strong>Họ và Tên:</strong> \${student.pName}</p>
                    <p><strong>Email:</strong> \${student.email}</p>
                    <p><strong>Địa chỉ:</strong> \${student.address || 'Chưa cập nhật'}</p>
                    <p><strong>Số điện thoại:</strong> \${student.phonenumber || 'Chưa cập nhật'}</p>
                    <p><strong>Giới tính:</strong> \${student.gender}</p>
                    <hr><p><strong>Trạng thái:</strong> \${activeStatus}</p>`;
                modalBody.html(detailHtml);
            },
            error: function() { modalBody.html('<p class="text-danger text-center">Lỗi tải dữ liệu.</p>'); }
        });
    });
});
</script>

</body>
</html>

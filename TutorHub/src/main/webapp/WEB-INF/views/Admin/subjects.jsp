<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%--
  File: subjects.jsp
  Description: Giao diện quản lý môn học với các chức năng CRUD qua modal.
--%>

<!-- Header của trang -->
<div class="card bg-light-info shadow-none position-relative overflow-hidden">
    <div class="card-body px-4 py-3">
        <div class="row align-items-center">
            <div class="col-9">
                <h4 class="fw-semibold mb-8">Quản lý Môn học</h4>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a class="text-muted" href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item" aria-current="page">Danh sách Môn học</li>
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

<!-- Bảng danh sách môn học -->
<div class="card w-100 position-relative overflow-hidden">
    <div class="card-body p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h5 class="card-title fw-semibold mb-0">Danh sách Môn học</h5>
            <button id="addSubjectBtn" class="btn btn-primary rounded-pill">
                <i class="ti ti-plus me-2"></i>Thêm mới Môn học
            </button>
        </div>

        <div class="table-responsive">
            <table class="table border text-nowrap customize-table mb-0 align-middle" id="subjectDataTable">
                <thead class="text-dark fs-4">
                    <tr>
                        <th><h6 class="fs-4 fw-semibold mb-0">Tên Môn học</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Người tạo</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Trạng thái</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0 text-center">Hành động</h6></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td><p class="mb-0 fw-normal fs-4">${subject.suName}</p></td>
                            <td><p class="mb-0 fw-normal fs-4">${subject.admin.pName}</p></td>
                            <td>
                                <c:choose>
                                    <c:when test="${subject.active == 'On'}">
                                        <span class="badge bg-light-success text-success fw-semibold fs-2">Hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-light-danger text-danger fw-semibold fs-2">Đã khóa</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <div class="dropdown dropstart">
                                    <a href="#" class="text-muted" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="ti ti-dots-vertical fs-6"></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item d-flex align-items-center gap-3 view-btn" href="#" data-id="${subject.subId}"><i class="fs-4 ti ti-eye"></i>Xem chi tiết</a></li>
                                        <li><a class="dropdown-item d-flex align-items-center gap-3 edit-btn" href="#" data-id="${subject.subId}"><i class="fs-4 ti ti-edit"></i>Sửa</a></li>
                                        <li><a class="dropdown-item d-flex align-items-center gap-3" href="<c:url value='/admin/subjects/delete/${subject.subId}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa môn học này?');"><i class="fs-4 ti ti-trash"></i>Xóa</a></li>
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

<!-- Modal Thêm/Sửa Môn học -->
<div class="modal fade" id="subjectModal" tabindex="-1" aria-labelledby="subjectModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="subjectForm" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="subjectModalLabel">Thêm mới Môn học</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="subId" name="subId">
                    <div class="mb-3">
                        <label for="suName" class="form-label">Tên Môn học</label>
                        <input type="text" class="form-control" id="suName" name="suName" required>
                    </div>
                    <div class="mb-3">
                        <label for="suDescription" class="form-label">Mô tả</label>
                        <textarea class="form-control" id="suDescription" name="suDescription" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="adminId" class="form-label">Người tạo</label>
                        <select id="adminId" name="adminId" class="form-select" required>
                            <option value="adminname">-- Chọn người tạo --</option>
                            <c:forEach var="admin" items="${admins}">
                                <option value="${admin.id}">${admin.pName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-light-danger text-danger" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Xem Chi tiết -->
<div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="detailModalLabel">Chi tiết Môn học</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="detailModalBody">
                <p class="text-center">Đang tải dữ liệu...</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light-danger text-danger" data-bs-dismiss="modal">Đóng</button>
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


<%-- 
    Lưu ý: Script này nên được đặt trong file layout chính (main.jsp)
    ngay trước thẻ đóng </body>, sau khi đã nạp jQuery và Bootstrap JS.
--%>
<script>
    $(document).ready(function () {

        // Khởi tạo DataTables cho bảng
        $('#subjectDataTable').DataTable({
            "language": {
                "search": "Tìm kiếm:", "lengthMenu": "Hiển thị _MENU_ mục",
                "info": "Hiển thị từ _START_ đến _END_ của _TOTAL_ mục", "infoEmpty": "Không có dữ liệu",
                "infoFiltered": "(được lọc từ _MAX_ mục)", "paginate": {"next": "Tiếp", "previous": "Trước"},
                "zeroRecords": "Không tìm thấy kết quả phù hợp"
            }
        });

        const subjectModal = new bootstrap.Modal(document.getElementById('subjectModal'));
        const detailModal = new bootstrap.Modal(document.getElementById('detailModal'));

        // Xử lý nút "Thêm mới"
        $('#addSubjectBtn').on('click', function () {
            $('#subjectForm').attr('action', '<c:url value="/admin/subjects/add"/>');
            $('#subjectModalLabel').text('Thêm mới Môn học');
            $('#subjectForm')[0].reset(); // Xóa dữ liệu cũ trong form
            $('#subId').val(''); // Đảm bảo ID trống
            subjectModal.show();
        });

        // SỬA LỖI: Xây dựng URL đúng cách
        const apiUrlBase = '<c:url value="/admin/subjects/api/"/>';

        // Xử lý nút "Sửa"
        $('#subjectDataTable').on('click', '.edit-btn', function () {
            const subjectId = $(this).data('id');

            $.ajax({
                url: apiUrlBase + subjectId, // <-- SỬA LỖI Ở ĐÂY
                method: 'GET',
                success: function (subject) {
                    $('#subjectForm').attr('action', '<c:url value="/admin/subjects/edit"/>');
                    $('#subjectModalLabel').text('Chỉnh sửa Môn học');
                    $('#subId').val(subject.subId);
                    $('#suName').val(subject.suName);
                    $('#suDescription').val(subject.suDescription);
                    $('#adminId').val(subject.admin.id);
                    subjectModal.show();
                },
                error: function () {
                    alert('Không thể tải dữ liệu môn học.');
                }
            });
        });

        // Xử lý nút "Xem chi tiết"
        $('#subjectDataTable').on('click', '.view-btn', function () {
            const subjectId = $(this).data('id');
            const modalBody = $('#detailModalBody');

            modalBody.html('<p class="text-center">Đang tải dữ liệu...</p>');
            detailModal.show();

            $.ajax({
                url: apiUrlBase + subjectId, // <-- SỬA LỖI Ở ĐÂY
                method: 'GET',
                success: function (subject) {
                    console.log('Dữ liệu nhận được từ API:', subject);
                    const activeStatus = subject.active === 'On'
                            ? '<span class="badge bg-success">Hoạt động</span>'
                            : '<span class="badge bg-danger">Đã khóa</span>';

                    // SỬA LỖI: Xóa các dấu '\' không cần thiết
                    const detailHtml = `
                    <p><strong>ID:</strong> \${subject.subId}</p>
                    <p><strong>Tên môn học:</strong> \${subject.suName}</p>
                    <p><strong>Mô tả:</strong> \${subject.suDescription || 'Không có'}</p>
                    <hr>
                    <p><strong>Người tạo:</strong> \${subject.admin.pName}</p>
                    <p><strong>Trạng thái:</strong> \${activeStatus}</p>
                `;
                    modalBody.html(detailHtml);
                },
                error: function () {
                    modalBody.html('<p class="text-danger text-center">Lỗi khi tải dữ liệu.</p>');
                }
            });
        });
    });
</script>

</body>
</html>

<%-- 
    Document   : tutors
    Created on : Jun 26, 2025, 9:07:21 PM
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
                <h4 class="fw-semibold mb-8">Quản lý Gia sư</h4>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a class="text-muted" href="<c:url value='/admin'/>">Trang chủ</a></li>
                        <li class="breadcrumb-item" aria-current="page">Danh sách Gia sư</li>
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

<div class="card w-100 position-relative overflow-hidden">
    <div class="card-body p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h5 class="card-title fw-semibold mb-0">Danh sách Gia sư</h5>
            <a href="<c:url value='/admin/tutors/create'/>" class="btn btn-primary rounded-pill">
                <i class="ti ti-plus me-2"></i>Thêm mới Gia sư
            </a>
        </div>

        <!-- Bảng dữ liệu -->
        <div class="table-responsive">
            <table class="table border text-nowrap customize-table mb-0 align-middle" id="tutorDataTable">
                <thead class="text-dark fs-4">
                    <tr>
                        <th><h6 class="fs-4 fw-semibold mb-0">Tên Gia sư</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Môn học</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Phí (VND/giờ)</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0">Đánh giá</h6></th>
                        <th><h6 class="fs-4 fw-semibold mb-0 text-center">Hành động</h6></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tutor" items="${tutors}">
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="bg-primary-subtle rounded-circle round-40 me-3">
                                        <span class="fs-6 text-primary fw-semibold">${tutor.pName.charAt(0)}</span>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="fs-4 fw-semibold mb-0">${tutor.pName}</h6>
                                        <span class="fw-normal">${tutor.email}</span>
                                    </div>
                                </div>
                            </td>
                            <td><p class="mb-0 fw-normal fs-4">${tutor.subject.suName}</p></td>
                            <td><p class="mb-0 fw-normal fs-4"><fmt:formatNumber value="${tutor.fee}" type="number"/> đ</p></td>
                            <td><span class="badge bg-light-warning text-warning fw-semibold fs-2">${tutor.rating}/5</span></td>
                            <td class="text-center">
                                <div class="dropdown dropstart">
                                    <a href="#" class="text-muted" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="ti ti-dots-vertical fs-6"></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a class="dropdown-item d-flex align-items-center gap-3 edit-tutor-btn" href="#" 
                                               data-bs-toggle="modal" 
                                               data-bs-target="#editTutorModal" 
                                               data-id="${tutor.id}">
                                                <i class="fs-4 ti ti-edit"></i>Sửa
                                            </a>
                                        </li>
                                        <li>
                                            <a class="dropdown-item d-flex align-items-center gap-3" href="<c:url value='/admin/tutors/delete/${tutor.id}'/>" onclick="return confirm('Bạn có chắc chắn muốn đình chỉ gia sư này?');">
                                                <i class="fs-4 ti ti-trash"></i>Đình chỉ
                                            </a>
                                        </li>
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

<!-- Modal Chỉnh sửa Gia sư -->
<div class="modal fade" id="editTutorModal" tabindex="-1" aria-labelledby="editTutorModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="editTutorForm" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="editTutorModalLabel">Chỉnh sửa thông tin Gia sư</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center">Đang tải dữ liệu...</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-light-danger text-danger" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
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
        Lưu ý: Để các script bên dưới hoạt động, file layout chính (main.jsp)
        PHẢI nạp các thư viện sau ở cuối trang (trước </body>):
        1. jQuery: <script src=".../jquery.min.js"></script>
        2. Bootstrap JS: <script src=".../bootstrap.bundle.min.js"></script>
        3. DataTables JS: <script src=".../jquery.dataTables.min.js"></script>
        4. DataTables Bootstrap 5 JS: <script src=".../dataTables.bootstrap5.min.js"></script>
    --%>
   <script>
    // SỬA ĐỔI: Chuyển danh sách môn học từ JSTL sang một mảng JavaScript
    const allSubjects = [
      <c:forEach var="subject" items="${subjects}" varStatus="loop">
        { subId: ${subject.subId}, suName: '${subject.suName}' }<c:if test="${!loop.last}">,</c:if>
      </c:forEach>
    ];

    $(document).ready(function() {
        
        $('#tutorDataTable').DataTable({
             "language": {
                "search": "Tìm kiếm:", "lengthMenu": "Hiển thị _MENU_ mục",
                "info": "Hiển thị từ _START_ đến _END_ của _TOTAL_ mục", "infoEmpty": "Không có dữ liệu",
                "infoFiltered": "(được lọc từ _MAX_ mục)", "paginate": { "next": "Tiếp", "previous": "Trước" },
                "zeroRecords": "Không tìm thấy kết quả phù hợp"
            }
        });

        $('#editTutorModal').on('show.bs.modal', function (event) {
            const button = $(event.relatedTarget);
            const tutorId = button.data('id');
            const modal = $(this);
            const modalBody = modal.find('.modal-body');

            const form = modal.find('#editTutorForm');
            const url = '<c:url value="/admin/tutors/edit/"/>' + tutorId;
            form.attr('action', url);
            
            modalBody.html('<p class="text-center">Đang tải dữ liệu...</p>');

            $.ajax({
                url: '<c:url value="/admin/tutors/api/"/>' + tutorId,
                method: 'GET',
                dataType: 'json',
                success: function(tutor) {
                    
                    // SỬA ĐỔI: Tạo danh sách <option> bằng JavaScript
                    const subjectOptions = allSubjects.map(s => {
                        const isSelected = (tutor.subject && tutor.subject.subId == s.subId) ? 'selected' : '';
                        return `<option value="\${s.subId}" \${isSelected}>\${s.suName}</option>`;
                    }).join('');

                    const formContent = `
                        <input type="hidden" name="id" value="\${tutor.id}">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Tên Gia sư</label>
                                <input type="text" class="form-control" name="pName" value="\${tutor.pName}" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" class="form-control" name="email" value="\${tutor.email}" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" name="address" value="\${tutor.address || ''}">
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" name="phonenumber" value="\${tutor.phonenumber || ''}">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Giới tính</label>
                                <select name="gender" class="form-select">
                                    <option value="Nam" \${tutor.gender === 'Nam' ? 'selected' : ''}>Nam</option>
                                    <option value="Nu" \${tutor.gender === 'Nu' ? 'selected' : ''}>Nữ</option>
                                </select>
                            </div>
                        </div>
                        <hr/>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label class="form-label">Phí (VND/giờ)</label>
                                <input type="number" class="form-control" name="fee" value="\${tutor.fee}" required>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label class="form-label">Đánh giá</label>
                                <select name="rating" class="form-select">
                                    <option value="1" \${tutor.rating == '1' ? 'selected' : ''}>1 sao</option>
                                    <option value="2" \${tutor.rating == '2' ? 'selected' : ''}>2 sao</option>
                                    <option value="3" \${tutor.rating == '3' ? 'selected' : ''}>3 sao</option>
                                    <option value="4" \${tutor.rating == '4' ? 'selected' : ''}>4 sao</option>
                                    <option value="5" \${tutor.rating == '5' ? 'selected' : ''}>5 sao</option>
                                </select>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label class="form-label">Môn học</label>
                                <select name="subject.subId" class="form-select" required>
                                    \${subjectOptions}
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Học vấn</label>
                            <input type="text" class="form-control" name="education" value="\${tutor.education || ''}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Kinh nghiệm</label>
                            <textarea class="form-control" name="experience" rows="3">\${tutor.experience || ''}</textarea>
                        </div>
                    `;
                    modalBody.html(formContent);
                },
                error: function(jqXHR) {
                    modalBody.html(`<p class="text-danger text-center">Không thể tải dữ liệu. Lỗi: \${jqXHR.status}</p>`);
                }
            });
        });
    });
</script>
</body>
</html>


<%-- 
    Document   : createTutor
    Created on : Jun 27, 2025, 9:55:26 PM
    Author     : qnhat
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  File: tutor_form.jsp
  Description: Giao diện form để thêm mới hoặc chỉnh sửa thông tin gia sư.
--%>

<!-- Header của trang -->
<div class="card bg-light-info shadow-none position-relative overflow-hidden">
    <div class="card-body px-4 py-3">
        <div class="row align-items-center">
            <div class="col-9">
                <h4 class="fw-semibold mb-8">Thêm mới Gia sư</h4>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a class="text-muted" href="<c:url value='/admin/tutors'/>">Danh sách Gia sư</a></li>
                        <li class="breadcrumb-item" aria-current="page">Thêm mới</li>
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

<!-- Form nhập liệu -->
<div class="card">
    <div class="card-body">
        <h5 class="card-title fw-semibold mb-4">Thông tin Gia sư</h5>

        <form:form action="${pageContext.request.contextPath}/admin/tutors/create" method="post" modelAttribute="tutor">

            <div class="row">
                <div class="col-md-12">
                    <div class="mb-3">
                        <label for="pName" class="form-label">Họ và Tên</label>
                        <form:input path="pName" cssClass="form-control" id="pName" required="true" />
                        <form:errors path="pName" cssClass="text-danger" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <form:input type="email" path="email" cssClass="form-control" id="email" required="true" />
                        <form:errors path="email" cssClass="text-danger" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <form:password path="password" cssClass="form-control" id="password" required="true" />
                        <form:errors path="password" cssClass="text-danger" />
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="address" class="form-label">Địa chỉ</label>
                <form:input path="address" cssClass="form-control" id="address" />
                <form:errors path="address" cssClass="text-danger" />
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="phonenumber" class="form-label">Số điện thoại</label>
                        <form:input path="phonenumber" cssClass="form-control" id="phonenumber" />
                        <form:errors path="phonenumber" cssClass="text-danger" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="gender" class="form-label">Giới tính</label>
                        <form:select path="gender" cssClass="form-select" id="gender">
                            <form:option value="Nam">Nam</form:option>
                            <form:option value="Nu">Nữ</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <hr class="my-4">
            <h5 class="card-title fw-semibold mb-4">Thông tin chuyên môn</h5>

            <div class="row">
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="fee" class="form-label">Phí (VND/giờ)</label>
                        <form:input type="number" path="fee" cssClass="form-control" id="fee" required="true" />
                        <form:errors path="fee" cssClass="text-danger" />
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="rating" class="form-label">Đánh giá (1-5)</label>
                        <form:select path="rating" cssClass="form-select" id="rating">
                            <form:option value="5">5 sao</form:option>
                            <form:option value="4">4 sao</form:option>
                            <form:option value="3">3 sao</form:option>
                            <form:option value="2">2 sao</form:option>
                            <form:option value="1">1 sao</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="subjectSubId" class="form-label">Môn học chuyên môn</label>

                        <%-- SỬA ĐỔI: Sử dụng input-group để đặt nút bên cạnh dropdown --%>
                        <div class="input-group">
                            <form:select path="subject.subId" cssClass="form-select" id="subjectSubId" required="true">
                                <form:option value="">-- Chọn môn học --</form:option>
                                <c:forEach var="subject" items="${subjects}">
                                    <form:option value="${subject.subId}">${subject.suName}</form:option>
                                </c:forEach>
                            </form:select>
                            <a href="<c:url value='/admin/subjects'/>" class="btn btn-outline-primary" type="button" title="Quản lý môn học">
                                <i class="ti ti-settings"></i>
                            </a>
                        </div>
                        <form:errors path="subject.subId" cssClass="text-danger" />
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="education" class="form-label">Học vấn</label>
                <form:input path="education" cssClass="form-control" id="education" required="true" />
                <form:errors path="education" cssClass="text-danger" />
            </div>

            <div class="mb-3">
                <label for="experience" class="form-label">Kinh nghiệm</label>
                <form:textarea path="experience" cssClass="form-control" id="experience" rows="4" />
                <form:errors path="experience" cssClass="text-danger" />
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-primary">Tạo mới</button>
                <a href="<c:url value='/admin/tutors'/>" class="btn btn-light-danger text-danger ms-2">Hủy bỏ</a>
            </div>

        </form:form>
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

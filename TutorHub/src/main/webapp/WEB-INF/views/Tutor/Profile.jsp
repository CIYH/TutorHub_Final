<%-- 
    Document   : test
    Created on : Jun 20, 2025, 5:50:42 PM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-content">
    <section class="section">
        <div class="card">
            <div class="card-header">
                <div class="d-flex justify-content-between align-items-center">
                    <h4 class="card-title mb-0">Hồ sơ của tôi</h4>
                    <div class="avatar avatar-xl">
                        <%-- Thay bằng ảnh đại diện của gia sư --%>
                        <img src="${pageContext.request.contextPath}/src/images/faces/1.jpg" alt="Avatar">
                    </div>
                </div>
            </div>
            <div class="card-body">
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${successMessage}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/tutor/HoSo/update" method="POST">
                    <%-- Thêm một trường ẩn để gửi ID của gia sư khi submit form --%>
                    <input type="hidden" name="id" value="${tutor.getId()}">

                    <div class="row">
                        <%-- Tên --%>
                        <div class="col-md-6 col-12">
                            <div class="form-group">
                                <label for="p_name">Tên gia sư</label>
                                <div class="input-group">
                                    <p class="form-control-static">${tutor.getP_name()}</p>
                                    <input type="text" id="p_name" class="form-control d-none" name="p_name" value="${tutor.getP_name()}">
                                    <span class="input-group-text btn-edit" data-target="p_name"><i class="bi bi-pencil"></i></span>
                                </div>
                            </div>
                        </div>

                        <%-- Email --%>
                        <div class="col-md-6 col-12">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <div class="input-group">
                                    <p class="form-control-static">${tutor.getEmail()}</p>
                                    <input type="email" id="email" class="form-control d-none" name="email" value="${tutor.getEmail()}">
                                    <span class="input-group-text btn-edit" data-target="email"><i class="bi bi-pencil"></i></span>
                                </div>
                            </div>
                        </div>

                        <%-- Địa chỉ --%>
                        <div class="col-12">
                            <div class="form-group">
                                <label for="address">Địa chỉ</label>
                                <div class="input-group">
                                    <p class="form-control-static">${tutor.getAddress()}</p>
                                    <input type="text" id="address" class="form-control d-none" name="address" value="${tutor.getAddress()}">
                                    <span class="input-group-text btn-edit" data-target="address"><i class="bi bi-pencil"></i></span>
                                </div>
                            </div>
                        </div>

                        <%-- Giới tính --%>
                        <div class="col-md-6 col-12">
                            <div class="form-group">
                                <label for="gender">Giới tính</label>
                                <div class="input-group">
                                    <p class="form-control-static">${tutor.getGender()}</p>
                                    <select id="gender" class="form-select d-none" name="gender">
                                        <option value="Nữ" ${tutor.getGender() == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                        <option value="Nam" ${tutor.getGender() == 'Nam' ? 'selected' : ''}>Nam</option>
                                    </select>
                                    <span class="input-group-text btn-edit" data-target="gender"><i class="bi bi-pencil"></i></span>
                                </div>
                            </div>
                        </div>

                        <%-- Số điện thoại --%>
                        <div class="col-md-6 col-12">
                            <div class="form-group">
                                <label for="phonenumber">Số điện thoại</label>
                                <div class="input-group">
                                    <p class="form-control-static">${tutor.getPhonenumber()}</p>
                                    <input type="tel" id="phonenumber" class="form-control d-none" name="phonenumber" value="${tutor.getPhonenumber()}">
                                    <span class="input-group-text btn-edit" data-target="phonenumber"><i class="bi bi-pencil"></i></span>
                                </div>
                            </div>
                        </div>

                        <%-- Các trường chỉ đọc --%>
                        <hr class="my-4">
                        <h5 class="mb-3">Thông tin chuyên môn</h5>

                        <div class="col-12"><div class="form-group"><label>Môn học giảng dạy</label><p class="form-control-static text-muted">${tutor.getSubjectName()}</p></div></div>
                        <div class="col-12"><div class="form-group"><label>Bằng cấp</label><p class="form-control-static text-muted">${tutor.getEducation()}</p></div></div>
                        <div class="col-12"><div class="form-group"><label>Kinh nghiệm</label><p class="form-control-static text-muted">${tutor.getExperience()}</p></div></div>
                    </div>

                    <div class="row mt-4">
                        <div class="col-12 d-flex justify-content-end">
                            <button type="button" class="btn btn-light-secondary me-2" onclick="window.location.reload();">Hủy</button>
                            <button type="submit" class="btn btn-primary" id="btn-save" disabled>Lưu thay đổi</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>

<style>
    /* CSS để định dạng văn bản tĩnh trông giống như ô input bị vô hiệu hóa */
    .form-control-static {
        display: block;
        width: 100%;
        padding: .375rem .75rem;
        font-size: 1rem;
        font-weight: 400;
        line-height: 1.5;
        color: #607080;
        background-color: #f2f4f6;
        border: 1px solid #dce7f1;
        border-radius: .25rem;
        min-height: calc(1.5em + .75rem + 2px);
        margin-bottom: 0;
    }
    .btn-edit {
        cursor: pointer;
    }
</style>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const editButtons = document.querySelectorAll('.btn-edit');
        const saveButton = document.getElementById('btn-save');

        editButtons.forEach(button => {
            button.addEventListener('click', function () {
                // Lấy id của input/select mục tiêu từ thuộc tính data-target
                const targetId = this.getAttribute('data-target');
                const inputGroup = this.closest('.input-group');

                // Tìm phần tử văn bản tĩnh và ô nhập liệu
                const staticText = inputGroup.querySelector('.form-control-static');
                const editableField = inputGroup.querySelector('#' + targetId);

                // Ẩn văn bản, hiện ô nhập liệu
                if (staticText && editableField) {
                    staticText.classList.add('d-none');
                    editableField.classList.remove('d-none');
                    editableField.focus();
                }

                // Kích hoạt nút lưu
                saveButton.removeAttribute('disabled');
            });
        });
    });
</script>
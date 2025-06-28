<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="section">
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between align-items-center">
                <h4 class="card-title mb-0">Hồ sơ của tôi</h4>
                <div class="avatar avatar-xl">
                    <img src="${pageContext.request.contextPath}/images/faces/1.jpg" alt="Avatar">
                </div>
            </div>
        </div>
        <div class="card-body">
            
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">${errorMessage}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/profile/update" method="POST">
                <div class="row">
                    <%-- Tên --%>
                    <div class="col-md-6 col-12">
                        <div class="form-group">
                            <label for="p_name">Họ và Tên</label>
                            <div class="input-group">
                                <p class="form-control-static">${profile.pName}</p>
                                <input type="text" id="p_name" class="form-control d-none" name="pName" value="${profile.pName}">
                                <span class="input-group-text btn-edit" data-target="p_name"><i class="bi bi-pencil"></i></span>
                            </div>
                        </div>
                    </div>

                    <%-- Email --%>
                    <div class="col-md-6 col-12">
                         <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-group">
                                <p class="form-control-static">${profile.email}</p>
                                <input type="email" id="email" class="form-control d-none" name="email" value="${profile.email}">
                                <span class="input-group-text btn-edit" data-target="email"><i class="bi bi-pencil"></i></span>
                            </div>
                        </div>
                    </div>

                    <%-- Địa chỉ --%>
                    <div class="col-12">
                        <div class="form-group">
                            <label for="address">Địa chỉ</label>
                            <div class="input-group">
                                <p class="form-control-static">${profile.address}</p>
                                <input type="text" id="address" class="form-control d-none" name="address" value="${profile.address}">
                                <span class="input-group-text btn-edit" data-target="address"><i class="bi bi-pencil"></i></span>
                            </div>
                        </div>
                    </div>

                    <%-- Giới tính --%>
                    <div class="col-md-6 col-12">
                        <div class="form-group">
                            <label for="gender">Giới tính</label>
                             <div class="input-group">
                                <p class="form-control-static">${profile.gender}</p>
                                <select id="gender" class="form-select d-none" name="gender">
                                    <option value="Nam" ${profile.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                    <option value="Nu" ${profile.gender == 'Nu' ? 'selected' : ''}>Nữ</option>
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
                                <p class="form-control-static">${profile.phonenumber}</p>
                                <input type="tel" id="phonenumber" class="form-control d-none" name="phonenumber" value="${profile.phonenumber}">
                                <span class="input-group-text btn-edit" data-target="phonenumber"><i class="bi bi-pencil"></i></span>
                            </div>
                        </div>
                    </div>

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

<style>
    .form-control-static { display: block; width: 100%; padding: .375rem .75rem; font-size: 1rem; font-weight: 400; line-height: 1.5; color: #607080; background-color: #f2f4f6; border: 1px solid #dce7f1; border-radius: .25rem; min-height: calc(1.5em + .75rem + 2px); margin-bottom: 0; }
    .btn-edit { cursor: pointer; }
</style>

<script>
document.addEventListener('DOMContentLoaded', function () {
    const editButtons = document.querySelectorAll('.btn-edit');
    const saveButton = document.getElementById('btn-save');

    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const targetId = this.getAttribute('data-target');
            const inputGroup = this.closest('.input-group');
            const staticText = inputGroup.querySelector('.form-control-static');
            const editableField = inputGroup.querySelector('#' + targetId);

            if (staticText && editableField) {
                staticText.classList.add('d-none');
                editableField.classList.remove('d-none');
                editableField.focus();
            }
            
            saveButton.removeAttribute('disabled');
        });
    });
});
</script>

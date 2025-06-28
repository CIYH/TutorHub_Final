<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Đăng Ký Tài Khoản - Flexy Admin</title>

        <%-- Sử dụng CSS và JS từ template Flexy --%>
        <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/logos/favicon.png" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.min.css" />
    </head>

    <body>
        <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
             data-sidebar-position="fixed" data-header-position="fixed">
            <div class="position-relative overflow-hidden text-bg-light min-vh-100 d-flex align-items-center justify-content-center">
                <div class="d-flex align-items-center justify-content-center w-100">
                    <div class="row justify-content-center w-100">
                        <div class="col-md-8 col-lg-6 col-xxl-3">
                            <div class="card mb-0">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/" class="text-nowrap logo-img text-center d-block py-3 w-100">
                                        <img src="${pageContext.request.contextPath}/assets/images/logos/TutorHub.svg" width="180" alt="Logo">
                                    </a>
                                    <p class="text-center">Tạo tài khoản mới</p>

                                    <%-- Hiển thị thông báo lỗi (nếu có) --%>
                                    <c:if test="${not empty errorMessage}">
                                        <div class="alert alert-danger" role="alert">
                                            ${errorMessage}
                                        </div>
                                    </c:if>

                                    <form:form method="POST" modelAttribute="students" action="${pageContext.request.contextPath}/register">
                                        <div class="mb-3">
                                            <label for="pName" class="form-label">Họ và Tên</label>
                                            <form:input path="pName" cssClass="form-control" id="pName" required="true"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Địa chỉ Email</label>
                                            <form:input path="email" type="email" cssClass="form-control" id="email" required="true"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="phonenumber" class="form-label">Số điện thoại</label>
                                            <form:input path="phonenumber" cssClass="form-control" id="phonenumber"/>
                                        </div>
                                        <div class="mb-4">
                                            <label for="password" class="form-label">Mật khẩu</label>
                                            <form:password path="password" cssClass="form-control" id="password" required="true"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="address" class="form-label">Địa chỉ</label>
                                            <form:input path="address" cssClass="form-control" id="address"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="gender" class="form-label">Giới tính</label>
                                            <form:select path="gender" cssClass="form-select" id="gender">
                                                <form:option value="Nam">Nam</form:option>
                                                <form:option value="Nữ">Nữ</form:option>
                                                <form:option value="Khác">Khác</form:option>
                                            </form:select>
                                        </div>

                                        <button type="submit" class="btn btn-primary w-100 py-8 fs-4 mb-4 rounded-2">Đăng Ký</button>

                                        <div class="d-flex align-items-center justify-content-center">
                                            <p class="fs-4 mb-0 fw-bold">Đã có tài khoản?</p>
                                            <a class="text-primary fw-bold ms-2" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
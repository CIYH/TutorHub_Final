<%-- 
    Document   : LandingPage
    Created on : Jun 29, 2025, 12:28:34 AM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chào mừng đến với Dịch vụ Gia sư</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            /* Đặt màu nền dự phòng cho body */
            background-color: #f4f4f9; 
        }

        /* THAY ĐỔI: Style giờ được áp dụng cho phần hero thay vì toàn bộ trang */
        .hero-section-with-bg {
            /* Thay thế bằng đường dẫn đến ảnh của bạn */
            background-image: url('${pageContext.request.contextPath}/src/images/samples/pexels-katerina-holmes-5905480.jpg'); 
            
            /* Tính toán chiều cao để lấp đầy phần còn lại của màn hình (100% chiều cao trừ đi chiều cao của navbar, khoảng 56px) */
            min-height: calc(100vh - 56px); 
            
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            position: relative;
        }

        .hero-section-with-bg::before {
            content: "";
            position: absolute;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0, 0, 0, 0.5);
        }
        
        .hero-content {
            position: relative;
            z-index: 2;
            text-align: center;
            color: white;
            padding: 2rem;
        }

        .hero-content h1 {
            font-size: 3.5rem;
            font-weight: 700;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.7);
        }

        .hero-content p {
            font-size: 1.25rem;
            max-width: 600px;
            margin: 1rem auto;
        }
    </style>
</head>
<body>

    <%-- THAY ĐỔI: Bỏ fixed-top, đổi navbar-dark thành navbar-light và bg-white --%>
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
        <div class="container">
            <div class="navbar-nav">
                <%-- THAY ĐỔI: Nút bấm được đổi style cho nền sáng --%>
                <a class="nav-link" href="${pageContext.request.contextPath}/login">
                    <button class="btn btn-outline-primary me-2" type="button">Đăng nhập</button>
                </a>
                <a class="nav-link" href="${pageContext.request.contextPath}/register">
                    <button class="btn btn-primary" type="button">Đăng kí</button>
                </a>
            </div>

            <a class="navbar-brand ms-auto" href="#">
                <%-- LƯU Ý: Sử dụng logo tối màu trên nền trắng --%>
                <img src="${pageContext.request.contextPath}/src/images/logo/TutorHub.svg" alt="Logo" width="200" height="40">
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>
    <%-- THAY ĐỔI: Section này giờ sẽ có ảnh nền và được đẩy xuống dưới topbar --%>
    <div class="hero-section-with-bg d-flex align-items-center justify-content-center">
        <div class="hero-content">
            <h1 class="display-4">Tìm kiếm Gia sư, Nâng cao Tri thức</h1>
            <p class="lead">Kết nối với hàng ngàn gia sư chất lượng cao trên mọi lĩnh vực, sẵn sàng giúp bạn chinh phục mọi mục tiêu học tập.</p>
            <a href="${pageContext.request.contextPath}/register" class="btn btn-primary btn-lg mt-3">Bắt đầu ngay</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

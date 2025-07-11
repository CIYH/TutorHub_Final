<%-- 
    Document   : header
    Created on : Jun 17, 2025, 4:45:57 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Mazer Admin Dashboard</title>

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/bootstrap.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/vendors/iconly/bold.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/vendors/perfect-scrollbar/perfect-scrollbar.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/vendors/bootstrap-icons/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/app.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/src/images/favicon.svg" type="image/x-icon">
    </head>

    <body>
        <div id="app">
            <div id="sidebar" class="active">
                <div class="sidebar-wrapper active">
                    <div class="sidebar-header">
                        <div class="d-flex justify-content-between">
                            <div class="logo">
                                <a href="#"><img src="${pageContext.request.contextPath}/src/images/logo/TutorHub.svg" alt="Logo" srcset=""></a>
                            </div>
                            <div class="toggler">
                                <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-menu">
                        <ul class="menu">
                            <li class="sidebar-title">Menu</li>

                            <li class="sidebar-item ${activeSidebar == 'Trang Chủ' ? 'active' : ''} ">
                                <a href="${pageContext.request.contextPath}/tutor/TrangChu" class='sidebar-link'>
                                    <i class="bi bi-grid-fill"></i>
                                    <span>Trang chủ</span>
                                </a>
                            </li>

                            <li class="sidebar-item  has-sub ${activeSidebar == 'Khóa Học' ? 'active' : ''}">
                                <a href="#" class='sidebar-link'>
                                    <i class="bi bi-stack"></i>
                                    <span>Khóa học</span>
                                </a>
                                <ul class="submenu ">
                                    <li class="submenu-item ${activePage == 'Tạo Khóa Học' ? 'active' : ''}">
                                        <a href="${pageContext.request.contextPath}/tutor/TaoKhoaHoc">Tạo khóa học</a>
                                    </li>
                                    <li class="submenu-item ${activePage == 'Quản Lý Khóa Học' ? 'active' : ''}">
                                        <a href="${pageContext.request.contextPath}/tutor/QuanLyKhoaHoc">Quản lý khóa học</a>
                                    </li>
                                </ul>
                            </li>

                            <li class="sidebar-item  has-sub ${activeSidebar == 'Quản Lý Học Viên' ? 'active' : ''}">
                                <a href="#" class='sidebar-link'>
                                    <i class="bi bi-collection-fill"></i>
                                    <span>Quản lý học viên</span>
                                </a>
                                <ul class="submenu ">
                                    <li class="submenu-item ${activePage == 'Duyệt Booking' ? 'active' : ''}">
                                        <a href="${pageContext.request.contextPath}/tutor/DuyetBooking">Duyệt booking</a>
                                    </li>
                                    <li class="submenu-item ${activePage == 'Điểm Danh' ? 'active' : ''}">
                                        <a href="${pageContext.request.contextPath}/tutor/CheckIn">Điểm danh</a>
                                    </li>
                                    <li class="submenu-item ${activePage == 'Danh Sách Học Viên' ? 'active' : ''}">
                                        <a href="${pageContext.request.contextPath}/tutor/DanhSachHocSinh">Xem danh sách học viên</a>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                    <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
                </div>
            </div>
            <div id="main">
                <header class="mb-3">
                    <a href="#" class="burger-btn d-block d-xl-none">
                        <i class="bi bi-justify fs-3"></i>
                    </a>
                </header>
                <!-- Topbar -->
                <div class="page-heading">
                    <div class="card rounded-3 shadow-sm p-3 mb-3">
                        <div class="page-title">
                            <div class="row align-items-center">
                                <div class="col-12 col-md-6 order-md-1 order-last text-md-start">
                                    <div class="d-flex justify-content-center justify-content-md-start">
                                        <h3 class="mb-0">${activePage}</h3>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 order-md-2 order-first d-flex justify-content-center justify-content-md-end ${activePage == 'Hồ Sơ' ? 'd-none' : ''}">
                                    <div class="dropdown">
                                        <a href="#" data-bs-toggle="dropdown" aria-expanded="false" class="d-flex align-items-center text-decoration-none text-reset bg-light rounded-3 p-2 shadow-sm">
                                            <div class="user-name me-2">
                                                <h6 class="mb-0 text-gray-600">Hồ Sơ</h6>
                                            </div>
                                        </a>
                                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/tutor/HoSo"><i class="icon-mid bi bi-person me-2"></i> My Profile</a></li>
                                            <li>
                                                <hr class="dropdown-divider">
                                            </li>
                                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="icon-mid bi bi-box-arrow-right me-2"></i> Logout</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

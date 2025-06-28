<%-- 
    Document   : layoutmaster
    Created on : Jun 6, 2025, 10:13:29 PM
    Author     : qnhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TutorHub-Quản lý</title>
        <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/logos/favicon.png" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.min.css" />

    </head>

    <body>
        <!--  Body Wrapper -->
        <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
             data-sidebar-position="fixed" data-header-position="fixed">

            <!--  App Topstrip -->
            <div class="app-topstrip bg-dark py-6 px-3 w-100 d-lg-flex align-items-center justify-content-between">
                <div class="d-flex align-items-center justify-content-center gap-5 mb-2 mb-lg-0">
                    <a class="d-flex justify-content-center" href="#">
                        <img src="${pageContext.request.contextPath}/assets/images/logos/logo-wrappixel.svg" alt="" width="150">
                    </a>


                </div>

                <div class="d-lg-flex align-items-center gap-2">
                    <h3 class="text-white mb-2 mb-lg-0 fs-5 text-center">TutorHub - Quản lý</h3>
                    <div class="d-flex align-items-center justify-content-center gap-2">

                        <div class="dropdown d-flex">
                            <a class="btn btn-primary d-flex align-items-center gap-1 " href="javascript:void(0)" id="drop4"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="ti ti-users"></i>
                                Design by group 2
                            </a>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Sidebar Start -->
            <aside class="left-sidebar">
                <!-- Sidebar scroll-->
                <div>
                    <div class="brand-logo d-flex align-items-center justify-content-between">
                        <a class="text-nowrap logo-img">
                            <img src="${pageContext.request.contextPath}/assets/images/logos/TutorHub.svg" alt="" />
                        </a>
                        <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                            <i class="ti ti-x fs-6"></i>
                        </div>
                    </div>
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
                        <ul id="sidebarnav">

                            <li class="nav-small-cap">
                            <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                            <span class="hide-menu">Trang chủ</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="<c:url value='/admin' />" aria-expanded="false">
                                    <div class="d-flex align-items-center gap-3">
                                        <span class="d-flex">
                                            <i class="ti ti-dashboard"></i>
                                        </span>
                                        <span class="hide-menu">Trang chủ</span>
                                    </div>

                                </a>
                            </li>

                            <li class="nav-small-cap">
                            <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                            <span class="hide-menu">Quản lý học viên</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="<c:url value='/admin/students' />" aria-expanded="false">
                                    <i class="ti ti-school"></i>
                                    <span class="hide-menu">Quản lý học viên</span>
                                </a>
                            </li>
                            <!-- ---------------------------------- -->
                            <!-- Dashboard -->
                            <!-- ---------------------------------- -->

                            <li>
                                <span class="sidebar-divider lg"></span>
                            </li>
                            <li class="nav-small-cap">
                            <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                            <span class="hide-menu">Quản lý gia sư</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="<c:url value='/admin/tutors' />" aria-expanded="false">
                                    <div class="d-flex align-items-center gap-3">
                                        <span class="d-flex">
                                            <i class="ti ti-user"></i>
                                        </span>
                                        <span class="hide-menu">Quản lý gia sư</span>
                                    </div>

                                </a>
                            </li>
                            <li class="nav-small-cap">
                            <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                            <span class="hide-menu">Quản lý môn học</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="<c:url value='/admin/subjects' />" aria-expanded="false">
                                    <div class="d-flex align-items-center gap-3">
                                        <span class="d-flex">
                                            <i class="ti ti-file-text"></i>
                                        </span>
                                        <span class="hide-menu">Quản lý môn học</span>
                                    </div>

                                </a>
                            </li>
                            <li>
                                <span class="sidebar-divider lg"></span>
                            </li>
                            <li class="nav-small-cap">
                            <iconify-icon icon="solar:menu-dots-linear" class="nav-small-cap-icon fs-4"></iconify-icon>
                            <span class="hide-menu">Quản lý hệ thống</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link justify-content-between" href="#" aria-expanded="false">
                                    <div class="d-flex align-items-center gap-3">
                                        <span class="d-flex">
                                            <i class="ti ti-device-desktop"></i>
                                        </span>
                                        <span class="hide-menu">Chỉnh sửa quy định</span>
                                    </div>

                                </a>
                            </li>


                        </ul>
                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!--  Sidebar End -->
            <!--  Main wrapper -->
            <div class="body-wrapper">
                <!--  Header Start -->
                <header class="app-header">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <ul class="navbar-nav">
                            <li class="nav-item d-block d-xl-none">
                                <a class="nav-link sidebartoggler " id="headerCollapse" href="javascript:void(0)">
                                    <i class="ti ti-menu-2"></i>
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link " href="javascript:void(0)" id="drop1" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="ti ti-bell"></i>
                                    <div class="notification bg-primary rounded-circle"></div>
                                </a>
                                <div class="dropdown-menu dropdown-menu-animate-up" aria-labelledby="drop1">
                                    <div class="message-body">
                                        <a href="javascript:void(0)" class="dropdown-item">
                                            Item 1
                                        </a>
                                        <a href="javascript:void(0)" class="dropdown-item">
                                            Item 2
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                            <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">

                                <li class="nav-item dropdown">
                                    <a class="nav-link " href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
                                       aria-expanded="false">
                                        <img src="${pageContext.request.contextPath}/assets/images/profile/user-1.jpg" alt="" width="35" height="35" class="rounded-circle">
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                                        <div class="message-body">
                                            <!--                                            <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                                                                            <i class="ti ti-user fs-6"></i>
                                                                                            <p class="mb-0 fs-3">My Profile</p>
                                                                                        </a>
                                                                                        <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                                                                            <i class="ti ti-mail fs-6"></i>
                                                                                            <p class="mb-0 fs-3">My Account</p>
                                                                                        </a>
                                                                                        <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                                                                            <i class="ti ti-list-check fs-6"></i>
                                                                                            <p class="mb-0 fs-3">My Task</p>
                                                                                        </a>-->
                                            <a href="<c:url value='/logout' />" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </header>
                <!--  Header End -->




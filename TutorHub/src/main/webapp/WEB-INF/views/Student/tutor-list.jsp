<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tìm Gia Sư</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap');

            body {
                font-family: 'Be Vietnam Pro', sans-serif;
                background-color: #f7f8fa;
                margin: 0;
                padding: 20px;
                color: #333;
            }

            .container {
                max-width: 1100px;
                margin: 0 auto;
            }

            .page-title {
                font-size: 28px;
                font-weight: 700;
                text-align: center;
                color: #1a2b4d;
                margin-bottom: 30px;
            }

            /* --- CSS CHO KHUNG TÌM KIẾM & LỌC --- */
            .filter-container {
                margin-bottom: 30px;
                background-color: #fff;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
            }

            .filter-form {
                display: flex;
                flex-wrap: wrap;
                gap: 15px;
                align-items: center;
            }

            .form-group {
                flex: 1;
                display: flex;
                flex-direction: column;
                min-width: 200px;
            }

            .form-label {
                font-size: 14px;
                font-weight: 500;
                color: #555;
                margin-bottom: 5px;
            }

            .form-control {
                padding: 12px 15px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 8px;
            }

            .filter-select {
                background-color: #fff;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E');
                background-repeat: no-repeat;
                background-position: right 15px top 50%;
                background-size: 10px;
            }

            .filter-button {
                padding: 12px 25px;
                margin-top: 20px;
                font-size: 16px;
                font-weight: 500;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s;
                align-self: flex-end;
            }

            .filter-button:hover {
                background-color: #0056b3;
            }
            /* ... các CSS cho tutor-card giữ nguyên ... */
            .tutor-card {
                background: #ffffff;
                border-radius: 12px;
                border: 1px solid #e4e7eb;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
                margin-bottom: 25px;
                padding: 20px;
                display: flex;
                gap: 20px;
                transition: all 0.3s ease;
            }

            .tutor-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 25px rgba(0, 0, 0, 0.08);
            }

            .tutor-avatar {
                flex-shrink: 0;
            }

            .avatar-placeholder {
                width: 80px;
                height: 80px;
                border-radius: 50%;
                background-color: #e3e8f0;
                display: flex;
                align-items: center;
                justify-content: center;
                color: #5c6a82;
            }

            .tutor-info {
                flex-grow: 1;
            }

            .tutor-header {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-bottom: 8px;
            }

            .tutor-name {
                font-size: 20px;
                font-weight: 700;
                color: #1a2b4d;
            }

            .featured-tag {
                background-color: #e6f7ff;
                color: #005f8d;
                padding: 3px 10px;
                border-radius: 15px;
                font-size: 12px;
                font-weight: 500;
            }

            .tutor-details {
                display: flex;
                flex-direction: column;
                gap: 6px;
                font-size: 14px;
                color: #5c6a82;
                margin-bottom: 12px;
            }

            .tutor-details span {
                display: flex;
                align-items: center;
                gap: 8px;
            }

            .tutor-description {
                font-size: 14px;
                line-height: 1.6;
                color: #48556a;
            }

            .tutor-actions {
                flex-shrink: 0;
                text-align: right;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                min-width: 160px;
            }

            .rating-price {
                margin-bottom: 10px;
            }

            .rating {
                font-size: 16px;
                font-weight: 500;
                color: #f5a623;
                margin-bottom: 4px;
            }

            .price {
                font-size: 18px;
                font-weight: 700;
                color: #007bff;
            }

            .price small {
                font-weight: 400;
                font-size: 13px;
                color: #5c6a82;
            }

            .action-buttons {
                display: flex;
                flex-direction: column;
                gap: 8px;
            }

            .btn {
                padding: 10px 15px;
                font-size: 14px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                text-decoration: none;
                text-align: center;
                font-weight: 500;
                transition: background-color 0.2s;
            }

            .btn-primary {
                background-color: #007bff;
                color: #fff;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }

            .btn-secondary {
                background-color: #e9ecef;
                color: #1a2b4d;
            }
            .btn-secondary:hover {
                background-color: #d1d8e0;
            }

            .icon {
                width: 16px;
                height: 16px;
                stroke-width: 2;
            }

        </style>
    </head>
    <body>

        <div class="container">
            <div class="filter-container">
                <form action="${pageContext.request.contextPath}/findtutors/list" method="get" class="filter-form">
                    <div class="form-group">
                        <label for="tutorName-input" class="form-label">Tên gia sư</label>
                        <input 
                            type="text" 
                            id="tutorName-input"
                            name="tutorName" 
                            class="form-control" 
                            placeholder="Nhập tên..." 
                            value="${tutorName}"
                            >
                    </div>
                    <div class="form-group">
                        <label for="subject-select" class="form-label">Môn học</label>
                        <select name="subjectId" id="subject-select" class="form-control filter-select">
                            <option value="">-- Tất cả môn học --</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.subId}" ${subject.subId == selectedSubjectId ? 'selected' : ''}>
                                    ${subject.suName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="filter-button">Tìm</button>
                </form>
            </div>

            <c:choose>
                <c:when test="${not empty tutors}">
                    <c:forEach var="tutor" items="${tutors}">
                        <div class="tutor-card">
                            <div class="tutor-avatar">
                                <div class="avatar-placeholder">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                </div>
                            </div>
                            <div class="tutor-info">
                                <div class="tutor-header">
                                    <span class="tutor-name">${tutor.pName}</span>
                                    <c:if test="${tutor.rating >= 4.5}"><span class="featured-tag">Nổi bật</span></c:if>
                                    </div>
                                    <div class="tutor-details">
                                    <%-- THÊM EMAIL VÀ SĐT VÀO ĐÂY --%>
                                    <span>
                                        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"><path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path></svg>
                                        <b>Môn dạy:</b> ${!empty tutor.subjectName ? tutor.subjectName : 'Chưa cập nhật'}
                                    </span>
                                    <span>
                                        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"><path d="M20 14.66V20a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h5.34"></path><polygon points="18 2 22 6 12 16 8 16 8 12 18 2"></polygon></svg>
                                        <b>Học vấn:</b> ${tutor.education}
                                    </span>
                                    <span>
                                        <svg class="icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"><rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect><path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path></svg>
                                        <b>Kinh nghiệm:</b> ${tutor.experience}
                                    </span>
                                </div>
                            </div>
                            <div class="tutor-actions">
                                <div class="rating-price">
                                    <div class="rating">⭐ ${!empty tutor.rating ? tutor.rating : 'Mới'}</div>

                                </div>
                                <div class="action-buttons">
                                    <a href="${pageContext.request.contextPath}/findtutors/detail/${tutor.id}" class="btn btn-primary">Xem chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p style="text-align: center; color: #5c6a82;">Không tìm thấy gia sư nào phù hợp.</p>
                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>

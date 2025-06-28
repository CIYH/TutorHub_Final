<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container homepage-container">
    <%-- Lời chào mừng, sử dụng thông tin từ session --%>
    <div class="welcome-banner">
        <h3>Chào mừng trở lại, ${studentName}!</h3>
        <p class="text-muted">Đây là lịch học trong tuần của bạn.</p>
    </div>

    <%-- Lịch học trong tuần --%>
    <div class="card shadow-sm rounded-3">
        <div class="card-body">
            <div class="schedule-grid">
                <c:set var="days" value="${['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật']}" />
                <c:forEach var="day" items="${days}">
                    <div class="day-column">
                        <h6 class="day-header">${day}</h6>
                        <div class="day-schedules">
                            <c:choose>
                                <c:when test="${not empty scheduleMap[day]}">
                                    <c:forEach var="schedule" items="${scheduleMap[day]}">
                                        <div class="home-schedule-card">
                                            <p class="hsc-title">${schedule.courseTitle}</p>
                                            <p class="hsc-tutor">
                                                <i class="bi bi-person-fill"></i> ${schedule.tutorName}
                                            </p>
                                            <p class="hsc-time">
                                                <i class="bi bi-clock-fill"></i>
                                                ${fn:substring(schedule.startAt, 0, 5)} - ${fn:substring(schedule.endAt, 0, 5)}
                                            </p>
                                            <p class="hsc-location">
                                                <i class="bi bi-geo-alt-fill"></i> ${schedule.location}
                                            </p>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="no-schedule-placeholder"></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<style>
    .homepage-container { padding-top: 20px; padding-bottom: 20px; }
    .welcome-banner {
        padding: 2rem;
        background-color: #e9f5ff;
        border: 1px solid #bde0fe;
        border-radius: .75rem;
        margin-bottom: 2rem;
    }
    .welcome-banner h3 {
        color: #0056b3;
        font-weight: 700;
    }
    
    .schedule-grid { display: grid; grid-template-columns: repeat(7, 1fr); gap: 15px; }
    .day-column { padding: 0; }
    .day-column h6.day-header { text-align: center; padding: 10px; background-color: #f4f5f7; border-radius: 8px; margin-bottom: 15px; font-weight: 600; color: #333; }
    
    .home-schedule-card {
        background-color: #fff;
        border: 1px solid #e9ecef;
        border-radius: 8px;
        padding: 15px;
        margin-bottom: 15px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.05);
    }
    .home-schedule-card p {
        margin-bottom: 8px;
        font-size: 0.9rem;
        display: flex;
        align-items: center;
    }
    .home-schedule-card p:last-child { margin-bottom: 0; }
    .home-schedule-card .hsc-title {
        font-weight: 600;
        color: #007bff;
        font-size: 1rem;
        margin-bottom: 10px;
    }
    .home-schedule-card i {
        margin-right: 8px;
        color: #6c757d;
    }
    .no-schedule-placeholder { min-height: 100px; }
</style>

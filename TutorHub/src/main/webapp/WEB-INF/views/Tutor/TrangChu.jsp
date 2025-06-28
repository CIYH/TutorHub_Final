<%-- 
    Document   : TrangChu
    Created on : Jun 21, 2025, 1:10:44 PM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-heading" >
    <h3>Chào mừng trở lại, <c:out value="${tutorName}"/>!</h3>
</div>
<div class="page-content">
    <section class="row">
        <div class="col-12 col-lg-9">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Lịch dạy của bạn trong tuần</h4>
                </div>
                <div class="card-body">
                    <div class="row" id="schedule-display-container">
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12 col-lg-3">
            <div class="card">
                <div class="card-body py-4 px-5">
                    <div class="d-flex align-items-center">
                        <div class="avatar avatar-xl">
                            <img src="${pageContext.request.contextPath}/src/images/faces/1.jpg" alt="Face 1">
                        </div>
                        <div class="ms-3 name">
                            <h5 class="font-bold"><c:out value="${tutorName}"/></h5>
                            <h6 class="text-muted mb-0">Gia sư</h6>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <h4>Kênh liên lạc</h4>
                </div>
                <div class="card-body">
                    <div class="d-flex align-items-center mb-3">
                        <div class="avatar avatar-lg">
                            <span class="avatar-content bg-primary text-white"><i class="bi bi-globe"></i></span>
                        </div>
                        <a href="#" class="ms-3 text-dark">Website TutorHub</a>
                    </div>
                    <div class="d-flex align-items-center mb-3">
                        <div class="avatar avatar-lg">
                            <span class="avatar-content bg-primary text-white"><i class="bi bi-facebook"></i></span>
                        </div>
                        <a href="#" class="ms-3 text-dark">Facebook TutorHub</a>
                    </div>
                    <div class="d-flex align-items-center">
                        <div class="avatar avatar-lg">
                            <span class="avatar-content bg-info text-white"><i class="bi bi-linkedin"></i></span>
                        </div>
                        <a href="#" class="ms-3 text-dark">LinkedIn</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<style>
    .day-column{
        padding:0 8px
    }
    .day-column h6.day-header{
        text-align:center;
        padding:8px;
        background-color:#f4f5f7;
        border-radius:5px;
        margin-bottom:12px
    }
    .schedule-card{
        background-color:#eef2ff;
        border-radius:8px;
        padding:12px;
        margin-bottom:12px;
        border-left:5px solid #435ebe;
        box-shadow:0 2px 4px rgba(0,0,0,.05)
    }
    .schedule-card .card-time{
        display:inline-block;
        background-color:#435ebe;
        color:#fff;
        padding:4px 8px;
        border-radius:4px;
        font-weight:700;
        font-size:.9em;
        margin-bottom:8px
    }
    .schedule-card .card-course-name{
        font-weight:600;
        color:#25396f;
        margin-bottom:4px;
        word-wrap:break-word
    }
    .schedule-card .card-time-range{
        font-size:.9em;
        color:#607080
    }
</style>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const scheduleDisplayContainer = document.getElementById('schedule-display-container');
        const daysOfWeek = ["Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"];
        const schedules = JSON.parse('${schedulesJson}' || '[]');

        function generateDayColumns() {
            scheduleDisplayContainer.innerHTML = '';
            daysOfWeek.forEach(day => {
                const col = document.createElement('div');
                col.className = 'col-12 col-lg day-column';
                col.innerHTML = `<h6 class="day-header">\${day}</h6><div id="day-col-\${day.replace(/\\s+/g, '')}"></div>`;
                scheduleDisplayContainer.appendChild(col);
            });
        }

        function renderScheduleCards() {
            schedules.sort((a, b) => dayToIndex(a.day) - dayToIndex(b.day) || timeToMinutes(a.start) - timeToMinutes(b.start));

            schedules.forEach(schedule => {
                const card = document.createElement('div');
                card.className = 'schedule-card';

                card.innerHTML = `
                <div class="card-time">\${schedule.start}</div>
                <div class="card-course-name">\${schedule.courseTitle}</div>
                <div class="card-time-range text-muted">Môn: \${schedule.subjectName}</div>
                <div class="card-time-range text-muted">Ca học: \${schedule.start} - \${schedule.end}</div>
            `;

                const colContentId = `day-col-\${schedule.day.replace(/\\s+/g, '')}`;
                const columnElement = document.getElementById(colContentId);
                if (columnElement) {
                    columnElement.appendChild(card);
                }
            });
        }

        function timeToMinutes(timeStr) {
            if (typeof timeStr !== 'string' || !timeStr)
                return 0;
            const [h, m] = timeStr.split(':').map(Number);
            return (h || 0) * 60 + (m || 0);
        }

        const dayOrder = {"Thứ 2": 2, "Thứ 3": 3, "Thứ 4": 4, "Thứ 5": 5, "Thứ 6": 6, "Thứ 7": 7, "Chủ Nhật": 8};
        function dayToIndex(day) {
            return dayOrder[day] || 9;
        }

        // --- Khởi tạo ban đầu ---
        generateDayColumns();
        renderScheduleCards();
    });
</script>

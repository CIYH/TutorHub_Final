<%-- 
    Document   : Sửa lịch học
    Created on : Jun 11, 2025, 3:13:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="section">
    <div class="row match-height">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Chỉnh Sửa Thông Tin Khóa Học</h4>
                </div>
                <div class="card-content">
                    <div class="card-body">
                        <form class="form form-vertical" id="courseForm" action="${pageContext.request.contextPath}/tutor/SuaKhoaHoc" method="POST">

                            <input type="hidden" name="sessionId" value="${course.getSessionId()}">

                            <div class="form-body">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="course-name" class="form-label">Tên Khóa Học <span class="text-danger">*</span></label>
                                            <input type="text" id="course-name" class="form-control" name="courseName" value="<c:out value='${course.getTitle()}'/>" required>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="subject-select" class="form-label">Môn Học <span class="text-danger">*</span></label>
                                            <select class="form-select" id="subject-select" name="subject" required>
                                                <option value="" disabled>Chọn môn học</option>
                                                <c:forEach var="subject" items="${listSubjects}">
                                                    <option value="${subject.getSubId()}" ${subject.getSubId() == course.getSubId() ? 'selected' : ''}>
                                                        <c:out value="${subject.getSu_Name()}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="duration" class="form-label">Thời Gian Khóa Học (theo tuần)<span class="text-danger">*</span></label>
                                            <input type="number" id="duration" class="form-control" name="duration" value="${course.getDuration()}" required min='1'>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="fee" class="form-label">Học Phí (VNĐ) <span class="text-danger">*</span></label>
                                            <input type="number" id="fee" class="form-control" name="fee" value="${course.getFee()}" required min="0">
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <label for="description" class="form-label">Mô Tả Khóa Học</label>
                                            <textarea class="form-control" id="description" name="description" rows="5" required><c:out value='${course.getSe_Description()}'/></textarea>
                                        </div>
                                    </div>

                                    <div class="col-12 mt-4">
                                        <h5 class="mb-3">Lịch Dạy Cho Khóa Học <span class="text-danger">*</span></h5>
                                        <div class="card shadow-sm rounded-3">
                                            <div class="card-body">
                                                <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addScheduleModal">
                                                    <i class="bi bi-plus-circle"></i> Thêm Lịch Dạy
                                                </button>
                                                <div id="schedule-list-container" class="mb-4">
                                                    <p id="no-schedule-text">Khóa học này chưa có lịch dạy.</p>
                                                </div>
                                                <div class="row" id="schedule-display-container"></div>
                                            </div>
                                        </div>
                                        <input type="hidden" id="selectedSchedules" name="selectedSchedules">
                                    </div>

                                    <div class="col-12 d-flex justify-content-end mt-4">
                                        <button type="button" id="submit-course-btn" class="btn btn-primary me-1 mb-1">Lưu Thay Đổi</button>
                                        <a href="${pageContext.request.contextPath}/tutor/QuanLyKhoaHoc" class="btn btn-light-secondary me-1 mb-1">Hủy</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="addScheduleModal" tabindex="-1" aria-labelledby="addScheduleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addScheduleModalLabel">Thêm Ca Học Mới</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="modalScheduleForm">
                    <div class="mb-3">
                        <label for="day-of-week" class="form-label">Thứ trong tuần <span class="text-danger">*</span></label>
                        <select class="form-select" id="day-of-week" required>
                            <option value="" selected disabled>-- Chọn thứ --</option>
                            <option value="Thứ 2">Thứ 2</option>
                            <option value="Thứ 3">Thứ 3</option>
                            <option value="Thứ 4">Thứ 4</option>
                            <option value="Thứ 5">Thứ 5</option>
                            <option value="Thứ 6">Thứ 6</option>
                            <option value="Thứ 7">Thứ 7</option>
                            <option value="Chủ Nhật">Chủ Nhật</option>
                        </select>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="start-time" class="form-label">Thời gian bắt đầu <span class="text-danger">*</span></label>
                            <input type="time" class="form-control" id="start-time" required step="900">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="end-time" class="form-label">Thời gian kết thúc <span class="text-danger">*</span></label>
                            <input type="time" class="form-control" id="end-time" required step="900">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-primary" id="save-schedule-btn">Lưu ca học</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        // --- PHẦN 1: HIỂN THỊ THÔNG BÁO TỪ CONTROLLER ---
        const successMessage = '<c:out value="${successMessage}"></c:out>';
        const errorMessage = '<c:out value="${errorMessage}"></c:out>';
        if (successMessage) {
            Swal.fire({icon: 'success', title: 'Thành công!', text: successMessage, confirmButtonColor: '#435ebe'});
        }
        if (errorMessage) {
            Swal.fire({icon: 'error', title: 'Đã có lỗi xảy ra!', text: errorMessage, confirmButtonColor: '#435ebe'});
        }

        // --- PHẦN 2: LOGIC CHÍNH CỦA TRANG SỬA ---
        const courseForm = document.getElementById('courseForm');
        const saveScheduleBtn = document.getElementById('save-schedule-btn');
        const modalForm = document.getElementById('modalScheduleForm');
        const addScheduleModal = new bootstrap.Modal(document.getElementById('addScheduleModal'));
        const scheduleListContainer = document.getElementById('schedule-list-container');
        const noScheduleText = document.getElementById('no-schedule-text');
        const scheduleDisplayContainer = document.getElementById('schedule-display-container');
        const selectedSchedulesInput = document.getElementById('selectedSchedules');
        const daysOfWeek = ["Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"];
        const submitCourseBtn = document.getElementById('submit-course-btn');

        // Lấy 2 loại dữ liệu lịch từ Controller
        const otherSchedules = JSON.parse('${otherSchedulesJson}' || '[]');
        let currentSchedules = JSON.parse('${schedulesOfThisCourseJson}' || '[]');

        function render() {
            renderScheduleList();
            renderScheduleCards();
            updateHiddenInput();
        }

        // Hàm này chỉ render danh sách các lịch có thể sửa (currentSchedules)
        function renderScheduleList() {
            scheduleListContainer.innerHTML = '';
            if (currentSchedules.length === 0) {
                noScheduleText.style.display = 'block';
                scheduleListContainer.appendChild(noScheduleText);
            } else {
                noScheduleText.style.display = 'none';
                currentSchedules.forEach(schedule => {
                    const item = document.createElement('div');
                    item.className = 'schedule-item';
                    item.innerHTML = `<span><i class="bi bi-calendar-check"></i> <strong>\${schedule.day}:</strong> \${schedule.start} - \${schedule.end}</span><button type="button" class="btn btn-sm btn-danger" data-id="\${schedule.id}"><i class="bi bi-trash"></i></button>`;
                    scheduleListContainer.appendChild(item);
                });
            }
        }

        // Hàm này hiển thị TẤT CẢ các lịch nhưng với màu khác nhau
        function renderScheduleCards() {
            document.querySelectorAll('.day-column > div').forEach(colContent => colContent.innerHTML = '');
            // Gộp cả lịch đang sửa và lịch tham khảo để hiển thị
            const allSchedules = [...otherSchedules, ...currentSchedules];
            allSchedules.sort((a, b) => dayToIndex(a.day) - dayToIndex(b.day) || timeToMinutes(a.start) - timeToMinutes(b.start));

            allSchedules.forEach(schedule => {
                const card = document.createElement('div');
                card.className = 'schedule-card';

                // Kiểm tra xem lịch này có nằm trong danh sách đang sửa không để tô màu
                const isEditable = currentSchedules.some(s => s.id === schedule.id);
                if (isEditable) {
                    card.classList.add('editable-schedule'); // Màu xanh/primary
                } else {
                    card.classList.add('existing-schedule'); // Màu xám
                }

                const subjectName = schedule.subjectName || "Tên môn học";
                const startTimeDisplay = schedule.start;
                card.innerHTML = `<div class="d-flex justify-content-between align-items-center mb-2"><div class="card-time">\${startTimeDisplay}</div><i class="bi bi-book text-muted"></i></div><div class="card-course-name">\${subjectName}</div><div class="card-time-range">Ca học: \${schedule.start} - \${schedule.end}</div>`;
                const colContentId = `day-col-\${schedule.day.replace(/\\s+/g, '')}`;
                const columnElement = document.getElementById(colContentId);
                if (columnElement) {
                    columnElement.appendChild(card);
                }
            });
        }

        function updateHiddenInput() {
            selectedSchedulesInput.value = JSON.stringify(currentSchedules);
        }

        // ... các hàm xử lý sự kiện và hàm phụ trợ khác giữ nguyên như phiên bản trước ...
        // (Bao gồm generateDayColumns, timeToMinutes, dayToIndex, saveScheduleBtn, scheduleListContainer, submitCourseBtn)

        // Dán lại để đảm bảo đầy đủ
        function generateDayColumns() {
            scheduleDisplayContainer.innerHTML = '';
            daysOfWeek.forEach(day => {
                const col = document.createElement('div');
                col.className = 'col-12 col-lg day-column';
                col.innerHTML = `<h6 class="day-header">\${day}</h6><div id="day-col-\${day.replace(/\\s+/g, '')}"></div>`;
                scheduleDisplayContainer.appendChild(col);
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

        saveScheduleBtn.addEventListener('click', function () {
            const day = document.getElementById('day-of-week').value;
            const start = document.getElementById('start-time').value;
            const end = document.getElementById('end-time').value;
            const subjectSelect = document.getElementById('subject-select');
            const subjectName = subjectSelect.options[subjectSelect.selectedIndex].text;
            if (!subjectSelect.value) {
                Swal.fire('Lỗi', 'Vui lòng chọn một môn học trên form chính.', 'error');
                return;
            }
            if (!day || !start || !end) {
                Swal.fire('Lỗi', 'Vui lòng điền đầy đủ Thứ, Giờ bắt đầu và Giờ kết thúc.', 'error');
                return;
            }
            if (start >= end) {
                Swal.fire('Lỗi', 'Thời gian kết thúc phải sau thời gian bắt đầu.', 'error');
                return;
            }

            // Validation chống trùng lịch phải kiểm tra với TẤT CẢ các lịch
            const allSchedulesToCheck = [...otherSchedules, ...currentSchedules];
            const newStartMinutes = timeToMinutes(start);
            const newEndMinutes = timeToMinutes(end);
            const isOverlapping = allSchedulesToCheck.some(s => {
                if (s.day !== day)
                    return false;
                return newStartMinutes < timeToMinutes(s.end) && newEndMinutes > timeToMinutes(s.start);
            });
            if (isOverlapping) {
                Swal.fire('Lỗi', 'Lịch vừa thêm bị trùng với một lịch đã có.', 'error');
                return;
            }

            currentSchedules.push({id: Date.now(), day, start, end, subjectName});
            render();
            addScheduleModal.hide();
            modalForm.reset();
        });

        scheduleListContainer.addEventListener('click', function (event) {
            const deleteButton = event.target.closest('button.btn-danger');
            if (deleteButton) {
                const scheduleId = deleteButton.dataset.id;
                currentSchedules = currentSchedules.filter(s => s.id != scheduleId);
                render();
            }
        });

        if (submitCourseBtn) {
            submitCourseBtn.addEventListener('click', function () {
                updateHiddenInput();
                if (!courseForm.checkValidity()) {
                    courseForm.reportValidity();
                    return;
                }
                Swal.fire({
                    title: 'Xác nhận thay đổi?',
                    text: "Bạn có chắc chắn muốn lưu lại các thông tin đã chỉnh sửa?",
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#435ebe',
                    cancelButtonColor: '#6c757d',
                    confirmButtonText: 'Lưu thay đổi',
                    cancelButtonText: 'Hủy'
                }).then((result) => {
                    if (result.isConfirmed) {
                        courseForm.submit();
                    }
                });
            });
        }

        // --- KHỞI TẠO BAN ĐẦU ---
        generateDayColumns();
        render();
    });
</script>

<style>
    /* CSS cho danh sách lịch đã thêm */
    .schedule-item {
        background-color: #f8f9fa;
        padding: 8px 12px;
        border-radius: 6px;
        margin-bottom: 8px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    /* NÂNG CẤP: CSS cho layout cột và thẻ lịch học */
    .day-column {
        padding: 0 8px;
    }

    .day-column h6.day-header {
        text-align: center;
        padding: 8px;
        background-color: #f4f5f7;
        border-radius: 5px;
        margin-bottom: 12px;
    }

    .schedule-card {
        background-color: #f4f5f7;
        border-radius: 8px;
        padding: 12px;
        margin-bottom: 12px;
        border-left: 5px solid #435ebe; /* Màu primary của Mazer */
        box-shadow: 0 2px 4px rgba(0,0,0,0.05);
    }

    .schedule-card .card-time {
        display: inline-block;
        background-color: #25396f;
        color: white;
        padding: 4px 8px;
        border-radius: 4px;
        font-weight: bold;
        font-size: 0.9em;
        margin-bottom: 8px;
    }

    .schedule-card .card-course-name {
        font-weight: 600;
        color: #25396f;
        margin-bottom: 4px;
        word-wrap: break-word;
    }

    .schedule-card .card-time-range {
        font-size: 0.9em;
        color: #607080;
    }
    /* Thêm style này để làm mờ hoặc thay đổi màu của lịch cũ */
    .schedule-card.existing-schedule {
        background-color: #e9ecef; /* Màu nền xám hơn */
        border-left-color: #6c757d; /* Màu viền xám */
    }

    .schedule-card.existing-schedule .card-time {
        background-color: #6c757d;
    }
</style>
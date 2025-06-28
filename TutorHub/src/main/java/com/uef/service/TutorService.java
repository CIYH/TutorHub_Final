package com.uef.service;

import com.uef.model.Schedule;
import com.uef.model.Session;
import com.uef.model.TutorDetail; // Giả sử model của bạn tên là Tutor
import com.uef.repository.ViewScheduleRepository;
import com.uef.repository.SessionRepository;
import com.uef.repository.TutorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service này quản lý tất cả các logic nghiệp vụ liên quan đến Gia sư. Nó được
 * gộp lại từ FindTutorService, SessionService, và ShowScheduleService để tăng
 * tính gắn kết và làm cho Controller gọn gàng hơn.
 */
@Service
public class TutorService {

    @Autowired
    private TutorDetailRepository tutorRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ViewScheduleRepository scheduleRepository;

    // --- Các phương thức từ FindTutorService cũ ---
    /**
     * Lọc và tìm kiếm gia sư một cách linh hoạt.
     */
    public List<TutorDetail> filterTutors(Integer subjectId, String tutorName) {
        return tutorRepository.filter(subjectId, tutorName);
    }

    /**
     * Lấy thông tin chi tiết của một gia sư, bao gồm cả tổng số buổi học.
     */
    public TutorDetail getTutorDetailsById(String id) {
        TutorDetail tutor = tutorRepository.findByIdWithDetails(id);
        if (tutor != null) {
            int sessionCount = tutorRepository.countSessionsByTutorId(id);
            tutor.setSessionCount(sessionCount);
        }
        return tutor;
    }

    // --- Các phương thức từ SessionService cũ ---
    /**
     * Lấy danh sách các khóa học đang hoạt động của một gia sư, kèm theo trạng
     * thái đăng ký của một sinh viên cụ thể.
     */
    public List<Session> findActiveSessionsByTutorId(String tutorId, String studentId) {
        return sessionRepository.findActiveByTutorId(tutorId, studentId);
    }

    // --- Các phương thức từ ShowScheduleService cũ ---
    /**
     * Lấy danh sách lịch dạy của một gia sư.
     */
    public List<Schedule> findSchedulesByTutorId(String tutorId) {
        return scheduleRepository.findByTutorId(tutorId);
    }

    @Transactional
    public void updateTutorRating(String tutorId, String rating) {
        tutorRepository.updateRating(tutorId, rating);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.ChonLichHoc;
import com.uef.model.DSKhoaHoc;
import com.uef.model.ScheduleDTO;
import com.uef.model.Tu_Session;
import com.uef.model.SessionWithSubName;
import com.uef.repository.Tu_SessionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class Tu_SessionService {
    
    @Autowired
    private Tu_SessionRepository sessionRepository;
    
    public List<ChonLichHoc> getAllSchedule(String tutorId){
        return sessionRepository.findAllSchedule(tutorId);
    }
    
    @Transactional
    public void createSession(Tu_Session session, List<ScheduleDTO> schedules, String tutorId) {
        int newSessionId = sessionRepository.insertSession(session, tutorId);
        for (ScheduleDTO schedule : schedules) {
            sessionRepository.insertSchedule(newSessionId, schedule);
        }
    }
    
    public List<DSKhoaHoc> searchAllSessionWithSubNameByTitle(String title, String tutorId){
        if (title != null && !title.trim().isEmpty()) {
            return sessionRepository.searchSessionWithSubNameByTitle(title, tutorId);
        }
        return sessionRepository.findAllSessionWithSubName(tutorId);
    }
    
    @Transactional
    public void deleteCourse(int sessionId) {
        sessionRepository.deleteSchedulesBySessionId(sessionId);
        sessionRepository.switchActiveSessionById(sessionId);
    }
    
    @Transactional
    public void updateCourse(Tu_Session session, List<ScheduleDTO> newSchedules) {
        // 1. Cập nhật thông tin chính của khóa học trong bảng Session
        sessionRepository.updateSession(session);
        // 2. Xóa tất cả các lịch dạy cũ liên quan đến Session này
        sessionRepository.deleteSchedulesBySessionId(session.getSessionId());
        // 3. Thêm lại toàn bộ lịch dạy mới từ form
        for (ScheduleDTO schedule : newSchedules) {
            sessionRepository.insertSchedule(session.getSessionId(), schedule);
        }
    }
    
    public SessionWithSubName getSessionWithSubNameById(int sessionId){
        return sessionRepository.findSessionWithSubNameById(sessionId);
    }
    
    public List<ScheduleDTO> getSchedulesBySessionId(int sessionId){
        return sessionRepository.findSchedulesBySessionId(sessionId);
    }
    
    public List<ScheduleDTO> getOtherSchedules(String tutorId, int currentSessionId) {
        return sessionRepository.findOtherSchedulesForTutor(tutorId, currentSessionId);
    }
}

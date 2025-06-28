/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.CheckIn;
import com.uef.model.CheckInForm;
import com.uef.repository.TutorCheckInRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class CheckInService {
    
    @Autowired
    private TutorCheckInRepository tutorCheckInRepository;
    
    public List<CheckIn> getCoursesForCheckin(String tutorId, String subjectQuery) {
        return tutorCheckInRepository.findAcceptedCoursesForTutor(tutorId, subjectQuery);
    }
    
    public List<CheckInForm> getHistory(int sessionId) {
        return tutorCheckInRepository.showCheckInHistory(sessionId);
    }

    public List<CheckInForm> getStudentsBySessionId(int sessionId) {
        return tutorCheckInRepository.findStudentsBySessionId(sessionId);
    }

    @Transactional
    public void createCheckinRecords(String tutorId, int sessionId, Map<String, String> studentStatuses) {
        if (studentStatuses == null || studentStatuses.isEmpty()) {
            return; // Không có gì để làm
        }

        for (Map.Entry<String, String> entry : studentStatuses.entrySet()) {
            String studentId = entry.getKey();
            String status = entry.getValue();

            // Tìm BookingId tương ứng với học viên và khóa học
            List<CheckInForm> bookingId = tutorCheckInRepository.findBookingId(sessionId);
            Integer Bid = bookingId.getFirst().getBookingId();
            if (Bid != null) {
                tutorCheckInRepository.insertCheckinRecord(Bid, studentId, tutorId, status);
            } else {
                // Có thể ghi log hoặc bỏ qua nếu không tìm thấy booking tương ứng
                System.out.println("Không tìm thấy BookingId cho StudentId: " + studentId + " và SessionId: " + sessionId);
            }
        }
    }

    @Transactional
    public void updateCheckinStatus(long checkinRecordId, String status) {
        tutorCheckInRepository.updateCheckinStatus(checkinRecordId, status);
    }
}

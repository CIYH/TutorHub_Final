package com.uef.service;

import com.uef.model.MySession;
import com.uef.repository.MySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySessionService {

    @Autowired
    private MySessionRepository mySessionRepository;

    public List<MySession> getSessionsByStudentId(String studentId, String searchTerm, Integer subjectId) {
        return mySessionRepository.findSessionsByStudentId(studentId, searchTerm, subjectId);
    }

    public MySession getDetailByBookingId(int bookingId) {
        return mySessionRepository.findDetailByBookingId(bookingId);
    }
}

package com.uef.service;

import com.uef.model.BookingSessionDetail;
import com.uef.repository.BookingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingSessionService {

    @Autowired
    private BookingSessionRepository bookingRepository;

    @Transactional
    public int createBooking(int sessionId, String location, String studentId) {

        BookingSessionDetail detail = new BookingSessionDetail();
        detail.setLocation(location);

        return bookingRepository.save(detail, sessionId, studentId);
    }

    @Transactional
    public void cancelBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}

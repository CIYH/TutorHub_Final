/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.BookingStudent;
import com.uef.repository.Tu_BookingSessionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class Tu_BookingSessionService {
    
    @Autowired
    private Tu_BookingSessionRepository bookingSessionRepository;
    
    public List<BookingStudent> getAllBookingStudent(String tutorId){
        return bookingSessionRepository.findAllBookingStudent(tutorId);
    }
    
    public BookingStudent getBookingStudentById(int bookingId){
        return bookingSessionRepository.findBookingStudentById(bookingId);
    }
    
    @Transactional
    public void approveBookingAndDeclineOthers(int bookingId, int sessionId, int duration) {
        // BƯỚC 1: LẤY DANH SÁCH CÁC ĐƠN CẦN TỪ CHỐI TRƯỚC KHI THAY ĐỔI BẤT CỨ ĐIỀU GÌ
        List<Integer> idsToDecline = bookingSessionRepository.getPendingBookingIdsForSession(sessionId, bookingId);

        // BƯỚC 2: DUYỆT ĐƠN ĐƯỢC CHỌN
        // Các thao tác này chỉ tác động đến duy nhất đơn có bookingId được truyền vào.
        bookingSessionRepository.updateBookingStatus(bookingId, "Accept");
        bookingSessionRepository.updateAcceptBookingDetail(bookingId, duration);

        // BƯỚC 3: TỪ CHỐI CÁC ĐƠN CÒN LẠI DỰA TRÊN DANH SÁCH ĐÃ LẤY Ở BƯỚC 1
        String declineReason = "Khóa học đã được đăng kí.";
        for (Integer otherBookingId : idsToDecline) {
            bookingSessionRepository.updateBookingStatus(otherBookingId, "Decline");
            bookingSessionRepository.updateDeclineBookingDetail(otherBookingId, declineReason);
        }
        
        // BƯỚC 4: KHÓA KHÓA HỌC LẠI
        bookingSessionRepository.switchSessionStatusToLock(sessionId);
    }
    
    @Transactional
    public void declineBooking(int bookingId, String reason) {
        // Cập nhật trạng thái và lý do cho duy nhất đơn được từ chối.
        bookingSessionRepository.updateBookingStatus(bookingId, "Decline");
        bookingSessionRepository.updateDeclineBookingDetail(bookingId, reason);
    }
}

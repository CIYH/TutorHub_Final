/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.BookingStudent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class Tu_BookingSessionRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<BookingStudent> findAllBookingStudent(String tutorId){
        String sql = "SELECT BookingId, Title, p_name "
                + "FROM Booking_Session AS bs, Session AS s, Student AS stu, people AS p, Tutor AS t "
                + "WHERE bs.SessionId = s.SessionId AND bs.StudentId = stu.StudentId AND stu.StudentId = p.Id AND s.TutorId = t.TutorId AND s.TutorId = ? AND Bs_Status IS NULL";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BookingStudent dto = new BookingStudent();
            dto.setBookingId(rs.getInt("BookingId"));
            dto.setTitle(rs.getString("Title"));
            dto.setP_name(rs.getString("p_name"));
            return dto;
        }, tutorId);
    }
    
    public BookingStudent findBookingStudentById(int bookingId){
        String sql = "SELECT p_name, email, phonenumber, Title, Su_Name, Se_Description, s.SessionId, s.Duration, bsd.Location, bs.BookingId "
                + "FROM people AS p, Booking_Session AS bs, Subject AS sub, Session AS s, Student AS stu, Booking_Session_Detail AS bsd "
                + "WHERE stu.StudentId = p.Id AND bs.StudentId = stu.StudentId AND bs.BookingId = bsd.BookingId AND bs.SessionId = s.SessionId AND s.SubId = sub.SubId AND bs.BookingId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            BookingStudent dto = new BookingStudent();
            dto.setP_name(rs.getString("p_name"));
            dto.setEmail(rs.getString("email"));
            dto.setPhonenumber(rs.getString("phonenumber"));
            dto.setTitle(rs.getString("Title"));
            dto.setSu_Name(rs.getString("Su_Name"));
            dto.setSe_Description(rs.getString("Se_Description"));
            dto.setSessionId(rs.getString("SessionId"));
            dto.setDuration(rs.getInt("Duration"));
            dto.setLocation(rs.getString("Location"));
            dto.setBookingId(rs.getInt("BookingId"));
            return dto;
        }, bookingId);
    }
    
    // Lấy ra ID của tất cả các đơn đăng kí đang chờ duyệt (status IS NULL)
    // cho một khóa học, TRỪ đơn đang được xử lý.
    public List<Integer> getPendingBookingIdsForSession(int sessionId, int bookingIdToExclude) {
        String sql = "SELECT BookingId FROM Booking_Session WHERE SessionId = ? AND Bs_Status IS NULL AND BookingId != ?";
        return jdbcTemplate.queryForList(sql, Integer.class, sessionId, bookingIdToExclude);
    }

    // Cập nhật trạng thái cho một đơn đăng kí cụ thể.
    public int updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE Booking_Session SET Bs_Status = ? WHERE BookingId = ?";
        return jdbcTemplate.update(sql, status, bookingId);
    }

    // Cập nhật chi tiết cho đơn được CHẤP NHẬN.
    public int updateAcceptBookingDetail(int bookingId, int durationInWeeks) {
        String sql = "UPDATE Booking_Session_Detail SET SessionStart = ?, SessionEnd = ?, declineReason = NULL WHERE BookingId = ?";
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusWeeks(durationInWeeks);
        Timestamp sessionStart = Timestamp.valueOf(start);
        Timestamp sessionEnd = Timestamp.valueOf(end);
        return jdbcTemplate.update(sql, sessionStart, sessionEnd, bookingId);
    }
    
    // Cập nhật chi tiết cho đơn bị TỪ CHỐI.
    public int updateDeclineBookingDetail(int bookingId, String reason) {
        // Cần đảm bảo bản ghi Booking_Session_Detail tồn tại. Cách an toàn là INSERT...ON DUPLICATE KEY UPDATE
        // hoặc kiểm tra sự tồn tại trước. Ở đây ta dùng INSERT IGNORE và theo sau là UPDATE.
        String checkSql = "SELECT COUNT(*) FROM Booking_Session_Detail WHERE BookingId = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, bookingId);

        if (count == 0) {
            // Nếu chưa có, tạo mới
            String insertSql = "INSERT INTO Booking_Session_Detail (BookingId, declineReason) VALUES (?, ?)";
            return jdbcTemplate.update(insertSql, bookingId, reason);
        } else {
            // Nếu có rồi, cập nhật
            String updateSql = "UPDATE Booking_Session_Detail SET declineReason = ?, SessionStart = NULL, SessionEnd = NULL WHERE BookingId = ?";
            return jdbcTemplate.update(updateSql, reason, bookingId);
        }
    }
    
    // Khóa Session lại sau khi đã có người được duyệt.
    public int switchSessionStatusToLock(int sessionId) {
        String sql = "UPDATE Session SET Se_Status = 'Lock' WHERE SessionId = ?";
        return jdbcTemplate.update(sql, sessionId);
    }
}

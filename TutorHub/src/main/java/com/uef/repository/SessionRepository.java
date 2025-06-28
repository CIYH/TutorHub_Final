/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SessionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * RowMapper này chỉ ánh xạ các cột từ bảng Session.
     */
    private Session mapRowToSession(ResultSet rs, int rowNum) throws SQLException {
        Session session = new Session();
        session.setSessionId(rs.getInt("SessionId"));
        session.setFee(rs.getInt("Fee"));
        session.setSubId(rs.getInt("SubId"));
        session.setSeDescription(rs.getString("Se_Description"));
        session.setTitle(rs.getString("Title"));
        session.setDuration(rs.getString("Duration")); // Giả sử Duration là INT
        session.setTutorId(rs.getString("TutorId"));
        session.setSeStatus(rs.getString("Se_Status"));
        session.setActive(rs.getString("Active"));
        return session;
    }

    /**
     * RowMapper này ánh xạ các cột từ câu lệnh JOIN phức tạp.
     */
    private Session mapRowToSessionWithDetails(ResultSet rs, int rowNum) throws SQLException {
        // Kế thừa mapper cơ bản
        Session session = mapRowToSession(rs, rowNum);

        // Map các cột được JOIN thêm
        session.setSubjectName(rs.getString("subjectName"));
        session.setBookingId(rs.getObject("bookingId", Integer.class));
        session.setBookingStatus(rs.getString("bookingStatus"));
        session.setAcceptedBookingsCount(rs.getInt("acceptedBookingsCount"));
        return session;
    }

    /**
     * Tìm tất cả các khóa học đang hoạt động của một gia sư, kèm theo trạng
     * thái đăng ký.
     */
    public List<Session> findActiveByTutorId(String tutorId, String studentId) {
        String sql = "SELECT "
                + "    ses.*, "
                + "    sub.Su_Name AS subjectName, "
                + "    bs.BookingId AS bookingId, "
                + "    bs.Bs_Status AS bookingStatus, "
                + "    (SELECT COUNT(*) FROM Booking_Session WHERE SessionId = ses.SessionId AND Bs_Status = 'Accept') AS acceptedBookingsCount "
                + "FROM "
                + "    Session ses "
                + "LEFT JOIN "
                + "    Subject sub ON ses.SubId = sub.SubId "
                + "LEFT JOIN "
                + "    Booking_Session bs ON ses.SessionId = bs.SessionId AND bs.StudentId = ? "
                + "WHERE "
                + "    ses.TutorId = ? AND ses.Active = 'On'";

        return jdbcTemplate.query(sql, this::mapRowToSessionWithDetails, studentId, tutorId);
    }
}

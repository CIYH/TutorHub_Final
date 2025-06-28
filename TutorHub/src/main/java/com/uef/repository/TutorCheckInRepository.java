/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.CheckIn;
import com.uef.model.CheckInForm;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class TutorCheckInRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<CheckIn> findAcceptedCoursesForTutor(String tutorId, String subjectQuery) {
        // Bắt đầu xây dựng câu lệnh SQL
        String baseSql = "SELECT ses.SessionId, ses.Title, sub.Su_Name " +
                         "FROM Booking_Session AS bs " +
                         "JOIN Session AS ses ON bs.SessionId = ses.SessionId " +
                         "JOIN Subject AS sub ON ses.SubId = sub.SubId " +
                         "WHERE bs.Bs_Status = 'Accept' AND ses.TutorId = ? ";

        List<Object> params = new ArrayList<>();
        params.add(tutorId);
        
        // Nếu có từ khóa tìm kiếm, thêm điều kiện LIKE vào câu SQL
        if (subjectQuery != null && !subjectQuery.trim().isEmpty()) {
            baseSql += "AND sub.Su_Name LIKE ? ";
            params.add("%" + subjectQuery + "%");
        }
        
        return jdbcTemplate.query(baseSql, (rs, rowNum) -> {
            CheckIn dto = new CheckIn();
            dto.setSessionId(rs.getInt("SessionId"));
            dto.setTitle(rs.getString("Title"));
            dto.setSu_Name(rs.getString("Su_Name"));
            return dto;
        }, params.toArray());
    }
    
    public List<CheckInForm> showCheckInHistory(int sessionId){
        String sql = "SELECT CreateTime, p_name, presentStatus, CheckinId "
                + "FROM Check_In AS ci "
                + "JOIN Booking_Session AS bs ON ci.BookingId = bs.BookingId "
                + "JOIN people AS p ON ci.StudentId = p.Id "
                + "WHERE bs.SessionId = ? ORDER BY ci.CreateTime DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CheckInForm dto = new CheckInForm();
            dto.setCreateTime(rs.getTimestamp("CreateTime").toLocalDateTime());
            dto.setP_name(rs.getString("p_name"));
            dto.setPresentStatus(rs.getString("presentStatus"));
            dto.setCheckinId(rs.getInt("CheckinId"));
            return dto;
        }, sessionId); 
    }
    
    public List<CheckInForm> findStudentsBySessionId(int sessionId) {
        String sql = "SELECT p.Id, p.p_name " +
                     "FROM people AS p " +
                     "JOIN Booking_Session AS bs ON p.Id = bs.StudentId " +
                     "WHERE bs.SessionId = ? AND bs.Bs_Status = 'Accept'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CheckInForm dto = new CheckInForm();
            dto.setStudentId(rs.getString("Id"));
            dto.setP_name(rs.getString("p_name"));
            return dto;
        }, sessionId);
    }
    
    public int insertCheckinRecord(int bookingId, String studentId, String tutorId, String status) {
        String sql = "INSERT INTO Check_In (BookingId, StudentId, TutorId, CreateTime, presentStatus, Active) VALUES (?, ?, ?, ?, ?, 'On')";
        return jdbcTemplate.update(sql, bookingId, studentId, tutorId, Timestamp.valueOf(LocalDateTime.now()), status);
    }
    
    public int updateCheckinStatus(long checkinRecordId, String status) {
        String sql = "UPDATE Check_In SET presentStatus = ? WHERE CheckInId = ?";
        return jdbcTemplate.update(sql, status, checkinRecordId);
    }
    
    public List<CheckInForm> findBookingId(int sessionId){
        String sql = "SELECT BookingId "
                + "FROM Booking_Session "
                + "WHERE SessionId = ? AND Bs_Status = 'Accept'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CheckInForm dto = new CheckInForm();
            dto.setBookingId(rs.getInt("BookingId"));
            return dto;
        }, sessionId);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */// File: src/main/java/com/uef/repository/DashboardRepository.java
package com.uef.repository;

import com.uef.dto.DailyCheckInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Đếm tổng số gia sư đang hoạt động.
     */
    public long getTutorCount() {
        String sql = "SELECT COUNT(*) FROM people WHERE p_role = 'tutor' AND Active = 'On'";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return (count != null) ? count : 0;
    }

    /**
     * Đếm tổng số học viên đang hoạt động.
     */
    public long getStudentCount() {
        String sql = "SELECT COUNT(*) FROM people WHERE p_role = 'user' AND Active = 'On'";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return (count != null) ? count : 0;
    }

    /**
     * Đếm tổng số môn học đang hoạt động.
     */
    public long getSubjectCount() {
        String sql = "SELECT COUNT(*) FROM Subject WHERE Active = 'On'";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return (count != null) ? count : 0;
    }

    /**
     * Lấy danh sách các buổi học trong ngày hôm nay và trạng thái điểm danh.
     */
    public List<DailyCheckInDTO> getTodaysCheckIns() {
        // Câu lệnh SQL này JOIN nhiều bảng để lấy thông tin cần thiết.
        // LEFT JOIN với bảng Check_In để lấy cả những buổi chưa có dữ liệu điểm danh.
        String sql = "SELECT "
                + "    p.p_name AS studentName, "
                + "    sub.Su_Name AS subjectName, "
                + "    s.Title AS sessionTitle, "
                + "    bsd.SessionStart AS startTime, "
                + "    COALESCE(ci.presentStatus, 'Chưa điểm danh') AS status "
                + "FROM Booking_Session_Detail bsd "
                + "JOIN Booking_Session bs ON bsd.BookingId = bs.BookingId "
                + "JOIN Student st ON bs.StudentId = st.StudentId "
                + "JOIN people p ON st.StudentId = p.Id "
                + "JOIN Session s ON bs.SessionId = s.SessionId "
                + "JOIN Subject sub ON s.SubId = sub.SubId "
                + "LEFT JOIN Check_In ci ON bs.BookingId = ci.BookingId "
                + "WHERE DATE(bsd.SessionStart) = CURDATE() AND bs.Bs_Status = 'Accept' "
                + // Chỉ lấy các buổi học đã được chấp nhận
                "ORDER BY bsd.SessionStart ASC";

        return jdbcTemplate.query(sql, this::mapRowToDailyCheckInDTO);
    }

    private DailyCheckInDTO mapRowToDailyCheckInDTO(ResultSet rs, int rowNum) throws SQLException {
        DailyCheckInDTO dto = new DailyCheckInDTO();
        dto.setStudentName(rs.getString("studentName"));
        dto.setSubjectName(rs.getString("subjectName"));
        dto.setSessionTitle(rs.getString("sessionTitle"));
        dto.setStartTime(rs.getTimestamp("startTime").toLocalDateTime().toLocalTime());
        dto.setStatus(rs.getString("status"));
        return dto;
    }
}

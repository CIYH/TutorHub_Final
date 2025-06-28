/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.HomePage;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class HomePageRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public String findTutorNameById(String tutorId) {
        String sql = "SELECT p_name FROM people WHERE Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, tutorId);
        } catch (Exception e) {
            return "Gia sư"; // Giá trị mặc định nếu không tìm thấy
        }
    }

    public List<HomePage> findAllSchedulesByTutorId(String tutorId) {
        String sql = "SELECT ses.Title, sub.Su_Name, sch.StudyDate, sch.StartAt, sch.EndAt " +
                     "FROM Schedule AS sch " +
                     "JOIN Session AS ses ON sch.SessionId = ses.SessionId " +
                     "JOIN Subject AS sub ON ses.SubId = sub.SubId " +
                     "JOIN Booking_Session AS bs ON ses.SessionId = bs.SessionId " +
                     "WHERE ses.TutorId = ? AND sch.Active = 'On' AND ses.Active = 'On' AND Bs_Status = 'Accept'";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HomePage dto = new HomePage();
            Time startAt = rs.getTime("StartAt");
            Time endAt = rs.getTime("EndAt");

            dto.setCourseTitle(rs.getString("Title"));
            dto.setSubjectName(rs.getString("Su_Name"));
            dto.setDay(rs.getString("StudyDate"));
            dto.setStart(startAt != null ? startAt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
            dto.setEnd(endAt != null ? endAt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
            return dto;
        }, tutorId);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.StudentList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class StudentListRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StudentList> findListOfStudent(String tutorId) {
        String sql = "SELECT 	p.p_name, se.Title, bsd.SessionEnd, "
                + "		SUM(CASE WHEN presentStatus = 'Present' THEN 1 ELSE 0 END) AS presentCount, "
                + "		SUM(CASE WHEN presentStatus = 'Absent' THEN 1 ELSE 0 END) AS absentCount, "
                + "		SUM(CASE WHEN presentStatus = 'Late' THEN 1 ELSE 0 END) AS lateCount "
                + "FROM Check_In AS ci "
                + "JOIN people AS p ON ci.StudentId = p.Id "
                + "JOIN Booking_Session AS bs ON ci.BookingId = bs.BookingId "
                + "JOIN Session AS se ON bs.SessionId = se.SessionId "
                + "JOIN Booking_Session_Detail AS bsd ON ci.BookingId = bsd.BookingId "
                + "WHERE ci.TutorId = ? AND bs.Bs_Status = 'Accept' "
                + "GROUP BY p.p_name, se.Title, bsd.SessionEnd";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            StudentList dto = new StudentList();
            dto.setP_name(rs.getString("p_name"));
            dto.setTitle(rs.getString("Title"));
            dto.setSessionEnd(rs.getTimestamp("SessionEnd").toLocalDateTime());
            dto.setPresent(rs.getInt("presentCount"));
            dto.setAbsent(rs.getInt("absentCount"));
            dto.setLate(rs.getInt("lateCount"));
            return dto;
        }, tutorId);
    }
    
    
}

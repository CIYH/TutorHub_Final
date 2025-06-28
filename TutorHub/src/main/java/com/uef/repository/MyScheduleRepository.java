package com.uef.repository;

import com.uef.model.MySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MyScheduleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MySchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        MySchedule schedule = new MySchedule();
        schedule.setCourseTitle(rs.getString("courseTitle"));
        schedule.setTutorName(rs.getString("tutorName"));
        schedule.setStudyDate(rs.getString("StudyDate"));
        schedule.setStartAt(rs.getTime("StartAt"));
        schedule.setEndAt(rs.getTime("EndAt"));
        schedule.setLocation(rs.getString("Location"));
        return schedule;
    }

    public List<MySchedule> findApprovedSchedulesByStudentId(String studentId) {
        String sql = "SELECT s.Title AS courseTitle, p.p_name AS tutorName, sch.StudyDate, sch.StartAt, sch.EndAt, bsd.Location " +
                     "FROM Booking_Session bs " +
                     "JOIN Session s ON bs.SessionId = s.SessionId " +
                     "JOIN Schedule sch ON s.SessionId = sch.SessionId " +
                     "JOIN people p ON s.TutorId = p.Id " +
                     "LEFT JOIN Booking_Session_Detail bsd ON bs.BookingId = bsd.BookingId " +
                     "WHERE bs.StudentId = ? AND bs.Bs_Status = 'Accept'";
        return jdbcTemplate.query(sql, this::mapRow, studentId);
    }
}
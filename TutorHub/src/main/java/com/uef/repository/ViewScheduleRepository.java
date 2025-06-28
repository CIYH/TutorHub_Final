package com.uef.repository;

import com.uef.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ViewScheduleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Schedule mapRowToSchedule(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(rs.getInt("ScheduleId"));
        schedule.setSessionId(rs.getInt("SessionId"));
        schedule.setAdminId(rs.getString("AdminID"));
        schedule.setStudyDate(rs.getString("StudyDate"));
        schedule.setStartAt(rs.getTime("StartAt").toLocalTime());
        schedule.setEndAt(rs.getTime("EndAt").toLocalTime());
        schedule.setActive(rs.getString("Active"));
        schedule.setSessionTitle(rs.getString("sessionTitle"));
        
        return schedule;
    }

    /**
     * Tìm tất cả các lịch dạy của một gia sư cụ thể.
     */
    public List<Schedule> findByTutorId(String tutorId) {
    String sql = "SELECT sch.*, s.Title AS sessionTitle FROM Schedule sch " +
                 "JOIN Session s ON sch.SessionId = s.SessionId " +
                 "WHERE s.TutorId = ? AND sch.Active = 'On'";
    return jdbcTemplate.query(sql, this::mapRowToSchedule, tutorId);
}
}
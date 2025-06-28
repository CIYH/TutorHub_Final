/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.ChonLichHoc;
import com.uef.model.DSKhoaHoc;
import com.uef.model.ScheduleDTO;
import com.uef.model.Tu_Session;
import com.uef.model.SessionWithSubName;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class Tu_SessionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Tu_Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tu_Session session = new Tu_Session();
        session.setSessionId(rs.getInt("SessionId"));
        session.setFee(rs.getInt("Fee"));
        session.setSubId(rs.getInt("SubId"));
        session.setSe_Description(rs.getString("Se_Description"));
        session.setTitle(rs.getString("Title"));
        session.setDuration(rs.getInt("Duration"));
        session.setTutorId(rs.getString("TutorId"));
        session.setSe_Status(rs.getString("Se_Status"));
        session.setActive(rs.getString("Active"));
        return session;
    }

    public List<ChonLichHoc> findAllSchedule(String tutorId) {
        String sql = "select Su_Name, StudyDate, StartAt, EndAt "
                + "from Schedule, Session, Subject, Tutor "
                + "where Tutor.TutorId = Session.TutorId and Tutor.TutorId = ? and Session.SubId = Subject.SubId and Session.SessionId = Schedule.SessionId;";
        return jdbcTemplate.query(sql, new ChonLichHocRowMapper(), tutorId);
    }

    public int insertSession(Tu_Session session, String tutorId) {
        String sql = "INSERT INTO Session (Fee, SubId, Se_Description, Title, Duration, TutorId, Se_Status, Active) VALUES (?, ?, ?, ?, ?, ?, 'Unlock', 'On')";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, session.getFee());
            ps.setInt(2, session.getSubId());
            ps.setString(3, session.getSe_Description());
            ps.setString(4, session.getTitle());
            ps.setInt(5, session.getDuration());
            ps.setString(6, tutorId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void insertSchedule(int sessionId, ScheduleDTO schedule) {
        String sql = "INSERT INTO Schedule (SessionId, StudyDate, StartAt, EndAt, Active) VALUES (?, ?, ?, ?, 'On')";
        jdbcTemplate.update(sql, sessionId, schedule.getDay(), schedule.getStart(), schedule.getEnd());
    }

    public List<DSKhoaHoc> findAllSessionWithSubName(String tutorId) {
        String sql = "select SessionId, Title, Su_Name, Duration, Se_Status from Session, Subject where Session.SubId = Subject.SubId and Session.Active = 'On' and TutorID = ?;";
        return jdbcTemplate.query(sql, new DSKhoaHocRowMapper(), tutorId);
    }

    public List<DSKhoaHoc> searchSessionWithSubNameByTitle(String title, String tutorId) {
        String sql = "select SessionId, Title, Su_Name, Duration, Se_Status from Session, Subject where Session.SubId = Subject.SubId and Session.Active = 'On' and TutorID = ? and Session.Title like ?;";
        return jdbcTemplate.query(sql, new DSKhoaHocRowMapper(), tutorId, "%" + title + "%");
    }

    public int switchActiveSessionById(int sessionId) {
        String sql = "UPDATE Session SET Active = 'Off' WHERE SessionId = ?";
        return jdbcTemplate.update(sql, sessionId);
    }

    public int deleteSchedulesBySessionId(int sessionId) {
        String sql = "DELETE FROM Schedule WHERE SessionId = ?";
        return jdbcTemplate.update(sql, sessionId);
    }

    public int updateSession(Tu_Session session) {
        String sql = "UPDATE Session SET Title = ?, SubId = ?, Duration = ?, Fee = ?, Se_Description = ? WHERE SessionId = ?";
        return jdbcTemplate.update(sql,
                session.getTitle(),
                session.getSubId(),
                session.getDuration(),
                session.getFee(),
                session.getSe_Description(),
                session.getSessionId());
    }

    public SessionWithSubName findSessionWithSubNameById(int sessionId) {
        String sql = "select ses.SessionId, ses.Title, sub.Su_Name, ses.Duration, ses.Se_Status, ses.SubId, ses.Fee, ses.Se_Description "
                + "from Session AS ses, Subject AS sub "
                + "where ses.SubId = sub.SubId and ses.SessionId = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new SessionWithSubNameRowMapper(), sessionId);
        } catch (Exception e) {
            return null; // Trả về null nếu không tìm thấy
        }
    }

    public List<ScheduleDTO> findSchedulesBySessionId(int sessionId) {
        // Câu lệnh SQL được cập nhật với JOIN đến Session và Subject
        String sql = "SELECT s.ScheduleId, s.StudyDate, s.StartAt, s.EndAt, sub.Su_Name "
                + "FROM Schedule s "
                + "JOIN Session ses ON s.SessionId = ses.SessionId "
                + "JOIN Subject sub ON ses.SubId = sub.SubId "
                + "WHERE s.SessionId = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ScheduleDTO dto = new ScheduleDTO();
            Time startAt = rs.getTime("StartAt");
            Time endAt = rs.getTime("EndAt");

            dto.setId(rs.getLong("ScheduleId"));
            dto.setDay(rs.getString("StudyDate"));

            // Định dạng lại thời gian để đảm bảo luôn là HH:mm
            if (startAt != null) {
                dto.setStart(startAt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            if (endAt != null) {
                dto.setEnd(endAt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }

            // Lấy tên môn học từ kết quả JOIN và set vào DTO
            dto.setSubjectName(rs.getString("Su_Name"));

            return dto;
        }, sessionId);
    }

    public List<ScheduleDTO> findOtherSchedulesForTutor(String tutorId, int currentSessionId) {
        String sql = "SELECT s.ScheduleId, s.StudyDate, s.StartAt, s.EndAt, sub.Su_Name "
                + "FROM Schedule s "
                + "JOIN Session ses ON s.SessionId = ses.SessionId "
                + "JOIN Subject sub ON ses.SubId = sub.SubId "
                + "WHERE ses.TutorId = ? AND s.SessionId != ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ScheduleDTO dto = new ScheduleDTO();
            Time startAt = rs.getTime("StartAt");
            Time endAt = rs.getTime("EndAt");
            dto.setId(rs.getLong("ScheduleId"));
            dto.setDay(rs.getString("StudyDate"));
            dto.setStart(startAt != null ? startAt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
            dto.setEnd(endAt != null ? endAt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "");
            dto.setSubjectName(rs.getString("Su_Name"));
            return dto;
        }, tutorId, currentSessionId);
    }
}

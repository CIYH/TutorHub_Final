package com.uef.repository;

import com.uef.model.MySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySessionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MySession mapRow(ResultSet rs, int rowNum) throws SQLException {
        MySession session = new MySession();
        session.setBookingId(rs.getInt("BookingId"));
        session.setBookingStatus(rs.getString("Bs_Status"));
        session.setCourseTitle(rs.getString("courseTitle"));
        session.setCourseDescription(rs.getString("courseDescription"));
        session.setSubjectName(rs.getString("subjectName"));
        session.setTutorName(rs.getString("tutorName"));
        session.setLocation(rs.getString("Location"));
        session.setSessionStart(rs.getTimestamp("SessionStart"));
        session.setSessionEnd(rs.getTimestamp("SessionEnd"));
        return session;
    }

    public List<MySession> findSessionsByStudentId(String studentId, String searchTerm, Integer subjectId) {
        List<Object> params = new ArrayList<>();

        // Câu lệnh SQL cơ sở
        String baseSql = "SELECT bs.BookingId, bs.Bs_Status, s.Title AS courseTitle, "
                + "s.Se_Description AS courseDescription, sub.Su_Name AS subjectName, "
                + "p.p_name AS tutorName, bsd.Location, bsd.SessionStart, bsd.SessionEnd "
                + "FROM Booking_Session bs "
                + "JOIN Session s ON bs.SessionId = s.SessionId "
                + "JOIN people p ON s.TutorId = p.Id "
                + "JOIN Subject sub ON s.SubId = sub.SubId "
                + "LEFT JOIN Booking_Session_Detail bsd ON bs.BookingId = bsd.BookingId "
                + "WHERE bs.StudentId = ? ";

        params.add(studentId);

        StringBuilder sqlBuilder = new StringBuilder(baseSql);

        // Nếu có từ khóa tìm kiếm, thêm điều kiện
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            sqlBuilder.append("AND (p.p_name LIKE ? OR s.Title LIKE ?) ");
            String searchParam = "%" + searchTerm.trim() + "%";
            params.add(searchParam);
            params.add(searchParam);
        }
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            sqlBuilder.append("AND (p.p_name LIKE ? OR s.Title LIKE ?) ");
            String searchParam = "%" + searchTerm.trim() + "%";
            params.add(searchParam);
            params.add(searchParam);
        }
        if (subjectId != null && subjectId > 0) {
            sqlBuilder.append("AND s.SubId = ? ");
            params.add(subjectId);
        }
        return jdbcTemplate.query(sqlBuilder.toString(), this::mapRow, params.toArray());
    }
    public MySession findDetailByBookingId(int bookingId) {
    String sql = "SELECT bs.BookingId, bs.Bs_Status, s.Title AS courseTitle, " +
                 "s.Se_Description AS courseDescription, sub.Su_Name AS subjectName, " +
                 "p.p_name AS tutorName, bsd.Location, bsd.SessionStart, bsd.SessionEnd " +
                 "FROM Booking_Session bs " +
                 "JOIN Session s ON bs.SessionId = s.SessionId " +
                 "JOIN people p ON s.TutorId = p.Id " +
                 "JOIN Subject sub ON s.SubId = sub.SubId " +
                 "LEFT JOIN Booking_Session_Detail bsd ON bs.BookingId = bsd.BookingId " +
                 "WHERE bs.BookingId = ?";
    try {
        return jdbcTemplate.queryForObject(sql, this::mapRow, bookingId);
    } catch (EmptyResultDataAccessException e) {
        return null; 
    }
}
}

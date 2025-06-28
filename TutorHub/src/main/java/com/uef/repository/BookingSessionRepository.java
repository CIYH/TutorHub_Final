package com.uef.repository;

import com.uef.model.BookingSessionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class BookingSessionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(BookingSessionDetail detail, int sessionId, String studentId) {

        // 1. Insert vào bảng Booking_Session và lấy BookingId tự động tăng
        String bookingSql = "INSERT INTO Booking_Session (StudentId, SessionId, Bs_Status, Active) VALUES (?, ?, NULL, 'On')";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(bookingSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, studentId);
            ps.setInt(2, sessionId);
            return ps;
        }, keyHolder);

        // Lấy ID vừa được sinh ra một cách an toàn
        int bookingId = 0;
        if (keyHolder.getKey() != null) {
            bookingId = keyHolder.getKey().intValue();
        } else {
            // Xử lý trường hợp không lấy được key (ví dụ: ném ra một exception)
            throw new IllegalStateException("Không thể lấy được Booking ID sau khi insert.");
        }

        // 2. Insert vào bảng Booking_Session_Detail với BookingId vừa nhận được
        String detailSql = "INSERT INTO Booking_Session_Detail (BookingId, Location, Active) VALUES (?, ?, 'On')";
        jdbcTemplate.update(detailSql, bookingId, detail.getLocation());

        return bookingId;
    }

    public void deleteById(int bookingId) {
        String sql = "DELETE FROM Booking_Session WHERE BookingId = ?";
        jdbcTemplate.update(sql, bookingId);
    }
}

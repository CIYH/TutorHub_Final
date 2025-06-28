/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

/**
 *
 * @author qnhat
 */
import com.uef.model.Admin;
import com.uef.model.Tutor;
import com.uef.model.People;
import com.uef.model.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Ad_TutorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String BASE_SQL = "SELECT t.Fee, t.Rating, t.Education, t.Experience, "
            + "p.Id, p.p_name, p.email, p.address, p.phonenumber, p.gender, "
            + "s.SubId, s.Su_Name AS SubName "
            + "FROM Tutor t "
            + "JOIN people p ON t.TutorId = p.Id "
            + "JOIN Subject s ON t.SubId = s.SubId ";

    private static final class TutorRowMapper implements RowMapper<Tutor> {

        @Override
        public Tutor mapRow(ResultSet rs, int rowNum) throws SQLException {

            Subject subject = new Subject();
            subject.setSubId(rs.getInt("SubId"));
            subject.setSuName(rs.getString("SubName"));

            Tutor tutor = new Tutor();
            tutor.setId(rs.getString("Id"));
            tutor.setpName(rs.getString("p_name"));
            tutor.setEmail(rs.getString("email"));
            tutor.setAddress(rs.getString("address"));
            tutor.setGender(rs.getString("gender"));
            tutor.setPhonenumber(rs.getString("phonenumber"));
            tutor.setFee(rs.getInt("Fee"));
            tutor.setRating(rs.getString("Rating"));
            tutor.setEducation(rs.getString("Education"));
            tutor.setExperience(rs.getString("Experience"));
            tutor.setSubject(subject);

            return tutor;
        }
    }

    public List<Tutor> findAll() {
        return jdbcTemplate.query(BASE_SQL + "WHERE p.Active = 'On'", new TutorRowMapper());
    }

    public Tutor findById(String id) {
        String sql = BASE_SQL + "WHERE p.p_role = 'tutor' AND p.Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new TutorRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            // Trả về null nếu không có kết quả nào được tìm thấy
            return null;
        }
    }

    public void save(Tutor tutor) {
        // Câu lệnh SQL để chèn dữ liệu vào bảng Tutor.
        // Các cột được liệt kê rõ ràng để đảm bảo thứ tự chính xác.
        String sqlPeople = "INSERT INTO people (Id, p_name, email, address, p_role, gender, phonenumber, Active, password) VALUES (?, ?, ?, ?, 'tutor', ?, ?, 'On', ?)";

        // Thực thi câu lệnh chèn cho bảng 'people'
        jdbcTemplate.update(sqlPeople,
                tutor.getId(), // Id của người dùng, cũng sẽ là TutorId
                tutor.getpName(), // Tên của người dùng
                tutor.getEmail(), // Email
                tutor.getAddress(), // Địa chỉ
                tutor.getGender(), // Giới tính
                tutor.getPhonenumber(), // Số điện thoại
                tutor.getPassword() // Mật khẩu (nên được mã hóa trước khi lưu)
        );

        // --- BƯỚC 2: Chèn dữ liệu vào bảng 'Tutor' ---
        // Câu lệnh SQL để tạo bản ghi gia sư, liên kết với bản ghi 'people' vừa tạo.
        String sqlTutor = "INSERT INTO Tutor (TutorId, Fee, Rating, SubId, Education, Experience) VALUES (?, ?, ?, ?, ?, ?)";

        // Thực thi câu lệnh chèn cho bảng 'Tutor'
        jdbcTemplate.update(sqlTutor,
                tutor.getId(), // Khóa ngoại, tham chiếu đến people.Id
                tutor.getFee(), // Phí dạy học
                tutor.getRating(), // Đánh giá
                tutor.getSubject().getSubId(), // ID môn học
                tutor.getEducation(), // Học vấn
                tutor.getExperience() // Kinh nghiệm
        );
    }

    public void update(Tutor tutor) {
        // --- BƯỚC 1: Cập nhật bảng 'people' ---
        String sqlPeople = "UPDATE people SET p_name = ?, email = ?, address = ?, gender = ?, phonenumber = ? WHERE Id = ?";
        jdbcTemplate.update(sqlPeople,
                tutor.getpName(),
                tutor.getEmail(),
                tutor.getAddress(),
                tutor.getGender(),
                tutor.getPhonenumber(),
                tutor.getId() // Điều kiện WHERE
        );

        // --- BƯỚC 2: Cập nhật bảng 'Tutor' ---
        String sqlTutor = "UPDATE Tutor SET Fee = ?, Rating = ?, SubId = ?, Education = ?, Experience = ? WHERE TutorId = ?";
        jdbcTemplate.update(sqlTutor,
                tutor.getFee(),
                tutor.getRating(),
                tutor.getSubject().getSubId(),
                tutor.getEducation(),
                tutor.getExperience(),
                tutor.getId() // Điều kiện WHERE
        );
    }

    public void softDelete(String tutorId) {
        String sql = "UPDATE people SET Active = 'Off' WHERE Id = ?";
        jdbcTemplate.update(sql, tutorId);
    }
    
    
    public int countTutors() {
        String sql = "SELECT COUNT(*) FROM Tutor";
        try {
            // queryForObject có thể trả về null nếu bảng rỗng, nên cần kiểm tra
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            return (count != null) ? count : 0;
        } catch (Exception e) {
            // Trong thực tế, bạn nên ghi log lỗi ở đây
            // e.printStackTrace(); 
            return 0; // Trả về 0 nếu có lỗi xảy ra
        }
    }

}

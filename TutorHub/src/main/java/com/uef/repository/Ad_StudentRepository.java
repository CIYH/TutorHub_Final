package com.uef.repository;

import com.uef.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Ad_StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * SỬA LỖI: Câu lệnh SQL đã được sửa lại để lấy dữ liệu chính xác. 1. Lấy dữ
     * liệu từ bảng 'people' (p). 2. JOIN với bảng 'Student' (s) để đảm bảo
     * người đó là một sinh viên. 3. Chọn tường minh các cột từ bảng 'people' để
     * tránh lỗi.
     */
    private final String BASE_SQL = "SELECT p.Id, p.p_name, p.email, p.address, p.gender, p.phonenumber, p.Active, p.p_role, p.password "
            + "FROM people p "
            + "JOIN Student s ON p.Id = s.StudentId ";

    private static final class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getString("Id"));
            student.setpName(rs.getString("p_name"));
            student.setEmail(rs.getString("email"));
            student.setAddress(rs.getString("address"));
            student.setGender(rs.getString("gender"));
            student.setPhonenumber(rs.getString("phonenumber"));
            student.setActive(rs.getString("Active"));
            student.setpRole(rs.getString("p_role"));
            student.setPassword(rs.getString("password")); // Nạp cả mật khẩu để dùng cho chức năng sửa
            return student;
        }
    }

    /**
     * SỬA LỖI: Đã thêm khoảng trắng và điều kiện p_role = 'user'
     */
    public List<Student> findAll() {
        String sql = BASE_SQL + " WHERE p.Active = 'On' AND p.p_role = 'user'";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    /**
     * SỬA LỖI: Đã thêm khoảng trắng
     */
    public Student findById(String id) {
        String sql = BASE_SQL + " WHERE p.Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // ... các phương thức save, update, softDelete không thay đổi ...
    public void save(Student student) {
        String sqlPeople = "INSERT INTO people (Id, p_name, email, address, p_role, gender, phonenumber, Active, password) VALUES (?, ?, ?, ?, 'user', ?, ?, 'On', ?)";
        jdbcTemplate.update(sqlPeople, student.getId(), student.getpName(), student.getEmail(), student.getAddress(), student.getGender(), student.getPhonenumber(), student.getPassword());
        String sqlStudent = "INSERT INTO Student (StudentId) VALUES (?)";
        jdbcTemplate.update(sqlStudent, student.getId());
    }

    public void update(Student student) {
        String sqlPeople = "UPDATE people SET p_name = ?, email = ?, address = ?, gender = ?, phonenumber = ? WHERE Id = ?";
        jdbcTemplate.update(sqlPeople, student.getpName(), student.getEmail(), student.getAddress(), student.getGender(), student.getPhonenumber(), student.getId());
    }

    public void softDelete(String studentId) {
        String sql = "UPDATE people SET Active = 'Off' WHERE Id = ?";
        jdbcTemplate.update(sql, studentId);
    }
    
     public int countStudents() {
        String sql = "SELECT COUNT(*) FROM people WHERE p_role = 'user'";
        // Sử dụng queryForObject để lấy một giá trị duy nhất (Integer)
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}

package com.uef.repository;

import com.uef.model.Stu_People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository này chịu trách nhiệm cho tất cả các thao tác CRUD (Tạo, Đọc, Cập nhật, Xóa)
 * trên bảng 'people'.
 */
@Repository
public class PeopleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Ánh xạ một hàng từ ResultSet sang đối tượng People
    private Stu_People mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Ánh xạ đầy đủ thông tin vào đối tượng People
        Stu_People people = new Stu_People();
        people.setId(rs.getString("Id"));
        people.setpName(rs.getString("p_name"));
        people.setEmail(rs.getString("email"));
        people.setAddress(rs.getString("address"));
        people.setpRole(rs.getString("p_role"));
        people.setGender(rs.getString("gender"));
        people.setPhonenumber(rs.getString("phonenumber"));
        people.setActive(rs.getString("Active"));
        people.setPassword(rs.getString("password"));
        return people;
    }

    // Lấy tất cả người dùng
    public List<Stu_People> findAll() {
        String sql = "SELECT * FROM people";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    // Tìm người dùng bằng ID
    public Stu_People findById(String id) {
        String sql = "SELECT * FROM people WHERE Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null nếu không tìm thấy
        }
    }
    
    /**
     * Tìm một người dùng dựa trên địa chỉ email.
     * @param email Email cần tìm.
     * @return Đối tượng People nếu tìm thấy, ngược lại trả về null.
     */
    public Stu_People findByEmail(String email) {
        String sql = "SELECT * FROM people WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRow, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Tìm tên của một người dùng dựa trên ID
    public String findNameById(String id) {
        String sql = "SELECT p_name FROM people WHERE Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Lưu một người dùng mới
    public void save(Stu_People people) {
        String sql = "INSERT INTO people (Id, p_name, email, address, p_role, gender, phonenumber, Active, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                people.getId(),
                people.getpName(),
                people.getEmail(),
                people.getAddress(),
                people.getpRole(),
                people.getGender(),
                people.getPhonenumber(),
                people.getActive(),
                people.getPassword());
    }

    // Cập nhật thông tin người dùng
    public void update(Stu_People people) {
        String sql = "UPDATE people SET p_name = ?, email = ?, address = ?, p_role = ?, gender = ?, phonenumber = ?, Active = ?, password = ? WHERE Id = ?";
        jdbcTemplate.update(sql,
                people.getpName(),
                people.getEmail(),
                people.getAddress(),
                people.getpRole(),
                people.getGender(),
                people.getPhonenumber(),
                people.getActive(),
                people.getPassword(),
                people.getId());
    }

    // Xóa người dùng bằng ID
    public void delete(String id) {
        String sql = "DELETE FROM people WHERE Id = ?";
        jdbcTemplate.update(sql, id);
    }
}
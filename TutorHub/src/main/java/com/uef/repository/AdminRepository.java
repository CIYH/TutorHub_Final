package com.uef.repository;

import com.uef.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * SỬA LỖI: Câu lệnh SQL đã được sửa lại để lấy dữ liệu chính xác. 1. Lấy dữ
     * liệu từ bảng 'people' (p). 2. JOIN với bảng 'Admin' (a) để đảm bảo người
     * đó là một admin. 3. Chọn tất cả các cột từ bảng 'people' vì nó chứa toàn
     * bộ thông tin cần thiết.
     */
    private final String BASE_SQL = "SELECT p.* "
            + "FROM people p "
            + "JOIN Admin a ON p.Id = a.AdminId ";

    private static final class AdminRowMapper implements RowMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            // Lấy dữ liệu từ các cột của bảng people
            admin.setId(rs.getString("Id"));
            admin.setpName(rs.getString("p_name"));
            admin.setEmail(rs.getString("email"));
            admin.setAddress(rs.getString("address"));
            admin.setGender(rs.getString("gender"));
            admin.setPhonenumber(rs.getString("phonenumber"));
            admin.setActive(rs.getString("Active"));
            admin.setPassword(rs.getString("password"));
            admin.setpRole(rs.getString("p_role")); // Đảm bảo gán cả role
            return admin;
        }
    }

    /**
     * Lấy danh sách tất cả các admin đang hoạt động.
     *
     * @return List<Admin>
     */
    public List<Admin> findAllActive() {
        String sql = BASE_SQL + " WHERE p.Active = 'On'";
        return jdbcTemplate.query(sql, new AdminRowMapper());
    }

    /**
     * Tìm một admin theo ID.
     *
     * @param id
     * @return Admin object hoặc null
     */
    public Admin findById(String id) {
        String sql = BASE_SQL + " WHERE p.Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AdminRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Lưu một admin mới. Thao tác này bao gồm việc tạo bản ghi ở cả bảng
     * 'people' và 'Admin'.
     *
     * @param admin
     */
    public void save(Admin admin) {
        // Bước 1: Tạo bản ghi trong bảng people
        String sqlPeople = "INSERT INTO people (Id, p_name, email, address, p_role, gender, phonenumber, Active, password) "
                + "VALUES (?, ?, ?, ?, 'admin', ?, ?, 'On', ?)";
        jdbcTemplate.update(sqlPeople,
                admin.getId(),
                admin.getpName(),
                admin.getEmail(),
                admin.getAddress(),
                admin.getGender(),
                admin.getPhonenumber(),
                admin.getPassword());

        // Bước 2: Tạo bản ghi liên kết trong bảng Admin
        String sqlAdmin = "INSERT INTO Admin (AdminId) VALUES (?)";
        jdbcTemplate.update(sqlAdmin, admin.getId());
    }

    /**
     * Cập nhật thông tin admin (chỉ cần cập nhật bảng people).
     *
     * @param admin
     */
    public void update(Admin admin) {
        String sql = "UPDATE people SET p_name = ?, email = ?, address = ?, gender = ?, "
                + "phonenumber = ?, Active = ?, password = ? WHERE Id = ? AND p_role = 'admin'";
        jdbcTemplate.update(sql,
                admin.getpName(),
                admin.getEmail(),
                admin.getAddress(),
                admin.getGender(),
                admin.getPhonenumber(),
                admin.getActive(),
                admin.getPassword(),
                admin.getId());
    }

    /**
     * Xóa mềm một admin.
     *
     * @param id
     */
    public void softDelete(String id) {
        String sql = "UPDATE people SET Active = 'Off' WHERE Id = ? AND p_role = 'admin'";
        jdbcTemplate.update(sql, id);
    }
}

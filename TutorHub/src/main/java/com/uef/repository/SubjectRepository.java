/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.Admin;
import com.uef.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SubjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Câu lệnh SQL cơ sở với JOIN để lấy thông tin admin. Dùng cho cả findAll và findById.
    private final String BASE_SQL = "SELECT s.SubId, s.Su_Name, s.Su_Description, s.Active AS SubjectActive, "
            + "p.Id AS AdminId, p.p_name AS AdminName "
            + "FROM Subject s "
            + "JOIN Admin a ON s.AdminId = a.AdminId "
            + "JOIN people p ON a.AdminId = p.Id ";

    /**
     * RowMapper để ánh xạ kết quả từ ResultSet sang đối tượng Subject, bao gồm
     * cả đối tượng Admin.
     */
    private static final class SubjectRowMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            // Tạo và điền thông tin cho đối tượng Admin
            Admin admin = new Admin();
            admin.setId(rs.getString("AdminId"));
            admin.setpName(rs.getString("AdminName"));

            // Tạo và điền thông tin cho đối tượng Subject
            Subject subject = new Subject();
            subject.setSubId(rs.getInt("SubId"));
            subject.setSuName(rs.getString("Su_Name"));
            subject.setSuDescription(rs.getString("Su_Description"));
            subject.setActive(rs.getString("SubjectActive"));

            // Gán đối tượng Admin cho môn học
            subject.setAdmin(admin);

            return subject;
        }
    }

    /**
     * Lấy tất cả các môn học cùng với thông tin người tạo.
     *
     * @return danh sách các môn học.
     */
    public List<Subject> findAll() {
        return jdbcTemplate.query(BASE_SQL + "WHERE s.Active = 'On'", new SubjectRowMapper());
    }

    /**
     * Tìm một môn học bằng ID, bao gồm thông tin người tạo.
     *
     * @param id ID của môn học.
     * @return đối tượng Subject hoặc null nếu không tìm thấy.
     */
    public Subject findById(int id) {
        String sql = BASE_SQL + "WHERE s.SubId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new SubjectRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            // Trả về null nếu không có kết quả nào được tìm thấy
            return null;
        }
    }

    /**
     * Lưu một môn học mới vào cơ sở dữ liệu.
     *
     * @param subject đối tượng môn học cần lưu.
     */
    public void save(Subject subject) {
        String sql = "INSERT INTO Subject (AdminId, Su_Name, Su_Description, Active) VALUES (?, ?, ?, 'On')";
        jdbcTemplate.update(sql,
                subject.getAdmin().getId(), // Lấy ID từ đối tượng Admin
                subject.getSuName(),
                subject.getSuDescription());
    }

    /**
     * Cập nhật thông tin của một môn học đã có.
     *
     * @param subject đối tượng môn học với thông tin đã được cập nhật.
     */
    public void update(Subject subject) {
        String sql = "UPDATE Subject SET AdminId = ?, Su_Name = ?, Su_Description = ? WHERE SubId = ?";
        jdbcTemplate.update(sql,
                subject.getAdmin().getId(), // Lấy ID từ đối tượng Admin
                subject.getSuName(),
                subject.getSuDescription(),
                subject.getSubId());
    }

    /**
     * Xóa mềm một môn học bằng cách cập nhật trạng thái Active thành 'Off'.
     *
     * @param id ID của môn học cần xóa.
     */
    public void softDelete(int id) {
        String sql = "UPDATE Subject SET Active = 'Off' WHERE SubId = ?";
        jdbcTemplate.update(sql, id);
    }
}

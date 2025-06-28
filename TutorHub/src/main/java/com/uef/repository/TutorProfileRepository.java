/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.TutorProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class TutorProfileRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public TutorProfile findById(String tutorId) {
        String sql = "SELECT p.Id, p.p_name, p.email, p.address, p.gender, p.phonenumber, t.Education, t.Experience, s.Su_Name " +
                     "FROM people p " +
                     "JOIN tutor t ON p.Id = t.TutorId " +
                     "LEFT JOIN subject s ON t.SubId = s.SubId " + // Dùng LEFT JOIN phòng trường hợp gia sư chưa có môn học
                     "WHERE p.Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{tutorId}, (rs, rowNum) -> {
                TutorProfile tutor = new TutorProfile();
                tutor.setId(rs.getString("Id"));
                tutor.setP_name(rs.getString("p_name"));
                tutor.setEmail(rs.getString("email"));
                tutor.setAddress(rs.getString("address"));
                tutor.setGender(rs.getString("gender"));
                tutor.setPhonenumber(rs.getString("phonenumber"));
                tutor.setEducation(rs.getString("Education"));
                tutor.setExperience(rs.getString("Experience"));
                tutor.setSubjectName(rs.getString("Su_Name"));
                return tutor;
            });
        } catch (Exception e) {
            // Trả về null hoặc xử lý ngoại lệ nếu không tìm thấy
            return null;
        }
    }

    /**
     * Cập nhật thông tin cơ bản của gia sư trong bảng people
     */
    public int update(TutorProfile tutor) {
        String sql = "UPDATE people SET p_name = ?, email = ?, address = ?, gender = ?, phonenumber = ? WHERE Id = ?";
        return jdbcTemplate.update(sql, 
                tutor.getP_name(), 
                tutor.getEmail(), 
                tutor.getAddress(), 
                tutor.getGender(), 
                tutor.getPhonenumber(), 
                tutor.getId());
    }
}

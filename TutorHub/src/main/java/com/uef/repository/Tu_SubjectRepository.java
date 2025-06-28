/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.ChonLichHoc;
import com.uef.model.Tu_Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class Tu_SubjectRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private Tu_Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tu_Subject subject = new Tu_Subject();
        subject.setSubId(rs.getInt("SubId"));
        subject.setAdminId(rs.getString("AdminId"));
        subject.setSu_Name(rs.getString("Su_Name"));
        subject.setSu_Description(rs.getString("Su_Description"));
        return subject;
    }
    
    public List<Tu_Subject> findAll(){
        String sql = "SELECT * FROM Subject";
        return jdbcTemplate.query(sql, this::mapRow);
    }
    
}

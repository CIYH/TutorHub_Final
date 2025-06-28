/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.DSKhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class DSKhoaHocRowMapper implements RowMapper<DSKhoaHoc>{
    @Override
    public DSKhoaHoc mapRow(ResultSet rs, int rowNum) throws SQLException {
        DSKhoaHoc dto = new DSKhoaHoc();
        dto.setSessionId(rs.getInt("SessionId"));
        dto.setTitle(rs.getString("Title"));
        dto.setSu_Name(rs.getString("Su_Name"));
        dto.setDuration(rs.getInt("Duration"));
        dto.setSe_Status(rs.getString("Se_Status"));
        return dto;
    }
}

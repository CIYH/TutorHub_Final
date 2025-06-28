/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.ChonLichHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class ChonLichHocRowMapper implements RowMapper<ChonLichHoc>{  
    @Override
    public ChonLichHoc mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChonLichHoc dto = new ChonLichHoc();
        dto.setSubjectName(rs.getString("Su_Name"));
        dto.setDay(rs.getString("StudyDate"));
        dto.setStart(rs.getTime("StartAt").toLocalTime());
        dto.setEnd(rs.getTime("EndAt").toLocalTime());
        return dto;
    }
}

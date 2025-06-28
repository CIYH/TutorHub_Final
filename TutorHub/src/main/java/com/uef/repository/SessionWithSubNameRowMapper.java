/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.SessionWithSubName;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class SessionWithSubNameRowMapper implements RowMapper<SessionWithSubName> {
    @Override
    public SessionWithSubName mapRow(ResultSet rs, int rowNum) throws SQLException {
        SessionWithSubName dto = new SessionWithSubName();
        dto.setSessionId(rs.getInt("SessionId"));
        dto.setTitle(rs.getString("Title"));
        dto.setSu_Name(rs.getString("Su_Name"));
        dto.setDuration(rs.getInt("Duration"));
        dto.setSe_Status(rs.getString("Se_Status"));
        dto.setSubId(rs.getInt("SubId"));
        dto.setFee(rs.getInt("Fee"));
        dto.setSe_Description(rs.getString("Se_Description"));
        return dto;
    }
}

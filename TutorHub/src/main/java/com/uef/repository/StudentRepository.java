/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.repository;

import com.uef.model.Stu_Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Stu_Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stu_Student student = new Stu_Student();
        student.setId(rs.getString("StudentId"));
        return student;
    }

    public List<Stu_Student> findAll() {
        String sql = "SELECT * FROM Student";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Stu_Student findById(String id) {
        String sql = "SELECT * FROM Student WHERE StudentId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRow, id);
        } catch (Exception e) {
            return null;
        }
    }

    public void save(String studentId) {
        String sql = "INSERT INTO Student (StudentId) VALUES (?)";
        jdbcTemplate.update(sql, studentId);
    }

    public void delete(String id) {
        String sql = "DELETE FROM Student WHERE StudentId = ?";
        jdbcTemplate.update(sql, id);
    }
}

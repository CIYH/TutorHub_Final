package com.uef.repository;

import com.uef.model.TutorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TutorDetailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_TUTOR_DETAILS_SQL =
            "SELECT p.Id, p.p_name, p.email, p.address, p.p_role, p.gender, p.phonenumber, p.Active, " +
            "t.Fee, t.Rating, t.SubId, t.Education, t.Experience, " +
            "s.Su_Name AS subjectName " +
            "FROM people p JOIN Tutor t ON p.Id = t.TutorId " +
            "LEFT JOIN Subject s ON t.SubId = s.SubId";

    private TutorDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        TutorDetail tutor = new TutorDetail();
        tutor.setId(rs.getString("Id"));
        tutor.setpName(rs.getString("p_name"));
        tutor.setEmail(rs.getString("email"));
        tutor.setAddress(rs.getString("address"));
        tutor.setpRole(rs.getString("p_role"));
        tutor.setGender(rs.getString("gender"));
        tutor.setPhonenumber(rs.getString("phonenumber"));
        tutor.setActive(rs.getString("Active"));
        tutor.setFee(rs.getInt("Fee"));
        tutor.setRating(rs.getString("Rating"));
        tutor.setSubId(rs.getObject("SubId", Integer.class));
        tutor.setEducation(rs.getString("Education"));
        tutor.setExperience(rs.getString("Experience"));
        tutor.setSubjectName(rs.getString("subjectName"));
        return tutor;
    }

    public List<TutorDetail> filter(Integer subjectId, String tutorName) {
        StringBuilder sql = new StringBuilder(FIND_TUTOR_DETAILS_SQL);
        List<Object> params = new ArrayList<>();
        sql.append(" WHERE 1=1");
        if (subjectId != null && subjectId > 0) {
            sql.append(" AND t.SubId = ?");
            params.add(subjectId);
        }
        if (tutorName != null && !tutorName.trim().isEmpty()) {
            sql.append(" AND p.p_name LIKE ?");
            params.add("%" + tutorName.trim() + "%");
        }
        return jdbcTemplate.query(sql.toString(), this::mapRow, params.toArray());
    }

    public TutorDetail findByIdWithDetails(String id) {
        String sql = FIND_TUTOR_DETAILS_SQL + " WHERE p.Id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /**
     * PHƯƠNG THỨC MỚI: Đếm tổng số buổi học (Session) của một gia sư.
     * @param tutorId ID của gia sư.
     * @return Tổng số buổi học.
     */
    public int countSessionsByTutorId(String tutorId) {
        String sql = "SELECT COUNT(*) FROM Session WHERE TutorId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tutorId);
        // Trả về 0 nếu kết quả là null (tránh NullPointerException)
        return (count != null) ? count : 0;
    }
    public void updateRating(String tutorId, String newRating) {
    String sql = "UPDATE Tutor SET Rating = ? WHERE TutorId = ?";
    jdbcTemplate.update(sql, newRating, tutorId);
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.uef.dto;

import com.uef.dto.DailyCheckInDTO;
import java.util.List;

/**
 * Lớp này dùng để chứa tất cả các thông tin thống kê cần hiển thị trên Dashboard.
 */
public class DashboardStatsDTO {
    private long tutorCount;
    private long studentCount;
    private long subjectCount;
    private List<DailyCheckInDTO> dailyCheckIns;

    // Getters and Setters
    public long getTutorCount() { return tutorCount; }
    public void setTutorCount(long tutorCount) { this.tutorCount = tutorCount; }

    public long getStudentCount() { return studentCount; }
    public void setStudentCount(long studentCount) { this.studentCount = studentCount; }

    public long getSubjectCount() { return subjectCount; }
    public void setSubjectCount(long subjectCount) { this.subjectCount = subjectCount; }

    public List<DailyCheckInDTO> getDailyCheckIns() { return dailyCheckIns; }
    public void setDailyCheckIns(List<DailyCheckInDTO> dailyCheckIns) { this.dailyCheckIns = dailyCheckIns; }
}

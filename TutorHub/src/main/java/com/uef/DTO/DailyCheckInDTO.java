// File: src/main/java/com/uef/dto/DashboardStatsDTO.java
package com.uef.dto;



// File: src/main/java/com/uef/dto/DailyCheckInDTO.java


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp này đại diện cho thông tin một buổi học cần điểm danh trong ngày.
 */
public class DailyCheckInDTO {
    private String studentName;
    private String subjectName;
    private String sessionTitle;
    private LocalTime startTime;
    private String status;

    // Getters and Setters
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getSessionTitle() { return sessionTitle; }
    public void setSessionTitle(String sessionTitle) { this.sessionTitle = sessionTitle; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Phương thức tiện ích để định dạng thời gian sang chuỗi HH:mm
    public String getFormattedStartTime() {
        if (this.startTime == null) return "";
        return this.startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}

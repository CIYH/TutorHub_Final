/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author qnhat
 */
public class Schedule implements Serializable {

    private int scheduleId;

    @NotNull(message = "SessionId không được để trống")
    private Integer sessionId; // FK tới Subject (SubId)

    @NotBlank(message = "AdminId không được để trống")
    private String adminId; // FK tới Admin

    @NotNull(message = "Ngày học không được để trống")
    private String studyDate;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    private LocalTime startAt;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    private LocalTime endAt;

    @Pattern(regexp = "On|Off", message = "Trạng thái chỉ được là 'On' hoặc 'Off'")
    private String active;
    private String sessionTitle;

    public Schedule() {
    }

    public Schedule(int scheduleId, int sessionId, String adminId, String studyDate, LocalTime startAt, LocalTime endAt, String active) {
        this.scheduleId = scheduleId;
        this.sessionId = sessionId;
        this.adminId = adminId;
        this.studyDate = studyDate;
        this.startAt = startAt;
        this.endAt = endAt;
        this.active = active;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String StudyDate() {
        return studyDate;
    }

    public void setStudyDate(String studyDate) {
        this.studyDate = studyDate;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStudyDate() {
        return studyDate;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Schedule{"
                + "scheduleId=" + scheduleId
                + ", sessionId=" + sessionId
                + ", adminId='" + adminId + '\''
                + ", studyDate=" + studyDate
                + ", startAt=" + startAt
                + ", endAt=" + endAt
                + ", active='" + active + '\''
                + '}';
    }

}

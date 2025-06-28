/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 *
 * @author qnhat
 */
public class BookingSession implements Serializable {

    
    
    private int bookingId;

    @NotBlank(message = "Không được trống")
    private String studentId; // Có thể null nên không cần annotation @NotBlank

    @NotNull(message = "SessionId không được để trống")
    private Integer sessionId; // Dùng Integer nếu bạn muốn kiểm tra null

    @Pattern(regexp = "Accept|Decline", message = "Trạng thái chỉ được là 'Accept' hoặc 'Decline'")
    private String bsStatus;

    @Pattern(regexp = "On|Off", message = "Trạng thái kích hoạt chỉ được là 'On' hoặc 'Off'")
    private String active;

    public BookingSession() {
    }

    public BookingSession(int bookingId, String studentId, int sessionId, String bsStatus, String active) {
        this.bookingId = bookingId;
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.bsStatus = bsStatus;
        this.active = active;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getBsStatus() {
        return bsStatus;
    }

    public void setBsStatus(String bsStatus) {
        this.bsStatus = bsStatus;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BookingSession{"
                + "bookingId=" + bookingId
                + ", studentId='" + studentId + '\''
                + ", sessionId=" + sessionId
                + ", bsStatus='" + bsStatus + '\''
                + ", active='" + active + '\''
                + '}';
    }

}

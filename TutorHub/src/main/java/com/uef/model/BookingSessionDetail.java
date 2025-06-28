/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author qnhat
 */
public class BookingSessionDetail implements Serializable {

    private int bookingSessionDetailId; // Tự sinh bên phía ứng dụng hoặc JDBC

    @NotBlank(message = "Không được trống")
    private Integer bookingId; // Có thể null, liên kết với BookingSession

    @NotBlank(message = "Không được trống")
    private Integer checkInId; // Có thể null, liên kết với CheckIn

    @Size(max = 300, message = "Vị trí không được vượt quá 300 ký tự")
    private String location;

    @Size(max = 300, message = "Lý do từ chối không được vượt quá 300 ký tự")
    private String declineReason;

    private LocalDateTime sessionStart;

    private LocalDateTime sessionEnd;

    @Pattern(regexp = "On|Off", message = "Trạng thái chỉ được là 'On' hoặc 'Off'")
    private String active;

    public BookingSessionDetail() {
    }

    public BookingSessionDetail(int bookingSessionDetailId, Integer bookingId, Integer checkInId, String location, String declineReason, LocalDateTime sessionStart, LocalDateTime sessionEnd, String active) {
        this.bookingSessionDetailId = bookingSessionDetailId;
        this.bookingId = bookingId;
        this.checkInId = checkInId;
        this.location = location;
        this.declineReason = declineReason;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.active = active;
    }

    public int getBookingSessionDetailId() {
        return bookingSessionDetailId;
    }

    public void setBookingSessionDetailId(int bookingSessionDetailId) {
        this.bookingSessionDetailId = bookingSessionDetailId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(Integer checkInId) {
        this.checkInId = checkInId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public LocalDateTime getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(LocalDateTime sessionStart) {
        this.sessionStart = sessionStart;
    }

    public LocalDateTime getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(LocalDateTime sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BookingSessionDetail{"
                + "bookingSessionDetailId=" + bookingSessionDetailId
                + ", bookingId=" + bookingId
                + ", checkInId=" + checkInId
                + ", location='" + location + '\''
                + ", declineReason='" + declineReason + '\''
                + ", sessionStart=" + sessionStart
                + ", sessionEnd=" + sessionEnd
                + ", active='" + active + '\''
                + '}';
    }
}

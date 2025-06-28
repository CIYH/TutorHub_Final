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
public class Session implements Serializable {

    private int sessionId;

    @Min(value = 0, message = "Phí không được âm")
    private int fee;
    private int subId;
    private String seDescription;
    private String title;
    private String duration;
    private String tutorId;
    private String subjectName;
    private Integer bookingId;
    private String bookingStatus;
    @Pattern(regexp = "Lock|Unlock", message = "Trạng thái phải là Lock hoặc Unlock")
    private String seStatus;

    @Pattern(regexp = "On|Off", message = "Trạng thái kích hoạt phải là On hoặc Off")
    private String active;
    private int acceptedBookingsCount;
    public Session() {
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public int getAcceptedBookingsCount() {
        return acceptedBookingsCount;
    }
    public void setAcceptedBookingsCount(int acceptedBookingsCount) {
        this.acceptedBookingsCount = acceptedBookingsCount;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public Session(int sessionId, int fee, int subId, String seDescription, String title, String duration, String tutorId, String seStatus, String active) {
        this.sessionId = sessionId;
        this.fee = fee;
        this.subId = subId;
        this.seDescription = seDescription;
        this.title = title;
        this.duration = duration;
        this.tutorId = tutorId;
        this.seStatus = seStatus;
        this.active = active;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSeDescription() {
        return seDescription;
    }

    public void setSeDescription(String seDescription) {
        this.seDescription = seDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getSeStatus() {
        return seStatus;
    }

    public void setSeStatus(String seStatus) {
        this.seStatus = seStatus;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Session{"
                + "sessionId=" + sessionId
                + ", fee=" + fee
                + ", subId=" + subId
                + ", seDescription='" + seDescription + '\''
                + ", title='" + title + '\''
                + ", duration='" + duration + '\''
                + ", tutorId='" + tutorId + '\''
                + ", seStatus='" + seStatus + '\''
                + ", active='" + active + '\''
                + '}';
    }
}

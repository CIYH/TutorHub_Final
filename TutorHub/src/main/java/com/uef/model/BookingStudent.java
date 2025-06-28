/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

/**
 *
 * @author Admin
 */
public class BookingStudent {
    private int BookingId;
    private String Title;
    private int Duration;
    private String p_name;
    private String email;
    private String phonenumber;
    private String Bs_Status;
    private String declineReason;
    private String Su_Name;
    private String Se_Description;
    private String SessionId;
    private String Location;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }

    public String getSu_Name() {
        return Su_Name;
    }

    public void setSu_Name(String Su_Name) {
        this.Su_Name = Su_Name;
    }

    public String getSe_Description() {
        return Se_Description;
    }

    public void setSe_Description(String Se_Description) {
        this.Se_Description = Se_Description;
    }
    
    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int BookingId) {
        this.BookingId = BookingId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBs_Status() {
        return Bs_Status;
    }

    public void setBs_Status(String Bs_Status) {
        this.Bs_Status = Bs_Status;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

/**
 *
 * @author Admin
 */
public class Tu_Session {
    private int SessionId;
    private int Fee;
    private int SubId;
    private String Se_Description;
    private String Title;
    private int Duration;
    private String TutorId;
    private String Se_Status;
    private String Active;

    public int getSessionId() {
        return SessionId;
    }

    public void setSessionId(int SessionId) {
        this.SessionId = SessionId;
    }

    public int getFee() {
        return Fee;
    }

    public void setFee(int Fee) {
        this.Fee = Fee;
    }

    public int getSubId() {
        return SubId;
    }

    public void setSubId(int SubId) {
        this.SubId = SubId;
    }

    public String getSe_Description() {
        return Se_Description;
    }

    public void setSe_Description(String Se_Description) {
        this.Se_Description = Se_Description;
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

    public String getTutorId() {
        return TutorId;
    }

    public void setTutorId(String TutorId) {
        this.TutorId = TutorId;
    }

    public String getSe_Status() {
        return Se_Status;
    }

    public void setSe_Status(String Se_Status) {
        this.Se_Status = Se_Status;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String Active) {
        this.Active = Active;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class StudentList {
    private String p_name;
    private String Title;
    private LocalDateTime SessionEnd;
    private int Present;
    private int Late;
    private int Absent;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public LocalDateTime getSessionEnd() {
        return SessionEnd;
    }

    public void setSessionEnd(LocalDateTime SessionEnd) {
        this.SessionEnd = SessionEnd;
    }

    public int getPresent() {
        return Present;
    }

    public void setPresent(int Present) {
        this.Present = Present;
    }

    public int getLate() {
        return Late;
    }

    public void setLate(int Late) {
        this.Late = Late;
    }

    public int getAbsent() {
        return Absent;
    }

    public void setAbsent(int Absent) {
        this.Absent = Absent;
    }
    
    
}

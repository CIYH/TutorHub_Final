/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import java.io.Serializable;
import java.sql.Time;

public class MySchedule implements Serializable {
    private String courseTitle;
    private String tutorName;
    private String studyDate;
    private Time startAt;
    private Time endAt;
    private String location;

    // Constructors, Getters, và Setters cho tất cả các trường
    public MySchedule() {}

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName; }
    public String getStudyDate() { return studyDate; }
    public void setStudyDate(String studyDate) { this.studyDate = studyDate; }
    public Time getStartAt() { return startAt; }
    public void setStartAt(Time startAt) { this.startAt = startAt; }
    public Time getEndAt() { return endAt; }
    public void setEndAt(Time endAt) { this.endAt = endAt; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}

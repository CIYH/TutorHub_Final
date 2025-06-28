package com.uef.model;

import java.io.Serializable;
import java.util.Date;


public class MySession implements Serializable {

    private int bookingId;
    private String bookingStatus;
    private String courseTitle;
    private String courseDescription;
    private String subjectName;
    private String tutorName;
    private String location;
    private Date sessionStart;
    private Date sessionEnd;

    // Constructors
    public MySession() {}

    // Getters and Setters cho tất cả các trường
    
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public String getCourseDescription() { return courseDescription; }
    public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Date getSessionStart() { return sessionStart; }
    public void setSessionStart(Date sessionStart) { this.sessionStart = sessionStart; }

    public Date getSessionEnd() { return sessionEnd; }
    public void setSessionEnd(Date sessionEnd) { this.sessionEnd = sessionEnd; }
}

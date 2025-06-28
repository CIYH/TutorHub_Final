/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;

public class TutorDetail extends Stu_People implements Serializable {

    @NotBlank(message = "Không được trống")
    @Min(value = 0, message = "Phí không được âm")
    private int fee;
//    private String TutorId;
    @Pattern(regexp = "^(1|2|3|4|5)$", message = "Rating phải là 1, 2, 3, 4 hoặc 5")
    private String rating;

    private Integer subId;

    @NotBlank(message = "Không được trống")
    @Size(max = 100, message = "Độ dài không quá 100 ký tự")
    private String education;

    @NotBlank(message = "Không được trống")
    @Size(max = 100, message = "Độ dài không quá 100 ký tự")
    private String experience;
    private String subjectName;
    private int sessionCount;

    public TutorDetail() {
        super();
        this.setpRole("tutor");
    }

    public TutorDetail(String id, String pName, String email, String address, String gender, String phonenumber, String active, String password, int fee, String rating, Integer subId, String education, String experience) {
        super(id, pName, email, address, "tutor", gender, phonenumber, active, password);
        this.fee = fee;
        this.rating = rating;
        this.subId = subId;
        this.education = education;
        this.experience = experience;
        this.sessionCount = 0;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    @Override
    public String toString() {
        return "Tutor{"
                + "id='" + getId() + '\''
                + ", pName='" + getpName() + '\''
                + ", email='" + getEmail() + '\''
                + ", address='" + getAddress() + '\''
                + ", pRole='" + getpRole() + '\''
                + ", gender='" + getGender() + '\''
                + ", phonenumber='" + getPhonenumber() + '\''
                + ", active='" + getActive() + '\''
                + ", password='" + getPassword() + '\''
                + ", fee=" + fee
                + ", rating='" + rating + '\''
                + ", subId=" + subId
                + ", education='" + education + '\''
                + ", experience='" + experience + '\''
                + '}';
    }

}

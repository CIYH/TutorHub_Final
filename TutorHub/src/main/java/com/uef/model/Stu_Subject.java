/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

/**
 *
 * @author qnhat
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

public class Stu_Subject implements Serializable {
    
    @NotBlank(message = "Không được trống")
    private int subId;
    
    @NotBlank(message = "Không được trống")
    @Size(max = 50, message = "Độ dài không quá 50 ký tự" )
    private String adminId;
    
    @NotBlank(message = "Không được trống")
    @Size(max = 100, message = "Độ dài không quá 100 ký tự" )
    private String suName;
    
    @NotBlank(message = "Không được trống")
    @Size(max = 200, message = "Độ dài không quá 200 ký tự" )
    private String suDescription;
    
    @NotBlank(message = "Không được trống")
    @Size(max = 200, message = "Độ dài không quá 200 ký tự" )
    @Pattern(regexp = "^(On|Off)$", message = "Active must be On|Off")
    private String active;

    public Stu_Subject() {
    }

    public Stu_Subject(int subId, String adminId, String suName, String suDescription, String active) {
        this.subId = subId;
        this.adminId = adminId;
        this.suName = suName;
        this.suDescription = suDescription;
        this.active = active;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getSuDescription() {
        return suDescription;
    }

    public void setSuDescription(String suDescription) {
        this.suDescription = suDescription;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "Subject{" +
               "subId=" + subId +
               ", adminId='" + adminId + '\'' +
               ", suName='" + suName + '\'' +
               ", suDescription='" + suDescription + '\'' +
               ", active='" + active + '\'' +
               '}';
    }
}

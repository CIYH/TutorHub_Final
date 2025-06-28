/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author qnhat
 */
public class Subject {

    private Integer subId;

    @NotBlank(message = "Không được trống")
    @Size(max = 50, message = "Độ dài không quá 50 ký tự")
    private String suName;

    @NotBlank(message = "Không được trống")
    @Size(max = 200, message = "Độ dài không quá 200 ký tự")
    private String suDescription;

    @NotBlank(message = "Không được trống")
    @Size(max = 200, message = "Độ dài không quá 200 ký tự")
    @Pattern(regexp = "^(On|Off)$", message = "Active must be On|Off")
    private String active; // 'On' or 'Off'

    /**
     * This field represents the link to the Admin. It holds the full Admin
     * object instead of just the ID. This corresponds to the FOREIGN KEY
     * (AdminId) REFERENCES Admin(AdminId).
     */
    private Admin admin;

    // Default constructor
    public Subject() {
    }

    // Constructor with all fields
    public Subject(Integer subId, String suName, String suDescription, String active, Admin admin) {
        this.subId = subId;
        this.suName = suName;
        this.suDescription = suDescription;
        this.active = active;
        this.admin = admin;
    }

    // Getters and Setters
    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "subId=" + subId
                + ", suName='" + suName + '\''
                + ", active='" + active + '\''
                + ", admin=" + (admin != null ? admin.getpName() : "null")
                + '}';
    }
}

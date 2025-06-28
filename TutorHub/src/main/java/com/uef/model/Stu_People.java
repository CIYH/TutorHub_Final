/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;


public class Stu_People implements Serializable {

    @NotBlank(message = "Không được trống")
    @Size(max = 50, message = "Id không quá 50 ký tự" )
    protected String id; // Sử dụng protected để các lớp con có thể truy cập
    
    @NotBlank(message = "Không được trống")
    @Size(max = 100, message = "Tên không quá 100 ký tự" )
    protected String pName;
    
    @Email(message = "Sai định dạng email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email phải đúng định dạng và kết thúc bằng @gmail.com")
    protected String email;
    
    @Size(max = 150, message = "Địa chỉ không quá 150 ký tự" )
    protected String address;
    
    @NotBlank(message = "Không được trống")
    @Pattern(regexp = "^(user|admin|tutor)$", message = "Vai trò phải là 'user', 'admin' hoặc 'tutor'")
    protected String pRole;
    
    @Pattern(regexp = "^(Nam|Nữ)$", message = "Giới tính phải là 'Nam' hoặc 'Nữ'")
    protected String gender;
    
    @Size(max = 20, message = "SĐT không quá 20 ký tự" )
    protected String phonenumber;
    
    @Pattern(regexp = "^(On|Off)$", message = "Active must be On|Off")
    protected String active;
    
    @NotBlank(message = "Không được trống")
    @Size(max = 200, message = "Id không quá 200 ký tự" )
    protected String password;

    public Stu_People() {
    }

    public Stu_People(String id, String pName, String email, String address, String pRole, String gender, String phonenumber, String active, String password) {
        this.id = id;
        this.pName = pName;
        this.email = email;
        this.address = address;
        this.pRole = pRole;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.active = "On";
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getpRole() {
        return pRole;
    }

    public void setpRole(String pRole) {
        this.pRole = pRole;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "People{"
                + "id='" + id + '\''
                + ", pName='" + pName + '\''
                + ", email='" + email + '\''
                + ", address='" + address + '\''
                + ", pRole='" + pRole + '\''
                + ", gender='" + gender + '\''
                + ", phonenumber='" + phonenumber + '\''
                + ", active='" + active + '\''
                + ", password='" + password + '\''
                + '}';
    }

}

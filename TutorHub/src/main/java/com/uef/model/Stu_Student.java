/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

import java.io.Serializable;

/**
 *
 * @author qnhat
 */
public class Stu_Student extends Stu_People implements Serializable {

    public Stu_Student() {
        super();
        this.setpRole("user"); 
    }

    public Stu_Student(String id, String pName, String email, String address, String gender, String phonenumber, String active, String password) {
        super(id, pName, email, address, "user", gender, phonenumber, active, password);
    }
    
    
    

    @Override
    public String toString() {
        return "Student{"
                + "id='" + getId() + '\''
                + ", pName='" + getpName() + '\''
                + ", email='" + getEmail() + '\''
                + ", address='" + getAddress() + '\''
                + ", pRole='" + getpRole() + '\''
                + ", gender='" + getGender() + '\''
                + ", phonenumber='" + getPhonenumber() + '\''
                + ", active='" + getActive() + '\''
                + ", password='" + getPassword() + '\''
                + '}';
    }

}

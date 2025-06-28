/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.model;

/**
 *
 * @author qnhat
 */
import java.io.Serializable;

public class Admin extends People implements Serializable {

    
    public Admin() {
        super();
        this.setpRole("admin"); 
    }

    public Admin(String id, String pName, String email, String address, String gender, String phonenumber, String active, String password) {
        super(id, pName, email, address, "admin", gender, phonenumber, active, password);
    }

    @Override
    public String toString() {
        return "Admin{"
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

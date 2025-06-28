/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.Admin;
import com.uef.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllActiveAdmins() {
        return adminRepository.findAllActive();
    }

    public Admin getAdminById(String id) {
        return adminRepository.findById(id);
    }

    public void createNewAdmin(Admin admin) {
        if (admin == null || admin.getId() == null || admin.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Thông tin admin không hợp lệ");
        }

        // Kiểm tra email có đúng định dạng không (validation được thực hiện ở lớp model)
        admin.setActive("On"); // Mặc định active khi tạo mới
        adminRepository.save(admin);
    }

    public void updateAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findById(admin.getId());
        if (existingAdmin == null) {
            throw new RuntimeException("Không tìm thấy admin với ID: " + admin.getId());
        }

        adminRepository.update(admin);
    }

    public void deleteAdmin(String id) {
        adminRepository.softDelete(id);
    }
}

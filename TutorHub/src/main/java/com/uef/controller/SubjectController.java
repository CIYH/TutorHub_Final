/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.uef.model.Admin;
import com.uef.model.Subject;
import com.uef.service.SubjectService;
import com.uef.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author qnhat
 */
@Controller
@RequestMapping("/admin/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AdminService adminService;

    private LoginController login;

    private final String path = "/WEB-INF/views/Admin/";

    @GetMapping()
    public String listSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Admin> admins = adminService.getAllActiveAdmins();
        model.addAttribute("admins", admins);
        model.addAttribute("subjects", subjects);
        model.addAttribute("body", path + "subjects.jsp");
        return "/layout/Admin/main";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Subject subject = new Subject();

        Admin admin = adminService.getAdminById(login.Id);

        subject.setAdmin(admin);
        // Lấy danh sách admin để chọn người tạo môn học
        List<Admin> admins = adminService.getAllActiveAdmins();
        model.addAttribute("subject", subject);
        model.addAttribute("admins", admins);
        model.addAttribute("body", path + "subject_add.jsp");
        return "/layout/Admin/main";
    }

    private String adminName(String AdminId, List<Admin> admin) {
        String adminname = admin.stream()
                .filter(admins -> admins.getId().equals(AdminId))
                .map(Admin::getpName)
                .findFirst()
                .orElse("Không tìm thấy Admin");
        return adminname;
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute Subject subject,
            @RequestParam("adminId") String adminId,
            RedirectAttributes redirectAttributes) {
        try {
            // Lấy thông tin admin từ adminId
            Admin admin = adminService.getAdminById(adminId);
            if (admin == null) {
                throw new IllegalArgumentException("Admin không tồn tại");
            }

            subject.setAdmin(admin);
            subjectService.createNewSubject(subject);

            redirectAttributes.addFlashAttribute("successMessage", "Thêm môn học thành công!");
            return "redirect:/admin/subjects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm môn học: " + e.getMessage());
            return "redirect:/admin/subjects/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy môn học với ID: " + id);
            return "redirect:/admin/subjects";
        }

        List<Admin> admins = adminService.getAllActiveAdmins();

        model.addAttribute("subject", subject);
        model.addAttribute("admins", admins);
        model.addAttribute("body", path + "subject_edit.jsp");
        return "/layout/Admin/main";
    }

    @PostMapping("/edit")
    public String updateSubject(@ModelAttribute Subject subject,
            @RequestParam("adminId") String adminId,
            RedirectAttributes redirectAttributes) {
        try {
            Admin admin = adminService.getAdminById(adminId);
            if (admin == null) {
                throw new IllegalArgumentException("Admin không tồn tại");
            }

            subject.setAdmin(admin);
            subjectService.updateSubjectDetails(subject);

            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật môn học thành công!");
            return "redirect:/admin/subjects";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật môn học: " + e.getMessage());
            return "redirect:/admin/subjects/edit/" + subject.getSubId();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            subjectService.deleteSubject(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa môn học thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa môn học: " + e.getMessage());
        }
        return "redirect:/admin/subjects";
    }

    @GetMapping("/detail/{id}")
    public String viewSubjectDetail(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy môn học với ID: " + id);
            return "redirect:/admin/subjects";
        }

        model.addAttribute("subject", subject);
        model.addAttribute("body", path + "subject_detail.jsp");
        return "/layout/Admin/main";
    }

    @GetMapping("/api/{id}")
    @ResponseBody // Rất quan trọng: Báo cho Spring trả về dữ liệu, không phải view
    public ResponseEntity<Subject> getSubjectByIdAsJson(@PathVariable("id") int id) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject != null) {
            return ResponseEntity.ok(subject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

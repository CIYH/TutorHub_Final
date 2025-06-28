/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.uef.model.Login_Model;
import com.uef.model.TutorProfile;
import com.uef.service.TutorProfileService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/tutor")
public class Tu_ProfileController {
    
    @Autowired
    private TutorProfileService tutorProfileService;
    
    @GetMapping("/HoSo")
    public String profileTutorPage(Model model, HttpSession session) {

        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");
        
        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }
        
        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        
        TutorProfile tutor = tutorProfileService.getTutorById(tutorId);
        
        if (tutor == null) {
            // Xử lý trường hợp không tìm thấy gia sư
            return "error/404"; // Trả về trang lỗi 404
        }
        
        model.addAttribute("tutor", tutor);
        model.addAttribute("activePage", "Hồ Sơ");
        model.addAttribute("body", "/WEB-INF/views/Tutor/Profile.jsp");
        return "layout/Tutor/main";
    }
    
    @PostMapping("/HoSo/update")
    public String handleProfileUpdate(@ModelAttribute TutorProfile tutor, RedirectAttributes redirectAttributes) {
        // @ModelAttribute sẽ tự động lấy dữ liệu từ form và gán vào đối tượng TutorProfile
        
        tutorProfileService.saveTutorProfile(tutor);
        
        // Gửi một thông báo thành công về trang redirect
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật hồ sơ thành công!");
        
        // Redirect về trang hồ sơ để tránh lỗi F5 gửi lại form
        return "redirect:/tutor/HoSo";
    }
}
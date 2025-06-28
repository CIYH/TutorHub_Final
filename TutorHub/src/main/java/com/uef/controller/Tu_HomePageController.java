/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uef.model.Login_Model;
import com.uef.service.HomePageService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/tutor")
public class Tu_HomePageController {
    
    @Autowired
    private HomePageService homePageService;
    
    @GetMapping("/tutor/")
    public String redirectToHome() {
        return "redirect:/tutor/TrangChu";
    }
    
    @GetMapping("/TrangChu")
    public String showTutorHomepage(Model model, HttpSession session) throws JsonProcessingException {
        
        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");
        
        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }
        
        // 3. Lấy ID từ đối tượng vừa lấy ra
        String id = loggedInUser.getId();

        Map<String, Object> data = homePageService.getDataForHomepage(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        model.addAttribute("tutorName", data.get("tutorName"));
        model.addAttribute("schedulesJson", objectMapper.writeValueAsString(data.get("schedules")));

        model.addAttribute("activeSidebar", "Trang Chủ");
        model.addAttribute("activePage", "Trang Chủ");
        model.addAttribute("body", "/WEB-INF/views/Tutor/TrangChu.jsp");

        return "layout/Tutor/main";
    }
}

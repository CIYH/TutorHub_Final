/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.uef.service.DashboardService;
import com.uef.dto.DashboardStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author qnhat
 */
@Controller
public class Ad_Layout {

    @Autowired
    private DashboardService dashboardService;
    
    private final String pathAdmin = "/WEB-INF/views/Admin/";
    
    @GetMapping("/admin")
    public String showAdmin(Model model) {
        DashboardStatsDTO stats = dashboardService.getDashboardStats();

        // Đặt đối tượng chứa dữ liệu vào model để gửi sang JSP
        model.addAttribute("stats", stats);

        // Giả sử bạn có một layout chung và trang dashboard là một phần của nó
        model.addAttribute("body", pathAdmin + "dashboard.jsp");
        return "layout/Admin/main";
    }

    @GetMapping("/tutor")
    public String showTutor(Model model) {
        return "layout/Tutor/main";
    }

    @GetMapping("/student")
    public String showStdent(Model model) {
        return "layout/Student/main";
    }

    @GetMapping("/")
    public String showHomePate(Model model) {
        return "redirect:/login";
    }
}

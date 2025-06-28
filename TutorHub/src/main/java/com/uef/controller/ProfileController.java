package com.uef.controller;

import com.uef.model.Stu_People;
import com.uef.service.PeopleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private PeopleService peopleService;
    
    private final String path = "/WEB-INF/views/";

    @GetMapping
    public ModelAndView showProfilePage(HttpSession session) {
        // SỬA LỖI: Lấy ID người dùng từ session với key "username"
        String userId = (String) session.getAttribute("username");

        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }
        
        Stu_People profileData = peopleService.getProfileById(userId);

        ModelAndView mav = new ModelAndView("layout/Student/main");
        mav.addObject("profile", profileData);
        mav.addObject("body", path + "Student/profile.jsp");
        mav.addObject("activePage", "Hồ sơ của tôi");
        
        return mav;
    }

    /**
     * Xử lý việc cập nhật thông tin hồ sơ.
     */
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute Stu_People updatedProfile, HttpSession session, RedirectAttributes redirectAttributes) {
        // SỬA LỖI: Lấy ID người dùng từ session với key "username"
        String userId = (String) session.getAttribute("username");

        if (userId == null) {
            return "redirect:/login";
        }
        
        try {
            updatedProfile.setId(userId);
            peopleService.updateProfile(updatedProfile);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật hồ sơ thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật hồ sơ thất bại.");
            e.printStackTrace();
        }

        return "redirect:/profile";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;


import com.uef.model.Student;
import com.uef.service.Ad_StudentService;
import com.uef.model.Login_Model;
import com.uef.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 *
 * @author qnhat
 */

@Controller
public class LoginController {
    
    public String Id;
    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Autowired
    private Ad_StudentService studentService;
    
    
     private static final class LoginRowMapper implements RowMapper<Login_Model> {

        @Override
        public Login_Model mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login_Model r = new Login_Model();
        r.setEmail(rs.getString("email"));
        r.setId(rs.getString("Id"));
        r.setpRole(rs.getString("p_role"));
        return r;
    }
    }
    
    @RequestMapping(value = "/TutorHub", method = RequestMethod.GET)
    public String showLandingPage() {
        return "layout/LandingPage";
    }
     
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "layout/authencation_login";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        model.addAttribute("students", new Student());
        return "layout/authencation_register";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam("username") String username,@RequestParam("password") String password,
            HttpSession session,Model model) {
        String sql = "SELECT * FROM people where email = ? AND password =?";
        try {
            List<Login_Model> user = jdbcTemplate.query(sql, new LoginRowMapper(), username, password);
            
            if(user.isEmpty()){
                model.addAttribute("errorMsg", "Sai tài khoản hoặc mật khẩu");
                return "layout/authencation_login";
            }
            Login_Model item = user.get(0);
            session.setAttribute("username", item.getId());
            session.setAttribute("role", item.getpRole());  
            
            this.Id = item.getId();
            //tutor by vkhang
            Login_Model loggedInUser = user.get(0);
            session.setAttribute("loggedInUser", loggedInUser); 
            //
            
            switch(item.getpRole()){
                case "admin":
                    return "redirect:/admin";
                
                case "tutor":
                    return "redirect:/tutor/TrangChu";
                    
                case "user":
                    return "redirect:/home";
                default:
                    return "redirect:/login";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "Lỗi hệ thống");
            return "login";
        }
    }
    
    // Phương thức POST để xử lý việc đăng ký
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("student") Student student, Model model) {
        try {           
           studentService.createNewStudent(student);
            return "redirect:/login?success=true";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "Hệ thống đã xảy ra lỗi. Vui lòng thử lại.");
            return "layout/authencation_register";
        }
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/TutorHub";
    }
}

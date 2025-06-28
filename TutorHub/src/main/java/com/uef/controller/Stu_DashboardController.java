package com.uef.controller;

import com.uef.model.MySchedule;
import com.uef.model.Stu_People;
import com.uef.service.MyScheduleService;
import com.uef.service.PeopleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class Stu_DashboardController {

    @Autowired
    private MyScheduleService myScheduleService;
    
    @Autowired
    private PeopleService peopleService;

    private final String path = "/WEB-INF/views/";

    // Giả sử URL cho trang chủ của sinh viên là /student
    @GetMapping("/home")
    public ModelAndView showHomePage(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        
        // SỬA LỖI: Lấy đúng ID của người dùng từ session với key "username"
        String studentId = (String) session.getAttribute("username");

        // Kiểm tra xem người dùng đã đăng nhập chưa
//        if (studentId == null || studentId.isEmpty()) {
//            mav.setViewName("redirect:/login");
//            return mav;
//        }

        // Lấy tên của học viên từ ID đã lấy được
        String studentName = peopleService.getPeopleNameById(studentId);
        
        // Lấy lịch học của sinh viên
        List<MySchedule> schedules = myScheduleService.getSchedulesForStudent(studentId);
        
        // Gom nhóm lịch học theo ngày để dễ hiển thị
        Map<String, List<MySchedule>> scheduleMap = schedules.stream()
            .collect(Collectors.groupingBy(MySchedule::getStudyDate));

        mav.setViewName("layout/Student/main"); // Dùng layout chung
        mav.addObject("studentName", studentName); // Gửi tên người dùng ra view
        mav.addObject("scheduleMap", scheduleMap);
        mav.addObject("body", path + "Student/dashboard.jsp"); // File JSP của trang chủ
        mav.addObject("activePage", "Trang Chủ");

        return mav;
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.uef.model.ChonLichHoc;
import com.uef.model.DSKhoaHoc;
import com.uef.model.Login_Model;
import com.uef.model.ScheduleDTO;
import com.uef.model.Tu_Session;
import com.uef.model.SessionWithSubName;
import com.uef.model.Tu_Subject;
import com.uef.service.Tu_SessionService;
import com.uef.service.Tu_SubjectService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class Tu_SessionController {

    @Autowired
    private Tu_SubjectService subjectService;

    @Autowired
    private Tu_SessionService sessionService;

    @GetMapping("/TaoKhoaHoc")
    public String TaoKhoaHocPage(Model model, HttpSession session) {
        List<Tu_Subject> subjects = subjectService.getAll();
        model.addAttribute("listSubjects", subjects);

        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();

        List<ChonLichHoc> chonlichhoc = sessionService.getAllSchedule(tutorId);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String dsLichDayJson = "[]";
        try {
            if (chonlichhoc != null && !chonlichhoc.isEmpty()) {
                dsLichDayJson = objectMapper.writeValueAsString(chonlichhoc);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("initialSchedulesJson", dsLichDayJson);

        model.addAttribute("activeSidebar", "Khóa Học");
        model.addAttribute("activePage", "Tạo Khóa Học");
        model.addAttribute("body", "/WEB-INF/views/Tutor/TaoKhoaHoc.jsp");
        return "layout/Tutor/main";
    }

    @PostMapping("/TaoKhoaHoc")
    public String handleCreateSession(
            @RequestParam("courseName") String title,
            @RequestParam("subject") int subId,
            @RequestParam("duration") int duration,
            @RequestParam("fee") int fee,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("selectedSchedules") String schedulesJson,
            RedirectAttributes redirectAttributes, HttpSession sessionn) {

        try {
            Tu_Session session = new Tu_Session();
            session.setTitle(title);
            session.setSubId(subId);
            session.setDuration(duration);
            session.setFee(fee);
            session.setSe_Description(description);

            // Chuyển đổi chuỗi JSON của lịch dạy thành List<ScheduleDTO>
            ObjectMapper objectMapper = new ObjectMapper();
            List<ScheduleDTO> schedules = objectMapper.readValue(schedulesJson, new TypeReference<List<ScheduleDTO>>() {
            });

            Login_Model loggedInUser = (Login_Model) sessionn.getAttribute("loggedInUser");

            // 2. Kiểm tra xem người dùng đã đăng nhập chưa
            if (loggedInUser == null) {
                return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
            }

            // 3. Lấy ID từ đối tượng vừa lấy ra
            String tutorId = loggedInUser.getId();
            // Gọi Service để thực hiện logic
            sessionService.createSession(session, schedules, tutorId);

            // 5. Thêm thông báo thành công và chuyển hướng
            redirectAttributes.addFlashAttribute("successMessage", "Tạo khóa học thành công!");
            return "redirect:/tutor/TaoKhoaHoc"; // Chuyển hướng về trang chủ hoặc trang quản lý khóa học

        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu parse JSON thất bại
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi xử lý dữ liệu lịch dạy.");
            return "redirect:/tutor/TaoKhoaHoc";
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý các lỗi khác
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi tạo khóa học.");
            return "redirect:/tutor/TaoKhoaHoc";
        }
    }

    @GetMapping("/QuanLyKhoaHoc")
    public String QuanLyKhoaHocpage(Model model, @RequestParam(name = "title", required = false) String title, HttpSession session) {
        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        List<DSKhoaHoc> dsKhoaHoc = sessionService.searchAllSessionWithSubNameByTitle(title, tutorId);
        model.addAttribute("session", dsKhoaHoc);
        model.addAttribute("title", title);
        model.addAttribute("activePage", "Quản Lý Khóa Học");
        model.addAttribute("activeSidebar", "Khóa Học");
        model.addAttribute("body", "/WEB-INF/views/Tutor/QuanLyKhoaHoc.jsp");
        return "layout/Tutor/main";
    }

    @PostMapping("/XoaKhoaHoc")
    public String handleDeleteCourse(@RequestParam("sessionId") int sessionId, RedirectAttributes redirectAttributes, Model model) {

        try {
            sessionService.deleteCourse(sessionId);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa khóa học thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa khóa học.");
        }

        return "redirect:/tutor/QuanLyKhoaHoc";
    }

    // --- HÀM MỚI ĐỂ HIỂN THỊ TRANG SỬA ---
    @GetMapping("/SuaKhoaHoc/{id}")
    public String showEditCoursePage(@PathVariable("id") int sessionId, Model model, HttpSession session) throws JsonProcessingException {
        // 1. Lấy thông tin chi tiết của khóa học cần sửa
        SessionWithSubName course = sessionService.getSessionWithSubNameById(sessionId);
        if (course == null) {
            // Xử lý trường hợp không tìm thấy khóa học
            return "redirect:/tutor/QuanLyKhoaHoc";
        }

        // 2. Lấy danh sách lịch dạy của riêng khóa học này
        List<ScheduleDTO> schedulesOfThisCourse = sessionService.getSchedulesBySessionId(sessionId);

        // 3. Lấy danh sách môn học cho dropdown
        List<Tu_Subject> subjects = subjectService.getAll();
        
        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        List<ScheduleDTO> otherSchedules = sessionService.getOtherSchedules(tutorId, sessionId);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // 4. Đưa tất cả vào model
        model.addAttribute("course", course); // Đối tượng chứa thông tin chính
        model.addAttribute("listSubjects", subjects); // Danh sách cho dropdown
        model.addAttribute("schedulesOfThisCourseJson", objectMapper.writeValueAsString(schedulesOfThisCourse));
        model.addAttribute("otherSchedulesJson", objectMapper.writeValueAsString(otherSchedules));

        // Các attribute cho layout
        model.addAttribute("activeSidebar", "Khóa Học");
        model.addAttribute("activePage", "Sửa Khóa Học");
        model.addAttribute("body", "/WEB-INF/views/Tutor/SuaKhoaHoc.jsp");

        return "layout/Tutor/main";
    }

    // --- HÀM MỚI ĐỂ XỬ LÝ CẬP NHẬT ---
    @PostMapping("/SuaKhoaHoc")
    public String handleUpdateCourse(
            @RequestParam("sessionId") int sessionId,
            @RequestParam("courseName") String title,
            @RequestParam("subject") int subId,
            @RequestParam("duration") int duration,
            @RequestParam("fee") int fee,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("selectedSchedules") String schedulesJson,
            RedirectAttributes redirectAttributes) {

        try {
            // 1. Tạo đối tượng Session từ các RequestParam
            Tu_Session sessionToUpdate = new Tu_Session();
            sessionToUpdate.setSessionId(sessionId);
            sessionToUpdate.setTitle(title);
            sessionToUpdate.setSubId(subId);
            sessionToUpdate.setDuration(duration);
            sessionToUpdate.setFee(fee);
            sessionToUpdate.setSe_Description(description);

            // 2. Parse JSON lịch học
            ObjectMapper objectMapper = new ObjectMapper();
            List<ScheduleDTO> schedules = objectMapper.readValue(schedulesJson, new TypeReference<>() {
            });

            // 3. Gọi service để cập nhật
            sessionService.updateCourse(sessionToUpdate, schedules);

            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật khóa học thành công!");
            return "redirect:/tutor/QuanLyKhoaHoc";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi xử lý dữ liệu lịch dạy.");
            return "redirect:/tutor/QuanLyKhoaHoc?id=" + sessionId;
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi cập nhật.");
            return "redirect:/tutor/QuanLyKhoaHoc?id=" + sessionId;
        }
    }
}

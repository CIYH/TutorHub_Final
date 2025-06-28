package com.uef.controller;

import com.uef.model.Stu_People;
import com.uef.model.Schedule;
import com.uef.model.Session;
import com.uef.model.Stu_Subject;
import com.uef.model.TutorDetail;
import com.uef.service.BookingSessionService; 
import com.uef.service.PeopleService;
import com.uef.service.SubjectService_1;
import com.uef.service.TutorService;
import jakarta.servlet.http.HttpSession;
import com.uef.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/findtutors")
public class FindTutorController {

    // Giờ đây chỉ cần inject các service chính
    @Autowired
    private TutorService tutorService;
    @Autowired
    private SubjectService_1 subjectService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private BookingSessionService bookingService;

    private final String path = "/WEB-INF/views/";

    @GetMapping({"", "/list"})
    public ModelAndView showTutorListPage(
            @RequestParam(name = "subjectId", required = false) Integer subjectId,
            @RequestParam(name = "tutorName", required = false) String tutorName) {
        
        ModelAndView mav = new ModelAndView("layout/Student/main");
        List<Stu_Subject> subjects = subjectService.getAllActiveSubjects();
        List<TutorDetail> tutorList = tutorService.filterTutors(subjectId, tutorName);

        mav.addObject("tutors", tutorList);
        mav.addObject("subjects", subjects);
        mav.addObject("selectedSubjectId", subjectId);
        mav.addObject("tutorName", tutorName);
        mav.addObject("body", path + "Student/tutor-list.jsp");
        mav.addObject("activePage", "Tìm Gia Sư");
        mav.addObject("activeSidebar", "Tìm Gia Sư");
        return mav;
    }
    
//    @GetMapping("/detail/{id}")
//    public ModelAndView showTutorDetailPage(@PathVariable("id") String id, HttpSession session) {
//        ModelAndView mav = new ModelAndView("layout/Student/main");
//        
//        String studentId = (String) session.getAttribute("username");
//        String studentName = "";
//        if (studentId != null) {
//            studentName = peopleService.getPeopleNameById(studentId);
//        }
//        TutorDetail tutor = tutorService.getTutorDetailsById(id);
//        // Nếu không có ai đăng nhập, studentId sẽ là null, và truy vấn sẽ không trả về trạng thái của bất kỳ ai
//        List<Session> sessions = tutorService.findActiveSessionsByTutorId(id, studentId);
//        
//        
//        Stu_People loggedInUser = (Stu_People) session.getAttribute("loggedInUser");
//        String studentId = (loggedInUser != null) ? loggedInUser.getId() : "GUEST_USER_NO_BOOKING";
//        String studentName = (loggedInUser != null) ? loggedInUser.getpName() : "";
//        
//        
//        
//        // Truy vấn danh sách khóa học với ID của người dùng hiện tại
//        List<Session> sessions = tutorService.findActiveSessionsByTutorId(id, studentId);
//        
//        List<Schedule> schedules = tutorService.findSchedulesByTutorId(id);
//        Map<String, List<Schedule>> scheduleMap = schedules.stream()
//            .collect(Collectors.groupingBy(schedule -> schedule.getStudyDate()));
//
//        mav.addObject("tutor", tutor);
//        mav.addObject("sessions", sessions);
//        mav.addObject("scheduleMap", scheduleMap);
//        mav.addObject("studentId", studentId);
//        mav.addObject("studentName", studentName);
//        mav.addObject("loggedInUser", loggedInUser); // Gửi cả đối tượng user để JSP kiểm tra đăng nhập
//        mav.addObject("body", path + "Student/tutor-detail.jsp");
//        mav.addObject("activePage", "Chi tiết Gia sư");
//        
//        return mav;
//    }
@GetMapping("/detail/{id}")
    public ModelAndView showTutorDetailPage(@PathVariable("id") String id, HttpSession session) {
        ModelAndView mav = new ModelAndView("layout/Student/main");
        

        String studentId = (String) session.getAttribute("username");
        
        String studentName = "";

        if (studentId != null && !studentId.isEmpty()) {
            studentName = peopleService.getPeopleNameById(studentId);
        } else {

            studentId = "GUEST_USER"; 
        }

        TutorDetail tutor = tutorService.getTutorDetailsById(id);
        List<Session> sessions = tutorService.findActiveSessionsByTutorId(id, studentId);
        List<Schedule> schedules = tutorService.findSchedulesByTutorId(id);
        Map<String, List<Schedule>> scheduleMap = schedules.stream()
            .collect(Collectors.groupingBy(Schedule::getStudyDate));

        mav.addObject("tutor", tutor);
        mav.addObject("sessions", sessions);
        mav.addObject("scheduleMap", scheduleMap);
        mav.addObject("studentName", studentName);
        mav.addObject("body", path + "Student/tutor-detail.jsp");
        mav.addObject("activePage", "Chi tiết Gia sư");
         mav.addObject("activeSidebar", "Đăng Ký");
        return mav;
    }
    @PostMapping("/book")
    public String handleBooking(
            @RequestParam("sessionId") int sessionId,
            @RequestParam("location") String location,
            @RequestParam("tutorId") String tutorId,
            HttpSession session, 
            RedirectAttributes redirectAttributes) {
                
        // LẤY ID SINH VIÊN TỪ SESSION
        String studentId = (String) session.getAttribute("username");
        
        if (studentId == null || studentId.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại.");
            return "redirect:/login";
        }

        try {
            bookingService.createBooking(sessionId, location, studentId);
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Đăng ký thất bại.");
            e.printStackTrace();
        }
        return "redirect:/findtutors/detail/" + tutorId;
    }
}

package com.uef.controller;

import com.uef.model.MySession;
import com.uef.model.Stu_People;
import com.uef.service.MySessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.uef.service.TutorService;
import com.uef.service.SubjectService_1;
import com.uef.model.Stu_Subject;
import com.uef.service.BookingSessionService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/my-sessions")
public class MySessionController {

    @Autowired
    private BookingSessionService bookingSessionService;

    @Autowired
    private TutorService findTutorService;

    @Autowired
    private SubjectService_1 SubjectService;

    @Autowired
    private MySessionService mySessionService;

    private final String path = "/WEB-INF/views/";

    @GetMapping
    public ModelAndView showMySessionsPage(HttpSession session,
                                           @RequestParam(name = "search", required = false) String searchTerm,
                                           @RequestParam(name = "subjectId", required = false) Integer subjectId) {
        
        // LẤY ID SINH VIÊN TỪ SESSION
        String studentId = (String) session.getAttribute("username");

        // Nếu người dùng chưa đăng nhập, chuyển hướng về trang login
        if (studentId == null || studentId.isEmpty()) {
            return new ModelAndView("redirect:/login");
        }

        // Lấy danh sách khóa học dựa trên ID của người dùng đã đăng nhập
        List<MySession> mySessions = mySessionService.getSessionsByStudentId(studentId, searchTerm, subjectId);
        List<Stu_Subject> subjects = SubjectService.getAllActiveSubjects();

        ModelAndView mav = new ModelAndView("layout/Student/main");
        mav.addObject("mySessions", mySessions);
        mav.addObject("subjects", subjects);
        mav.addObject("searchTerm", searchTerm);
        mav.addObject("selectedSubjectId", subjectId);
        mav.addObject("body", path + "Student/session-list.jsp");
        mav.addObject("activePage", "Khóa học của tôi"); 
        mav.addObject("activeSidebar", "Khóa Học");
        return mav;
    }

    @GetMapping("/detail/{bookingId}")
    public ModelAndView showDetailPage(@PathVariable("bookingId") int bookingId, HttpSession session) {
//        Stu_People loggedInUser = (Stu_People) session.getAttribute("loggedInUser");
//        if (loggedInUser == null) {
//            return new ModelAndView("redirect:/login");
//        }
        String studentId = (String) session.getAttribute("username");
        MySession courseDetail = mySessionService.getDetailByBookingId(bookingId);

        ModelAndView mav = new ModelAndView("layout/Student/main");
        mav.addObject("course", courseDetail);
        mav.addObject("body", path + "Student/session-detail.jsp");
        mav.addObject("activePage", "Chi tiết Khóa học");
        return mav;
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam("bookingId") int bookingId, RedirectAttributes redirectAttributes) {
        try {
            bookingSessionService.cancelBooking(bookingId);
            redirectAttributes.addFlashAttribute("successMessage", "Đã hủy khóa học thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hủy khóa học thất bại. Vui lòng thử lại.");
            e.printStackTrace();
        }
        return "redirect:/my-sessions";
    }

    @PostMapping("/rate")
    public String rateTutor(@RequestParam("bookingId") int bookingId,
            @RequestParam("tutorId") String tutorId, // Cần truyền tutorId từ form
            @RequestParam("rating") String rating,
            RedirectAttributes redirectAttributes) {
        try {
            // Logic cập nhật rating (bạn cần có phương thức này trong service)
            // findTutorService.updateTutorRating(tutorId, rating); 
            redirectAttributes.addFlashAttribute("successMessage", "Cảm ơn bạn đã đánh giá!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gửi đánh giá thất bại.");
            e.printStackTrace();
        }
        // Chuyển về lại trang chi tiết của lượt đăng ký đó
        return "redirect:/my-sessions/detail/" + bookingId;
    }
}

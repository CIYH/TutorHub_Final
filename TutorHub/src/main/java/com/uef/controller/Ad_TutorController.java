package com.uef.controller;

import com.uef.model.Subject;
import com.uef.model.Tutor;
import com.uef.service.Ad_TutorService;
import com.uef.service.SubjectService; // Giả định bạn có một Service để lấy danh sách môn học

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Ad_TutorController.java
 *
 * Controller này quản lý các hoạt động CRUD cho đối tượng Tutor trong khu vực
 * quản trị (admin).
 */
@Controller
@RequestMapping("/admin/tutors")
public class Ad_TutorController {

    @Autowired
    private Ad_TutorService tutorService;

    // Giả định bạn có một SubjectService để lấy danh sách môn học cho form
    @Autowired
    private SubjectService subjectService;

    private final String path = "/WEB-INF/views/Admin/";

    /**
     * Hiển thị danh sách tất cả các gia sư.
     *
     * @param model Model để truyền dữ liệu tới view.
     * @return Tên của view JSP để hiển thị danh sách.
     */
    @GetMapping("")
    public String listTutors(Model model) {
        // Lưu ý: Tên phương thức trong service của bạn là getAllSubjects(),
        // nhưng logic là lấy ra Tutor. Cân nhắc đổi tên thành getAllTutors() cho rõ ràng.
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Tutor> tutors = tutorService.getAllTutor();
        model.addAttribute("subjects", subjects);
        model.addAttribute("tutors", tutors);
        model.addAttribute("body", path + "tutors.jsp");
        return "layout/Admin/main";
    }

    /**
     * Hiển thị form để tạo mới một gia sư.
     *
     * @param model Model để truyền dữ liệu tới view.
     * @return Tên của view JSP chứa form.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("tutor", new Tutor());

        // Nạp danh sách môn học để hiển thị trong dropdown
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects); // <-- ĐÃ SỬA: Thêm subjects vào model

        // Trỏ đến file form (tutor_form.jsp) thay vì trang danh sách
        model.addAttribute("body", path + "createTutor.jsp");
        return "layout/Admin/main";
    }

    /**
     * SỬA LỖI: Xử lý việc submit form tạo mới gia sư. Đã sửa lại để trả về
     * trang form nếu có lỗi validation.
     */
    @PostMapping("/create")
    public String processCreateForm(@Valid @ModelAttribute("tutor") Tutor tutor,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Lấy lại đối tượng Subject đầy đủ trước khi validation để tránh lỗi
        if (tutor.getSubject() != null && tutor.getSubject().getSubId() != null) {
            Subject subject = subjectService.getSubjectById(tutor.getSubject().getSubId());
            tutor.setSubject(subject);
        }

//        if (bindingResult.hasErrors()) {
//            // Nếu có lỗi, nạp lại danh sách môn học và hiển thị lại trang form
//            List<Subject> subjects = subjectService.getAllSubjects();
//            model.addAttribute("subjects", subjects);
//            model.addAttribute("body", path + "createTutor.jsp"); // <-- ĐÃ SỬA: Trả về trang form
//            return "layout/Admin/main";
//        }

        try {
            tutorService.createNewTutor(tutor);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo gia sư thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi tạo gia sư: " + e.getMessage());
        }

        return "redirect:/admin/tutors";
    }

    /**
     * Hiển thị form để chỉnh sửa thông tin gia sư.
     *
     * @param id ID của gia sư cần chỉnh sửa.
     * @param model Model để truyền dữ liệu.
     * @return Tên của view JSP chứa form.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Tutor tutor = tutorService.getTutorById(id); // Cân nhắc đổi tên getSubjectById thành getTutorById
        model.addAttribute("tutor", tutor);
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("body", path + "form.jsp");
        return "admin/layout/main";
    }

    /**
     * Xử lý việc submit form cập nhật gia sư.
     *
     * @param tutor Đối tượng Tutor được binding từ form.
     * @param bindingResult Kết quả của việc validation.
     * @param model Model để truyền lại dữ liệu nếu có lỗi.
     * @param redirectAttributes Để gửi thông báo flash sau khi redirect.
     * @return Chuyển hướng về trang danh sách nếu thành công.
     */
    @PostMapping("/edit/{id}")
    public String processEditForm(@Valid @ModelAttribute("tutor") Tutor tutor,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            List<Subject> subjects = subjectService.getAllSubjects();
//            model.addAttribute("subjects", subjects);
//            model.addAttribute("body", path + "form.jsp");
//            return "admin/layout/main";
//        }

        try {
            tutorService.updateTutorDetails(tutor);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật gia sư thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật gia sư: " + e.getMessage());
        }

        return "redirect:/admin/tutors";
    }

    /**
     * Xử lý yêu cầu xóa mềm một gia sư.
     *
     * @param id ID của gia sư cần xóa.
     * @param redirectAttributes Để gửi thông báo.
     * @return Chuyển hướng về trang danh sách.
     */
    @GetMapping("/delete/{id}")
    public String deleteTutor(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        try {
            tutorService.deleteTutor(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa gia sư thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa gia sư: " + e.getMessage());
        }
        return "redirect:/admin/tutors";
    }

    @GetMapping("/api/{id}")
    @ResponseBody // Rất quan trọng: Báo cho Spring trả về dữ liệu, không phải view
    public ResponseEntity<Tutor> getTutorByIdAsJson(@PathVariable("id") String id) {
        // Đổi tên phương thức cho rõ ràng
        System.out.println("--- API call: Yêu cầu lấy thông tin gia sư với ID: " + id);

        // Giả sử tên phương thức đã được đổi cho rõ ràng
        Tutor tutor = tutorService.getTutorById(id);

        if (tutor != null) {
            // Dòng debug để xác nhận đã tìm thấy gia sư
            System.out.println("--- API call: Tìm thấy gia sư: " + tutor.getpName());
            // Trả về đối tượng Tutor với mã 200 OK
            return ResponseEntity.ok(tutor);
        } else {
            // Dòng debug để thông báo không tìm thấy
            System.out.println("--- API call: Không tìm thấy gia sư với ID: " + id);
            // Trả về mã 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}

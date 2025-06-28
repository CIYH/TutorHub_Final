/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.uef.model.Student;
import com.uef.service.Ad_StudentService;
import static java.lang.System.console;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/students")
public class Ad_StudentController {

    @Autowired
    private Ad_StudentService studentService;

    private final String VIEW_PATH = "/WEB-INF/views/Admin/";

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllActiveStudents();
        model.addAttribute("students", students);
        model.addAttribute("body", VIEW_PATH + "students.jsp");
        return "/layout/Admin/main";
    }

    // Hiển thị form thêm sinh viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("body", VIEW_PATH + "add.jsp");
        return "/layout/Admin/main";
    }

    // Xử lý thêm sinh viên
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {
        try {
            studentService.createNewStudent(student);
            redirectAttributes.addFlashAttribute("success", "Thêm sinh viên thành công!");
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage() );
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm sinh viên: " + e.getMessage());
            return "redirect:/admin/students";
        }
        return "redirect:/admin/students";
    }

    // Hiển thị form chỉnh sửa sinh viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model,
            RedirectAttributes redirectAttributes) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên với ID: " + id);
            return "redirect:/admin/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("body", VIEW_PATH + "edit.jsp");
        return "/layout/Admin/main";
    }

    // Xử lý cập nhật sinh viên
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {
        try {
            studentService.updateStudent(student);
            redirectAttributes.addFlashAttribute("success", "Cập nhật sinh viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật sinh viên: " + e.getMessage());
            return "redirect:/admin/students/edit/" + student.getId();
        }
        return "redirect:/admin/students";
    }

    // Xóa sinh viên (soft delete)
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id,
            RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Xóa sinh viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa sinh viên: " + e.getMessage());
        }
        return "redirect:/admin/students";
    }

    // Xem chi tiết sinh viên
    @GetMapping("/detail/{id}")
    public String viewStudentDetail(@PathVariable String id, Model model,
            RedirectAttributes redirectAttributes) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên với ID: " + id);
            return "redirect:/admin/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("body", VIEW_PATH + "students.jsp");
        return "/layout/Admin/main";
    }

    // Tìm kiếm sinh viên
    @GetMapping("/search")
    public String searchStudents(@RequestParam("name") String name, Model model) {
        List<Student> students = studentService.searchStudentsByName(name);
        model.addAttribute("students", students);
        model.addAttribute("searchTerm", name);
        model.addAttribute("body", VIEW_PATH + "students.jsp");
        return "/layout/Admin/main";
    }

    @GetMapping("/api/{id}")
    @ResponseBody // Rất quan trọng: Báo cho Spring trả về dữ liệu, không phải view
    public ResponseEntity<Student> getStudentByIdAsJson(@PathVariable String id) {
        
        
        System.out.println("--- API call: Yêu cầu lấy thông tin hoc vien với ID: " + id);
        
        
        Student student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println("--- API call: Tìm thấy hoc vien: " + student.getpName());
            return ResponseEntity.ok(student);
        } else {
            System.out.println("--- API call: Không tìm thấy hoc vien với ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }
}

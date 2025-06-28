/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.controller;

import com.opencsv.CSVWriter;
import com.uef.model.BookingStudent;
import com.uef.model.CheckIn;
import com.uef.model.CheckInForm;
import com.uef.model.Login_Model;
import com.uef.model.StudentList;
import com.uef.service.Tu_BookingSessionService;
import com.uef.service.CheckInService;
import com.uef.service.StudentListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class Tu_StudentManageController {

    @Autowired
    private Tu_BookingSessionService bookingSessionService;

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private StudentListService studentListService;

    @GetMapping("/DuyetBooking")
    public String duyetBookingPage(Model model, HttpSession session) {
        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        List<BookingStudent> listBs = bookingSessionService.getAllBookingStudent(tutorId);
        model.addAttribute("listBs", listBs);
        model.addAttribute("activeSidebar", "Quản Lý Học Viên");
        model.addAttribute("activePage", "Duyệt Booking");
        model.addAttribute("body", "/WEB-INF/views/Tutor/DuyetBooking.jsp");
        return "layout/Tutor/main";
    }

    @GetMapping("/DuyetBooking/{id}")
    public String duyetBookingFormPage(@PathVariable("id") int bookingId, Model model) {
        BookingStudent listBs2 = bookingSessionService.getBookingStudentById(bookingId);
        model.addAttribute("listBs2", listBs2);
        model.addAttribute("activeSidebar", "Quản Lý Học Viên");
        model.addAttribute("activePage", "Duyệt Booking");
        model.addAttribute("body", "/WEB-INF/views/Tutor/DuyetBookingForm.jsp");
        return "layout/Tutor/main";
    }

    @PostMapping("/ChapNhanBooking")
    public String handleApproveBooking(@RequestParam("bookingId") int bookingId,
            @RequestParam("sessionId") int sessionId,
            @RequestParam("duration") int duration,
            RedirectAttributes redirectAttributes) {
        try {
            bookingSessionService.approveBookingAndDeclineOthers(bookingId, sessionId, duration); // Gọi service để cập nhật DB
            redirectAttributes.addFlashAttribute("successMessage", "Duyệt đăng kí thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi duyệt đăng kí.");
        }
        // Chuyển hướng về trang danh sách chờ duyệt
        return "redirect:/tutor/DuyetBooking"; // <-- Thay bằng URL trang danh sách của bạn
    }

    @PostMapping("/TuChoiBooking")
    public String handleDeclineBooking(@RequestParam("bookingId") int bookingId,
            @RequestParam("declineReason") String reason,
            RedirectAttributes redirectAttributes) {
        try {
            bookingSessionService.declineBooking(bookingId, reason); // Gọi service để cập nhật DB
            redirectAttributes.addFlashAttribute("successMessage", "Đã từ chối đăng kí thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi từ chối đăng kí.");
        }
        // Chuyển hướng về trang danh sách chờ duyệt
        return "redirect:/tutor/DuyetBooking"; // <-- Thay bằng URL trang danh sách của bạn
    }

    @GetMapping("/CheckIn")
    public String checkInPage(Model model, @RequestParam(name = "subjectQuery", required = false) String subjectQuery, HttpSession session) {
        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        // Gọi service để lấy danh sách khóa học dựa trên tutorId và từ khóa tìm kiếm
        List<CheckIn> courseList = checkInService.getCoursesForCheckin(tutorId, subjectQuery);

        // Đưa danh sách kết quả và từ khóa tìm kiếm vào model
        model.addAttribute("courseList", courseList);
        model.addAttribute("subjectQuery", subjectQuery);
        model.addAttribute("activeSidebar", "Quản Lý Học Viên");
        model.addAttribute("activePage", "Điểm Danh");
        model.addAttribute("body", "/WEB-INF/views/Tutor/CheckIn.jsp");
        return "layout/Tutor/main";
    }

    @GetMapping("/CheckIn/{sessionId}")
    public String showCheckinForm(@PathVariable("sessionId") int sessionId, Model model) {
        List<CheckInForm> historyList = checkInService.getHistory(sessionId);
        List<CheckInForm> studentList = checkInService.getStudentsBySessionId(sessionId);

        model.addAttribute("historyList", historyList);
        model.addAttribute("studentList", studentList);
        model.addAttribute("sessionId", sessionId); // Rất quan trọng để các form có thể dùng

        // Bạn cũng nên gửi thông tin khóa học để hiển thị tiêu đề
        // model.addAttribute("course", courseService.findCourseById(sessionId));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentTime = now.format(formatter);
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("activeSidebar", "Quản Lý Học Viên");
        model.addAttribute("activePage", "Điểm Danh");
        model.addAttribute("body", "/WEB-INF/views/Tutor/CheckInForm.jsp");
        return "layout/Tutor/main";
    }

    @PostMapping("/check-in/create")
    public String handleCreateCheckin(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {
        int sessionId = Integer.parseInt(request.getParameter("sessionId"));

        Map<String, String> studentStatuses = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("status_")) {
                String studentId = paramName.substring("status_".length());
                String status = request.getParameter(paramName);
                studentStatuses.put(studentId, status);
            }
        }

        try {
            Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

            // 2. Kiểm tra xem người dùng đã đăng nhập chưa
            if (loggedInUser == null) {
                return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
            }

            // 3. Lấy ID từ đối tượng vừa lấy ra
            String tutorId = loggedInUser.getId();
            checkInService.createCheckinRecords(tutorId, sessionId, studentStatuses);
            redirectAttributes.addFlashAttribute("successMessage", "Điểm danh thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi điểm danh.");
        }
        return "redirect:/tutor/CheckIn/" + sessionId;
    }

    @PostMapping("/check-in/update")
    public String handleUpdateCheckin(@RequestParam("checkinRecordId") long checkinRecordId,
            @RequestParam("status") String status,
            @RequestParam("sessionId") int sessionId, // Nhận lại sessionId để redirect
            RedirectAttributes redirectAttributes) {
        try {
            checkInService.updateCheckinStatus(checkinRecordId, status);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật điểm danh thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật.");
        }
        return "redirect:/tutor/CheckIn/" + sessionId;
    }

    @GetMapping("/DanhSachHocSinh")
    public String shoưDanhSachHocSinh(Model model, HttpSession session) {
        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa
        if (loggedInUser == null) {
            return "redirect:/login"; // Nếu chưa, đá về trang đăng nhập
        }

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        List<StudentList> studentList = studentListService.getListOfStudent(tutorId);
        model.addAttribute("studentList", studentList);
        model.addAttribute("activeSidebar", "Quản Lý Học Viên");
        model.addAttribute("activePage", "Danh Sách Học Viên");
        model.addAttribute("body", "/WEB-INF/views/Tutor/DanhSachHocSinh.jsp");
        return "layout/Tutor/main";
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response, HttpSession session) throws IOException {
        // 1. Thiết lập các header cho response
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=danh-sach-hoc-vien.xlsx";
        response.setHeader(headerKey, headerValue);

        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        // 2. Lấy dữ liệu từ service (ví dụ)
        // List<StudentAttendance> studentList = studentService.getStudentAttendanceList();
        List<StudentList> studentList = studentListService.getListOfStudent(tutorId); // Dùng dữ liệu giả để demo

        // 3. Sử dụng Apache POI để tạo file Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh sách học viên");

        // Tạo dòng tiêu đề
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Học viên", "Khóa học", "Dự kiến hoàn tất", "Có mặt", "Muộn", "Vắng"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Đổ dữ liệu vào các dòng
        int rowNum = 1;
        for (StudentList student : studentList) {
            Row row = sheet.createRow(rowNum++);
            String formattedDate = student.getSessionEnd().format(formatter);
            row.createCell(0).setCellValue(student.getP_name());
            row.createCell(1).setCellValue(student.getTitle());
            row.createCell(2).setCellValue(formattedDate);
            row.createCell(3).setCellValue(student.getPresent());
            row.createCell(4).setCellValue(student.getLate());
            row.createCell(5).setCellValue(student.getAbsent());
        }

        // 4. Ghi workbook vào response output stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * Xử lý yêu cầu xuất danh sách học viên ra file CSV.
     *
     * @param response Đối tượng HttpServletResponse để ghi file trả về.
     * @throws IOException
     */
    @GetMapping("/export/csv")
    public void exportToCsv(HttpServletResponse response, HttpSession session) throws IOException {
        // 1. Thiết lập các header cho response
        response.setContentType("text/csv; charset=UTF-8");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=danh-sach-hoc-vien.csv";
        response.setHeader(headerKey, headerValue);

        // Ghi UTF-8 BOM để Excel đọc tiếng Việt không bị lỗi font
        response.getOutputStream().write(0xEF);
        response.getOutputStream().write(0xBB);
        response.getOutputStream().write(0xBF);

        Login_Model loggedInUser = (Login_Model) session.getAttribute("loggedInUser");

        // 2. Kiểm tra xem người dùng đã đăng nhập chưa

        // 3. Lấy ID từ đối tượng vừa lấy ra
        String tutorId = loggedInUser.getId();
        // 2. Lấy dữ liệu từ service (ví dụ)
        // List<StudentAttendance> studentList = studentService.getStudentAttendanceList();
        List<StudentList> studentList = studentListService.getListOfStudent(tutorId);; // Dùng dữ liệu giả để demo

        // 3. Sử dụng OpenCSV để ghi file CSV
        // Dùng OutputStreamWriter với UTF-8 để đảm bảo tiếng Việt
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        CSVWriter csvWriter = new CSVWriter(writer);

        // Ghi dòng tiêu đề
        String[] headers = {"Học viên", "Khóa học", "Dự kiến hoàn tất", "Có mặt", "Muộn", "Vắng"};
        csvWriter.writeNext(headers);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Ghi dữ liệu
        for (StudentList student : studentList) {
            String formattedDate = student.getSessionEnd().format(formatter);
            String[] data = {
                student.getP_name(),
                student.getTitle(),
                formattedDate,
                String.valueOf(student.getPresent()),
                String.valueOf(student.getLate()),
                String.valueOf(student.getAbsent())
            };
            csvWriter.writeNext(data);
        }

        // 4. Đóng writer
        csvWriter.close();
        writer.close();
    }
}

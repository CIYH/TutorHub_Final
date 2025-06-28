/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.Student;
import com.uef.repository.Ad_StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.password.PasswordEncoder; // **ĐÃ XÓA**

import java.util.List;

@Service
public class Ad_StudentService {

    @Autowired
    private Ad_StudentRepository studentRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder; // **ĐÃ XÓA**
    /**
     * Lấy danh sách tất cả sinh viên đang hoạt động
     *
     * @return List<Student>
     */
    public List<Student> getAllActiveStudents() {
        return studentRepository.findAll();
    }

    /**
     * Tìm sinh viên theo ID
     *
     * @param id
     * @return Student hoặc null nếu không tìm thấy
     */
    public Student getStudentById(String id) {
        return studentRepository.findById(id);
    }

    /**
     * Tạo mới sinh viên
     *
     * @param student
     * @throws IllegalArgumentException nếu thông tin không hợp lệ
     */
    public void createNewStudent(Student student) {
        // Validate dữ liệu đầu vào
        if (student == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không hợp lệ");
        }

        // **ĐÃ XÓA DÒNG MÃ HÓA MẬT KHẨU**
        // student.setPassword(passwordEncoder.encode(student.getPassword()));
        // Đảm bảo role là 'user'
        String newId = generateNewStudentId();
        student.setId(newId);

        // **ĐÃ XÓA DÒNG MÃ HÓA MẬT KHẨU (nếu có, vì file bạn cung cấp không dùng PasswordEncoder)**
        studentRepository.save(student);
    }

    /**
     * Cập nhật thông tin sinh viên
     *
     * @param student
     * @throws RuntimeException nếu không tìm thấy sinh viên
     */
    public void updateStudent(Student student) {
        Student existingStudent = studentRepository.findById(student.getId());
        if (existingStudent == null) {
            throw new RuntimeException("Không tìm thấy sinh viên với ID: " + student.getId());
        }

        // Giữ nguyên mật khẩu nếu không được cung cấp mật khẩu mới
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword(existingStudent.getPassword());
        }
        // **ĐÃ XÓA KHỐI ELSE MÃ HÓA MẬT KHẨU MỚI**
        // else {
        //     // Mã hóa mật khẩu mới nếu có
        //     student.setPassword(passwordEncoder.encode(student.getPassword()));
        // }

        studentRepository.update(student);
    }

    /**
     * Xóa mềm sinh viên (chuyển trạng thái Active thành 'Off')
     *
     * @param id
     */
    public void deleteStudent(String id) {
        studentRepository.softDelete(id);
    }

    /**
     * Tìm kiếm sinh viên theo tên (có thể mở rộng thêm các tiêu chí khác)
     *
     * @param name
     * @return List<Student>
     */
    public List<Student> searchStudentsByName(String name) {
        // Triển khai logic tìm kiếm tại repository nếu cần
        List<Student> allStudents = studentRepository.findAll();
        return allStudents.stream()
                .filter(s -> s.getpName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    private String generateNewStudentId() {
        // Lấy tổng số học viên hiện có để xác định số thứ tự tiếp theo
        // Bạn có thể cần một phương thức mới trong repository để lấy count
        // Hoặc lấy tất cả và đếm, nhưng lấy count từ DB sẽ hiệu quả hơn cho hệ thống lớn
        int nextSequence = studentRepository.countStudents() + 1;
        return String.format("student%03d", nextSequence); // Định dạng thành user001, user002...
    }
}

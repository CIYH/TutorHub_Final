/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.Subject;
import com.uef.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SubjectService.java
 *
 * Lớp này chứa logic nghiệp vụ để quản lý các môn học. Nó hoạt động như một
 * tầng trung gian giữa Controller và Repository.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Lấy danh sách tất cả các môn học.
     *
     * @return một danh sách các đối tượng Subject.
     */
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    /**
     * Lấy thông tin chi tiết của một môn học dựa trên ID.
     *
     * @param id ID của môn học.
     * @return đối tượng Subject nếu tìm thấy, ngược lại trả về null.
     */
    public Subject getSubjectById(int id) {
        return subjectRepository.findById(id);
    }

    /**
     * Tạo một môn học mới. Logic nghiệp vụ bổ sung (ví dụ: kiểm tra dữ liệu đầu
     * vào) có thể được thêm ở đây.
     *
     * @param subject đối tượng Subject chứa thông tin cần tạo.
     */
    public void createNewSubject(Subject subject) {
        // Ví dụ về logic nghiệp vụ:
        if (subject == null || subject.getSuName() == null || subject.getSuName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên môn học không được để trống.");
        }
        if (subject.getAdmin() == null || subject.getAdmin().getId() == null) {
            throw new IllegalArgumentException("Thông tin người tạo không hợp lệ.");
        }

        subjectRepository.save(subject);
    }

    /**
     * Cập nhật thông tin của một môn học đã có.
     *
     * @param subject đối tượng Subject chứa thông tin cần cập nhật.
     */
    public void updateSubjectDetails(Subject subject) {
        // Kiểm tra xem môn học có tồn tại không trước khi cập nhật
        Subject existingSubject = subjectRepository.findById(subject.getSubId());
        if (existingSubject == null) {
            throw new RuntimeException("Không tìm thấy môn học với ID: " + subject.getSubId());
        }

        subjectRepository.update(subject);
    }

    /**
     * Xóa một môn học (xóa mềm).
     *
     * @param id ID của môn học cần xóa.
     */
    public void deleteSubject(int id) {
        subjectRepository.softDelete(id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.Tutor;
import com.uef.repository.Ad_TutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author qnhat
 */
@Service
public class Ad_TutorService {

    @Autowired
    private Ad_TutorRepository tutorRepository;

    /**
     * Lấy danh sách tất cả các môn học.
     *
     * @return một danh sách các đối tượng Subject.
     */
    public List<Tutor> getAllTutor() {
        return tutorRepository.findAll();
    }

    /**
     * Lấy thông tin chi tiết của một môn học dựa trên ID.
     *
     * @param id ID của môn học.
     * @return đối tượng Subject nếu tìm thấy, ngược lại trả về null.
     */
    public Tutor getTutorById(String TutorId) {
        return tutorRepository.findById(TutorId);
    }

    /**
     * Tạo một môn học mới. Logic nghiệp vụ bổ sung (ví dụ: kiểm tra dữ liệu đầu
     * vào) có thể được thêm ở đây.
     *
     * @param subject đối tượng Subject chứa thông tin cần tạo.
     */
    // Đảm bảo tính toàn vẹn dữ liệu khi ghi vào 2 bảng
    @Transactional
    public void createNewTutor(Tutor tutor) {
        // --- Logic kiểm tra nghiệp vụ (Validation) ---
        if (tutor == null) {
            throw new IllegalArgumentException("Thông tin gia sư không được để trống.");
        }
        if (!StringUtils.hasText(tutor.getpName())) {
            throw new IllegalArgumentException("Tên gia sư không được để trống.");
        }
        if (!StringUtils.hasText(tutor.getEmail())) {
            throw new IllegalArgumentException("Email không được để trống.");
        }
        if (!StringUtils.hasText(tutor.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu không được để trống.");
        }
        // Thêm các kiểm tra khác nếu cần, ví dụ: định dạng email, độ mạnh mật khẩu...

        if (tutor.getSubject() == null || tutor.getSubject().getSubId() <= 0) {
            throw new IllegalArgumentException("Gia sư phải được gán một môn học.");
        }

        // TODO: Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu.
        // Ví dụ: tutor.setPassword(passwordEncoder.encode(tutor.getPassword()));
        // Sau khi kiểm tra thành công, gọi repository để lưu
        int count = tutorRepository.countTutors();
        String newTutorId;

        // Vòng lặp để đảm bảo ID là duy nhất, phòng trường hợp ID bị xóa không tuần tự
        do {
            count++;
            newTutorId = "tutor" + count;
        } while (tutorRepository.findById(newTutorId) != null); // Giả định có phương thức findById

        tutor.setId(newTutorId);
        //-----------------------------

        // Đảm bảo role là 'tutor'
        tutor.setpRole("tutor");

        // (Tùy chọn) Mã hóa mật khẩu nếu bạn có PasswordEncoder
        // tutor.setPassword(passwordEncoder.encode(tutor.getPassword()));
        tutorRepository.save(tutor);
    }

    /**
     * Cập nhật thông tin chi tiết của một gia sư.
     *
     * @param tutor Đối tượng Tutor chứa thông tin cần cập nhật.
     */
    @Transactional
    public void updateTutorDetails(Tutor tutor) {
        // Có thể thêm logic kiểm tra xem gia sư có tồn tại không trước khi cập nhật
        tutorRepository.update(tutor);
    }

    /**
     * Xóa mềm một gia sư.
     *
     * @param tutorId ID của gia sư cần xóa.
     */
    public void deleteTutor(String tutorId) {
        tutorRepository.softDelete(tutorId);
    }
}

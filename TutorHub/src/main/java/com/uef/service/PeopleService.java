package com.uef.service;

import com.uef.model.Stu_People;
import com.uef.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    /**
     * PHƯƠNG THỨC MỚI: Xác thực người dùng hiệu quả.
     * @param email Email người dùng nhập.
     * @param password Mật khẩu người dùng nhập.
     * @return Đối tượng People nếu xác thực thành công, ngược lại null.
     */
    public Stu_People authenticate(String email, String password) {
        Stu_People user = peopleRepository.findByEmail(email);

        // So sánh mật khẩu (trong thực tế nên dùng cơ chế hash an toàn hơn)
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Trả về null nếu sai email hoặc mật khẩu
    }

    @Transactional
    public void updateProfile(Stu_People updatedInfo) {
        // Lấy thông tin người dùng hiện tại từ DB để đảm bảo không thay đổi các trường quan trọng
        Stu_People existingUser = peopleRepository.findById(updatedInfo.getId());
        if (existingUser != null) {
            existingUser.setpName(updatedInfo.getpName());
            existingUser.setEmail(updatedInfo.getEmail());
            existingUser.setAddress(updatedInfo.getAddress());
            existingUser.setGender(updatedInfo.getGender());
            existingUser.setPhonenumber(updatedInfo.getPhonenumber());

            peopleRepository.update(existingUser);
        }
    }

    public Stu_People getProfileById(String id) {
        return peopleRepository.findById(id);
    }

    public String getPeopleNameById(String id) {
        return peopleRepository.findNameById(id);
    }

    /**
     * Lấy tất cả người dùng (sử dụng cho các chức năng quản lý, không nên dùng
     * cho login).
     *
     * @return Danh sách tất cả người dùng.
     */
    public List<Stu_People> getAll() {
        return peopleRepository.findAll();
    }
}

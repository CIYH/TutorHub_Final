/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.TutorProfile;
import com.uef.repository.TutorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TutorProfileService {
    
    @Autowired
    private TutorProfileRepository tutorProfileRepository;

    public TutorProfile getTutorById(String tutorId) {
        return tutorProfileRepository.findById(tutorId);
    }

    public void saveTutorProfile(TutorProfile tutor) {
        // Có thể thêm các logic kiểm tra dữ liệu, log, gửi email... ở đây
        tutorProfileRepository.update(tutor);
    }
}

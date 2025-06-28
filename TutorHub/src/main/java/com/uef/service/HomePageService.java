/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.HomePage;
import com.uef.repository.HomePageRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HomePageService {
    
    @Autowired
    private HomePageRepository homePageRepository;
    
    public Map<String, Object> getDataForHomepage(String tutorId) {
        Map<String, Object> data = new HashMap<>();
        
        String tutorName = homePageRepository.findTutorNameById(tutorId);
        List<HomePage> schedules = homePageRepository.findAllSchedulesByTutorId(tutorId);

        data.put("tutorName", tutorName);
        data.put("schedules", schedules);
        
        return data;
    }
}

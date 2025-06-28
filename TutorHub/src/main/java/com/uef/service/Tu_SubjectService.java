/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.ChonLichHoc;
import com.uef.model.Tu_Subject;
import com.uef.repository.Tu_SubjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class Tu_SubjectService {
    
    @Autowired
    private Tu_SubjectRepository subjectRepository;
    
    public List<Tu_Subject> getAll(){
        return subjectRepository.findAll();
    }
    
    
}

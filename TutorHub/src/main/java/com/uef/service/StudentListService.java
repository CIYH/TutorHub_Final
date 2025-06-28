/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.StudentList;
import com.uef.repository.StudentListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StudentListService {
    
    @Autowired
    private StudentListRepository studentListRepository;
    
    public List<StudentList> getListOfStudent(String tutorId){
        return studentListRepository.findListOfStudent(tutorId);
    }
}

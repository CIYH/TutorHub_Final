/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.model.Stu_Subject;
import com.uef.repository.SubjectRepository_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService_1 {

    @Autowired
    private SubjectRepository_1 subjectRepository;

    public List<Stu_Subject> getAllActiveSubjects() {
        return subjectRepository.findAllActive();
    }
}


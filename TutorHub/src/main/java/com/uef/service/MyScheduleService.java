package com.uef.service;

import com.uef.model.MySchedule;
import com.uef.repository.MyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyScheduleService {

    @Autowired
    private MyScheduleRepository myScheduleRepository;

    public List<MySchedule> getSchedulesForStudent(String studentId) {
        return myScheduleRepository.findApprovedSchedulesByStudentId(studentId);
    }
}
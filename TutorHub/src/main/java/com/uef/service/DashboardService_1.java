/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uef.service;

import com.uef.dto.DashboardStatsDTO;
import com.uef.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService_1 {

    @Autowired
    private DashboardRepository dashboardRepository;

    /**
     * Tổng hợp tất cả các thông tin thống kê cho Dashboard.
     *
     * @return Một đối tượng DTO chứa tất cả các số liệu.
     */
    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setTutorCount(dashboardRepository.getTutorCount());
        stats.setStudentCount(dashboardRepository.getStudentCount());
        stats.setSubjectCount(dashboardRepository.getSubjectCount());
        stats.setDailyCheckIns(dashboardRepository.getTodaysCheckIns());
        return stats;
    }
}

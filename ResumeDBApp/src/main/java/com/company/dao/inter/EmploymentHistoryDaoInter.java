/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import java.util.List;

/**
 *
 * @author elxan
 */
public interface EmploymentHistoryDaoInter {
    
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
    
    public EmploymentHistory getById(int id);
    
    public boolean updateEmploymentHistrory(EmploymentHistory emp);
    
    public boolean addEmploymentHistory(EmploymentHistory emp);
    
    public boolean removeEmploymentHistory(int id);
}

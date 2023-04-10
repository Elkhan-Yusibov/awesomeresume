/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.dao.inter;

import com.company.entity.Skill;
import java.util.List;

/**
 *
 * @author elxan
 */
public interface SkillDaoInter {
    
    public List<Skill> getAllSkill();
    
    public Skill getById(int id);
    
    public boolean updateSkill(Skill s);
    
    public boolean addSkill(Skill s);
    
    public boolean removeSkill(int id);
}

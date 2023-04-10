/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elxan
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillid");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("SELECT"
                    + " us.id as userSkillId,	"
                    + " u.*,"
                    + "	us.skill_id,"
                    + "	s.NAME AS skill_name,"
                    + "	us.power "
                    + " FROM"
                    + "	user_skill us"
                    + "	LEFT JOIN USER u ON us.user_id = u.id"
                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
                    + " WHERE"
                    + "	us.user_id =?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public UserSkill getById(int id) {
        UserSkill result = null;
        try ( Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from user_skill where id = " + id);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUserSkill(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUserSkill(UserSkill us) {
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("update user_skill set user_id=?,skill_id=?,power=? where id=?");
            stmt.setInt(1, us.getUser().getId());
            stmt.setInt(2, us.getSkill().getId());
            stmt.setInt(3, us.getPower());
            stmt.setInt(4, us.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUserSkill(UserSkill us) {
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("insert into user_skill(user_id,skill_id,power) value(?,?,?)");
            stmt.setInt(1, us.getUser().getId());
            stmt.setInt(2, us.getSkill().getId());
            stmt.setInt(3, us.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUserSkill(int id) {
        try ( Connection c = connect()) {

            Statement stmt = c.createStatement();
            return stmt.execute("delete from user_skill where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

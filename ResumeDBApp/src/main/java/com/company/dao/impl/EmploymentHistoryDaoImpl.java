/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elxan
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");
        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("SELECT * from employment_history where user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory u = getEmploymentHistory(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public EmploymentHistory getById(int id) {
        EmploymentHistory result = null;
        try ( Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from employment_history where id = " + id);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getEmploymentHistory(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEmploymentHistrory(EmploymentHistory emp) {
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("update employment_history set header=?,begin_date=?,end_date=?,job_description=?,user_id=? where id=?");
            stmt.setString(1, emp.getHeader());
            stmt.setDate(2, emp.getBeginDate());
            stmt.setDate(3, emp.getEndDate());
            stmt.setString(4, emp.getJobDescription());
            stmt.setInt(5, emp.getUser().getId());
            stmt.setInt(6, emp.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory emp) {
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("insert into employment_history(header,begin_date,end_date,job_description,user_id) value(?,?,?,?,?)");
            stmt.setString(1, emp.getHeader());
            stmt.setDate(2, emp.getBeginDate());
            stmt.setDate(3, emp.getEndDate());
            stmt.setString(4, emp.getJobDescription());
            stmt.setInt(5, emp.getUser().getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
        try ( Connection c = connect()) {

            Statement stmt = c.createStatement();
            return stmt.execute("delete from employment_history where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

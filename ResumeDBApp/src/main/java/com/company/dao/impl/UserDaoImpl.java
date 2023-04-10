/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
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
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profilDesc = rs.getString("profile_description");
        String adress = rs.getString("adress");
        Date birthDate = rs.getDate("birthdate");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, phone, email,profilDesc,adress, birthDate, nationality, birthPlace);
      
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try ( Connection c = connect()) {
            String sql = "SELECT "
                    + "u. * , "
                    + "c.name AS birthplace, "
                    + "n.nationality  "
                    + "FROM  user u  "
                    + "LEFT JOIN country n ON u.nationality_id = n.id "
                    + "LEFT JOIN country c ON u.birthplace_id = c.id WHERE 1=1";
            if(name!=null && !name.trim().isEmpty()){
                sql+=" and u.name=? ";
            }
            if(surname!=null && !surname.trim().isEmpty()){
                sql+=" and u.surname=? ";
            }
            if(nationalityId!=null ){
                sql+=" and u.nationality_id=? ";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i=1;
            if(name!=null && !name.trim().isEmpty()){
                stmt.setString(i,name);
                i++;
            }
            if(surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i,surname);
                i++;
            }
            if(nationalityId!=null){
                stmt.setInt(i,nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();


                while (rs.next()) {
                    User u = getUser(rs);

                    result.add(u);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try ( Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone=?,email=?,profile_description=?,adress=?,birthdate=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfilDesc());
            stmt.setString(6,u.getAdress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try ( Connection c = connect()) {

            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try ( Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select "
                    + " u.*, "
                    + " n.nationality , "
                    + " c.name as birthplace "
                    + " from user u "
                    + " left join country n on u.nationality_id = n.id "
                    + " left join country c on u.birthplace_id = c.id where u.id = " + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try ( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,profile_description,adress,birthplace_id) value(?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfilDesc());
            stmt.setString(6, u.getAdress());
            Country cc = u.getBirthPlace();
            stmt.setInt(7, cc.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

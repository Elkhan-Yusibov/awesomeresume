/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoInter userDao = Context.instanceUserDao();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        String nationalityIdStr = request.getParameter("nid");
        Integer nationalityId = null;
        if(nationalityIdStr!=null && !nationalityIdStr.trim().isEmpty()) {
            nationalityId = Integer.parseInt(nationalityIdStr);
        }
        List<User> list = userDao.getAll(name, surname, nationalityId);
         request.setAttribute("list",list);
            request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}

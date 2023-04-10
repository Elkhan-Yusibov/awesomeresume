<%-- 
    Document   : user
    Created on : Feb 15, 2023, 8:44:05 PM
    Author     : elxan
--%>

<%@page import="com.company.entity.User"%>
<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User u = (User) request.getAttribute("user");
        %>

        <div>
            <form method="POST" action="userdetail">
                <%--@declare id="name"--%><%--@declare id="surname"--%>
                <%--@declare id="adress"--%><%--@declare id="email"--%>
                    <%--@declare id="phone"--%>
                    <%--@declare id="nationality"--%><%--@declare id="country"--%><%--@declare id="birthplace"--%><%--@declare id="skill"--%><input type="hidden" name="id" value="<%=u.getId()%>"/>

                    <label for="name">Name:</label>
                    <input type="text" name="name" value="<%=u.getName()%>"/>
                    <br/>
                    <label for="surname">Surname:</label>
                    <input type="text" name="surname" value="<%=u.getSurname()%>"/>
                    <br/>
                    <label for="adress">Adress:</label>
                    <input type="text" name="adress" value="<%=u.getAdress()%>"/>
                    <br/>
                    <label for="email">Email:</label>
                    <input type="text" name="email" value="<%=u.getEmail()%>"/>
                    <br/>
                    <label for="phone">Phone:</label>
                    <input type="text" name="phone" value="<%=u.getPhone()%>" />
                    <br/>
                    <label for="nationality">Nationality:</label>
                    <input type="text" name="nationality" value="<%=u.getNationality().toString()%>" />
                    <br/>
                    <label for="birthplace">Birthplace:</label>
                    <input type="text" name="birthplace" value="<%=u.getBirthPlace().getName()%>"/>
                    <br/>





                    <input type="submit" name="action" value="update"/>
            </form>
        </div>
    </body>
</html>

<%-- 
    Document   : user
    Created on : Feb 15, 2023, 8:44:05 PM
    Author     : elxan
--%>

<%@page import="com.company.entity.User"%>
<%@ page import="com.company.dao.inter.UserDaoInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/CSS/users.css">
        <script src="https://kit.fontawesome.com/c279124962.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script type="text/javascript" src="assets/js/users.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<User> list = (List<User>) request.getAttribute("list");
        %>
        <div class="container">
        <div class="row">
        <div class="col-sm-4">
            <form action="users.jsp" method="GET" >
                <%--@declare id="name"--%><%--@declare id="surname"--%>
                <%--@declare id="adress"--%>
                <div class="form-group">
                     <label for="name">name:</label>
                     <input  placeholder="Enter name" class="form-control" type="text" name="name" value="" />
                </div>
                <br/>
                <div class="form-group">
                     <label for="surname">surname:</label>
                     <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
                </div>
                <input class="btn btn-primary" type="submit" name="search" value="Search" id="btnsearch"/>
            </form>
        </div>
        </div>
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th>name</th>
                        <th>surname</th>
                        <th>nationality</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                <%for(User u: list){%>
                    <tr>
                        <td><%=u.getName()%></td>
                        <td><%=u.getSurname()%></td>
                        <td><%=u.getNationality().getName()==null?"N/A":u.getNationality().getName()%></td>
                        <td style="width: 5px">

                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <input type="hidden" name="action" value="delete"/>
                                <button class="btn btn-danger" type="submit" value="delete" data-toggle="modal" data-target="#exampleModalLong"
                                        onclick="setIdForDelete('<%=u.getId()%>')">
                                    <i class=" fa-solid fa-trash-can"></i>
                                </button>

                        </td>
                        <td style="width: 5px">
                            <form action="userdetail" method="GET" >
                                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                                <input type="hidden" name="action" value="update"/>
                                <button class="btn btn-info" type="submit" value="update">
                                    <i class=" fa-sharp fa-solid fa-pen"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure?
                    </div>
                    <div class="modal-footer">
                        <form method="POST" action="userdetail">
                            <input type="hidden" name="id" value="" id="idForDelete"/>
                            <input type="hidden" name="action" value="delete"/>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>

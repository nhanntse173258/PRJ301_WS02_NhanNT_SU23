<%-- 
    Document   : search
    Created on : Jun 10, 2023, 8:27:03 AM
    Author     : jtkjm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="NhanNT.model.UsersDTO"%>
<%@page import="java.util.List"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color ="red">
        <%
            Cookie[] cookies = request.getCookies();
            String username = "";
            username = cookies[cookies.length - 1].getName();
        %>
        Welcome, <%=username%>
        </font>

        <h1>Search Page</h1>
        <form action="mainController">
            Search Value <input type="text" name="txtSearchValue" value=""/><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>

        <br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<UsersDTO> result = (List<UsersDTO>) request.getAttribute("SEARCHRESULT");

                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (UsersDTO dto : result) {
                        String urlRewritng = "mainController?pk=" + dto.getUsername()
                                + "&btAction=Delete&txtSearchValue=" + searchValue;
                %>
            <form action="mainController"><tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>">
                        <%= dto.getUsername()%>
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>">
                    </td>
                    <td>
                        <input type="text" name="txtFullname" value="<%= dto.getFullname()%>">
                    </td>
                    <% if (dto.isRole()) {%>
                    <td>
                        <input type="checkbox" name="isAdmin" value="ON" checked="checked">
                        <%= dto.isRole()%>
                    </td>
                    <% } else {%>
                    <td>
                        <input type="checkbox" name="isAdmin" value="ON">
                        <%= dto.isRole()%>
                    </td>
                    <% }%>
                    <td>
                        <a href="<%=urlRewritng%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%=searchValue%>"</>
                        <input type="submit" name="btAction" value="Update" </>
                    </td>
                </tr></form>
                <%
                    }
                %>

        </tbody>
    </table>
    <%} else { %>
    <h2>No record matched!</h2>
    <%
            }
        }
    %>
</body>
</html>

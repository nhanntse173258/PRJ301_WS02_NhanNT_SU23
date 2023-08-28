<%-- 
    Document   : createNewAccount
    Created on : Jun 28, 2023, 7:32:20 AM
    Author     : jtkjm
--%>

<%@page import="NhanNT.model.InsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account Page</title>
    </head>
    <body>
          <form action="mainController">
            <c:set var="err" value="${requestScope.INSERTERROR}"/>
                Username*(6-20 characters) <input type="tezt" name="newUsername" value=""/><br/>
            <c:if test="${not empty err.usernameLengthErr}">
                <font color ="red">${err.usernameLengthErr}</font><br/>
            </c:if>
                Password*(6-30 characters)<input type="password" name="password" value=""/><br/>
            <c:if test="${not empty err.passwordLengthErr}">
                <font color ="red">${err.passwordLengthErr}</font><br/>
            </c:if>
                Confirm* <input type="password" name="confirm" value=""/><br/>
            <c:if test="${not empty err.confirmNotMatch}">
                <font color ="red">${err.confirmNotMatch}</font><br/>
            </c:if>
            Full name(2-50 characters)<input type="text" value="" name="fullname"/><br/>
            <c:if test="${not empty err.fullnameLengthErr}">
                <font color ="red">${err.fullnameLengthErr}</font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction"/>
            <input type="reset" value="reset"/><br/>
            <c:if test="${not empty err.usernameExisted}">
                <font color="red">${err.usernameExisted}</font><br/>
            </c:if>
        </form>
    </body>
</html>

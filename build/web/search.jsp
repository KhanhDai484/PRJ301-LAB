<%-- 
    Document   : search
    Created on : Jun 11, 2024, 11:01:30 PM
    Author     : HP
--%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.format.DateTimeParseException"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.Invoice"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.FULLNAME}</h1>


        <form action="MainController" method="post">
            <input type="text" name="txtsearch" size="40" value="${sessionScope.SEARCHVALUE}"/>
            <input type="submit" value="Search" name="action"/>

        </form>

        <p style="text-align: right; width: 480px;">
            <a href="MainController?action=Addnew">Add new</a>
        </p>

        <table border='1'>
            <tr>

                <th>No</th>
                <th>Invoice ID</th>
                <th>Invoice Date</th>
                <th>Customer</th>
                <th>User ID</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>

            <c:forEach var="x" items="${sessionScope.LISTINVOICE}" varStatus="counter">

                <tr>
                    <td>${counter.count}</td>
                    <td>${x.getInvID()}</td>                    
                <form action="MainController" method="POST">
                    <td><input type="text" name="invDate" value="${x.getInvDate()}"/></td>
                        <td><input type="text" name="customer" value=" ${x.getCustomer()}"/></td>
                        <td>${x.getUserID()}</td>
                    <td><input type="submit" name="action" value="Delete"/></td>                                          
                    <td>
                        <input type="hidden" name="invID" value="${x.getInvID()}"/>
                        <input type="submit" name="action" value="Update"/>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>

    <br>


    <table border="1">
        <tr>
            <th>Total Invoices</th>
            <th>Update Information</th> 
            <th>Delete Information</th>
        </tr>
        <tr>
            <td>${sessionScope.SUM}</td>
            <td>${sessionScope.SUCCESS}</td>
            <td>${sessionScope.DETAIL}</td>
        </tr>


    </table>


    <p>
        <a href="MainController?action=Logout">Logout</a>
    </p>
</body>
</html>

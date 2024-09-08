<%-- 
    Document   : login
    Created on : Jun 11, 2024, 10:54:32 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        
        <h1>${sessionScope.mess}</h1>
        <form action="MainController" method="POST">
            <p>
                username<input type="text" name="userID"/>               
            </p>
            <p>
                password <input type="password" name="password"/>               
            </p>
            <p>
                <input type="submit" value="Login" name="action"/>
                <input type="reset" value="Reset"/>
            </p>
        </form>
    </body>
</html>

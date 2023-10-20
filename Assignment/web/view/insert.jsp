<%-- 
    Document   : insert
    Created on : Oct 16, 2023, 1:13:24 AM
    Author     : luulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert new Student</title>
    </head>
    <body>
        <h1>Insert new Student</h1>
        <form action="" method="post">
            <label for="studentName"></label>
            <input type="text" name="studentName" id="studentName" placeholder="Input student name">
            <input type="radio" name="gender" value="male" id="male">
            <label for="male">Male</label>
            <input type="radio" name="gender" value="female" id="female">
            <label for="female">Female</label>
            <input type="submit" value="Save">
        </form>
    </body>
</html>

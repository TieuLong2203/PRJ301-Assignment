<%-- 
    Document   : takeattendance
    Created on : Oct 14, 2023, 11:06:32 PM
    Author     : luulo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Attendance Page</title>
        <link rel="stylesheet" href="css/takeattendance.css"/>
    </head>
    <body>
        <div class="title">
            <h1>Attendance for Group: <span></span></h1>
        </div>
        <form action="attendance" method="post">
            <input type="hidden" value="${requestScope.sessionId}" name="sessionId">
            <c:forEach items="${requestScope.students}" var="student">
                <input type="hidden" name="studentId" value="${student.studentId}">
            </c:forEach>
            <table>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Student Email</th>
                    <th>Attendance</th>
                    <th>Description</th>
                </tr>
                <c:forEach items="${requestScope.students}" var="student">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>${student.studentEmail}</td>
                        <td><input type="radio" name="status${student.studentId}" value="attend"> Attend
                            <input type="radio" name="status${student.studentId}" value="absent"> Absent
                        </td>
                        <td>
                            <input type="text" value="Nothing" name="description${student.studentId}" placeholder="Note">
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="submit">
                <input type="submit" value="Submit Attendance">
            </div>
        </form>
    </body>
</html>
